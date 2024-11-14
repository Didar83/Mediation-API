package kz.mediation.api.mediation_executor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.mediation.api.greenapi.model_examples.request.OutgoingMessage;
import kz.mediation.api.mediation_executor.model.Dispute;
import kz.mediation.api.mediation_executor.model.MediationStage;
import kz.mediation.api.mediation_executor.model.Person;
import kz.mediation.api.mediation_executor.model.TextMessage;
import kz.mediation.api.mediation_executor.repository.DisputeRepository;
import kz.mediation.api.mediation_executor.repository.MediationStageRepository;
import kz.mediation.api.mediation_executor.repository.PersonRepository;
import kz.mediation.api.mediation_executor.util.enums.MediationStageEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static kz.mediation.api.mediation_executor.service.mapper.AnthropicUserMessageMapper.getUserMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class DisputeProcessService {
    private final DisputeRepository disputeRepository;
    private final PersonRepository personRepository;
    private final AnthropicService anthropicService;
    private final MediationStageRepository mediationStageRepository;
    private final SendWhatsappAnswerService sendWhatsappAnswerService;

    public String startConversation(TextMessage textMessage) {
        // todo: check if first message need to send to gpt
        Person newbie = Person.builder()
                .sender(textMessage.getSender())
                .senderName(textMessage.getSenderName())
                .build();
        personRepository.save(newbie);
        return String.format("Здравствуйте, %s! Я ваш виртуальный помощник, созданный для помощи в разрешении споров. Пожалуйста, " +
                "дайте мне знать, в чем заключается ваш вопрос или введите идентификатор спора.", textMessage.getSenderName());
    }

    public String continueConversation(Optional<Person> personOptional, TextMessage textMessage) {
        String answer;
        Person person;
        if (personOptional.isPresent()) { // если персона есть, то берем ее
            person = personOptional.get();
        } else {
            return startConversation(textMessage); // если персоны нет, то запрашиваем начало спора или код спора
        }
        List<Dispute> disputeList = disputeRepository
                .findByDisputeByPerson(person)
                .stream().filter(dispute ->
                        dispute.isActive() &&
                                (dispute.getMediationStage()
                                        .getMediationStage()
                                        .equals(MediationStageEnum.NONE) ||
                                        dispute
                                                .getMediationStage()
                                                .getMediationStage()
                                                .equals(MediationStageEnum.STARTED))
                ).toList(); // ищем активные споры, которые находятся на стадии NONE или STARTED
        if (disputeList.isEmpty()) {
            answer = createNewDispute(person, textMessage); // создаем новый спор если нет активных
        } else {
            // если активные споры есть, то проверяем их количество
            if (disputeList.size() > 1) {
                // выбрать один спор из множества
                StringBuilder sb = new StringBuilder("Выберите спор для продолжения: \n");
                for (Dispute dispute : disputeList) {
                    sb.append(dispute.getCodeIdentification()).append("\n");
                }
                return sb.toString();
            } else {
                Dispute actualDispute = disputeList.getFirst();
                if (actualDispute.getMediationStage().getMediationStage().equals(MediationStageEnum.NONE)) {
                    answer = continueConversationWithInitiator(person, textMessage, disputeList);
                } else if (actualDispute.getMediationStage().getMediationStage().equals(MediationStageEnum.STARTED)) {
                    answer = continueConversationWithRespondent(person, textMessage, disputeList);
                } else {
                    answer = getDisputeDecision(actualDispute);
                }
            }
        }
        String checkedAnswer = answer == null ? "Error" : answer.isEmpty() ? "Error" : answer;
        return checkedAnswer;
    }

    public String startDisputeWithCodeIdentification(Optional<Person> person, TextMessage textMessage, UUID disputeIdentification) {
        Optional<Dispute> disputeOptional = disputeRepository.findByCodeIdentification(disputeIdentification);
        Dispute dispute;
        if (disputeOptional.isEmpty()) {
            return "Спор по идентификатору " + disputeIdentification + " не найден";
        } else {
            dispute = disputeOptional.get();
            dispute.setActive(true);
            //set dispute active and deactivate others
            if (person.isPresent()) {
                List<Dispute> disputeList = disputeRepository
                        .findByDisputeByPerson(person.get())
                        .stream().filter(disputeFromList ->
                                disputeFromList.isActive() &&
                                        (disputeFromList.getMediationStage()
                                                .getMediationStage()
                                                .equals(MediationStageEnum.NONE) ||
                                                disputeFromList
                                                        .getMediationStage()
                                                        .getMediationStage()
                                                        .equals(MediationStageEnum.STARTED))
                        ).toList();
                for (Dispute deactivateDispute : disputeList) {
                    if (!deactivateDispute.getCodeIdentification().equals(disputeIdentification)) {
                        deactivateDispute.setActive(false);
                    }
                }
                disputeRepository.saveAll(disputeList); // не забываем сохранять
            }
        }
        switch (dispute.getMediationStage().getMediationStage()) {
            case NONE:
                // этой стадии не должно быть, так как идентификатор пользователь увидит только после завершения стадии NONE
                // update: эта стадия может быть если у пользователя есть спор инициированный другим пользователем
                if (person.isPresent() && dispute.getDisputeInitiator().equals(person.get())) {
                    return continueConversationWithInitiator(person.get(), textMessage, List.of(dispute));
                } else {
                    return "Произошла ошибка - вы не зарегистрированы в споре с идентификатором " + disputeIdentification;
                }
            case STARTED:
                return startConversationWithRespondent(person, textMessage, dispute); // отдаем активный спор, текст и опциональную персону
            case IN_PROGRESS: // завершаем спор и отдаем решение
                makeDisputeDecision(dispute);
                return "Прения по спору завершены. Решение скоро будет принято";
            case FINISHED: //если спор завершен, то отдаем решение
                return "Спор завершен. Решение по спору: \n" + getDisputeDecision(dispute);
            default:
                log.error("Unknown mediation stage: {} for dispute with code identification {}",
                        dispute.getMediationStage().getMediationStage(), disputeIdentification);
        }
        return "Error";
    }

    private String startConversationWithRespondent(Optional<Person> person, TextMessage textMessage, Dispute dispute) {
        // person with code identification
        Person respondent = person.orElseGet(() -> Person.builder()
                .sender(textMessage.getSender())
                .senderName(textMessage.getSenderName())
                .build());
        personRepository.save(respondent);
        String answer;
        MediationStage stage = dispute.getMediationStage();
        if (!stage.getMediationStage().equals(MediationStageEnum.STARTED)) {
            return "Прения сторон завершены. Ожидаем решение по спору";
        } else {
            String userMessage = null;
            String respondentPosition = dispute.getRespondentPosition() + ". \n" + textMessage.getTextMessage();
            dispute.setRespondentPosition(respondentPosition);
            userMessage = getUserMessage(dispute);
            String anthropicResponse = anthropicService.generateText(userMessage);
            String json = anthropicResponse
                    .replace("<updated_json>", "")
                    .replace("</updated_json>", "")
                    .trim();
            ObjectMapper objectMapper = new ObjectMapper();
            StringBuilder sb = new StringBuilder();
            try {
                JsonNode root = objectMapper.readTree(json);
                dispute.setRespondentPosition(root.get("respondentPosition").asText());
                boolean isClear = root.get("isClear").asBoolean();
                if (isClear) {
                    dispute.setMediationStage(MediationStage.builder()
                            .mediationStage(MediationStageEnum.IN_PROGRESS)
                            .build());
                    sb.append("Второй этап спора завершен. Ожидайте решение по спору");
                } else {
                    sb.append(root.get("additionalQuestion").asText());
                }
            } catch (JsonProcessingException e) {
                log.error("Error while parsing JSON", e);
            }
            answer = sb.toString();
        }
        mediationStageRepository.save(dispute.getMediationStage());
        disputeRepository.save(dispute);
        return answer;
    }

    @Scheduled(fixedRate = 30000)
    private void checkDisputeInProgress() {
        List<MediationStage> mediationStageList = mediationStageRepository.findByMediationStage(MediationStageEnum.IN_PROGRESS);
        for (MediationStage stage : mediationStageList) {
            Optional<Dispute> dispute = disputeRepository.findByMediationStage(stage);
            dispute.ifPresent(this::makeDisputeDecision);
        }
    }

    private void makeDisputeDecision(Dispute dispute) {
        //todo: отправить в антропик диспут и получить решение, положить его в диспут и сохранить
        if (dispute.getMediationStage().getMediationStage().equals(MediationStageEnum.IN_PROGRESS)) {
            String userMessage = null;
            userMessage = getUserMessage(dispute);
            String anthropicResponse = anthropicService.generateText(userMessage);
            String json = anthropicResponse
                    .replace("<updated_json>", "")
                    .replace("</updated_json>", "")
                    .trim();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode root = objectMapper.readTree(json);
                dispute.setDecision(root.get("decision").asText());
                boolean isDecisionAnotherLanguageEmpty = root.get("isDecisionAnotherLanguage").isEmpty();
                if (isDecisionAnotherLanguageEmpty) {
                    dispute.setDecisionAnotherLanguage(root.get("decisionAnotherLanguage").asText());
                }
                dispute.setMediationStage(MediationStage.builder()
                        .mediationStage(MediationStageEnum.FINISHED)
                        .build());
                dispute.setDisputeClosedAt(LocalDateTime.now());
                String decision = "Решение по спору: " + dispute.getDecision();
                String decisionAnotherLanguage = isDecisionAnotherLanguageEmpty ? null :
                        "Dispute decision: " + dispute.getDecisionAnotherLanguage();
                Person initiator = dispute.getDisputeInitiator();
                OutgoingMessage outgoingMessageToInitiator = new OutgoingMessage.Builder()
                        .chatId(initiator.getSender())
                        .message(decision)
                        .quotedMessageId(null)
                        .linkPreview(false)
                        .build();
                sendWhatsappAnswerService.sendAnswer(outgoingMessageToInitiator);
                Person respondent = dispute.getDisputeRespondent();
                OutgoingMessage outgoingMessageToRespondent = new OutgoingMessage.Builder()
                        .chatId(respondent.getSender())
                        .message(isDecisionAnotherLanguageEmpty ? decision : decisionAnotherLanguage)
                        .quotedMessageId(null)
                        .linkPreview(false)
                        .build();
                sendWhatsappAnswerService.sendAnswer(outgoingMessageToRespondent);
            } catch (JsonProcessingException e) {
                log.error("Error while parsing JSON", e);
            }
            mediationStageRepository.save(dispute.getMediationStage());
            disputeRepository.save(dispute);
        } else {
            log.error("Dispute with code identification {} is not in IN_PROGRESS stage", dispute.getCodeIdentification());
        }
    }

    private String getDisputeDecision(Dispute dispute) {
        //todo: добавить оплату
        return dispute.getDecision();
    }

    private String createNewDispute(Person person, TextMessage textMessage) {
        MediationStageEnum mediationStageEnum = MediationStageEnum.NONE;
        Dispute newDispute = Dispute.builder()
                .disputeInitiator(person)
                .initiatorPosition(textMessage.getTextMessage())
                .isActive(true)
                .mediationStage(MediationStage
                        .builder()
                        .mediationStage(mediationStageEnum)
                        .build())
                .build();
        String userMessage = getUserMessage(newDispute);
        String anthropicResponse = anthropicService.generateText(userMessage);
        String json = anthropicResponse
                .replace("<updated_json>", "")
                .replace("</updated_json>", "")
                .trim();
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        try {
            JsonNode root = objectMapper.readTree(json);
            root.fields().forEachRemaining(entry -> {
                if (entry.getKey().equals("additionalQuestion")) {
                    sb.append(entry.getValue().asText());
                }
            });
        } catch (JsonProcessingException e) {
            log.error("Error while parsing JSON", e);
        }
        disputeRepository.save(newDispute);
        return sb.toString();
    }

    private String continueConversationWithInitiator(Person person, TextMessage textMessage, List<Dispute> disputeInitiatorList) {
        if (disputeInitiatorList.size() > 1) {
            StringBuilder sb = new StringBuilder("Выберите спор для продолжения: \n");
            for (Dispute dispute : disputeInitiatorList) {
                sb.append(dispute.getCodeIdentification()).append("\n");
            }
            return sb.toString();
        } else {
            Dispute dispute = disputeInitiatorList.getFirst();
            MediationStage stage = dispute.getMediationStage();
            if (!stage.getMediationStage().equals(MediationStageEnum.NONE)) {
                return "Спор начат. Ожидаем ответ оппонента";
            } else {
                String userMessage = null;
                String initiatorPosition = dispute.getInitiatorPosition() + ". \n" + textMessage.getTextMessage();
                dispute.setInitiatorPosition(initiatorPosition);
                userMessage = getUserMessage(dispute);
                String anthropicResponse = anthropicService.generateText(userMessage);
                String json = anthropicResponse
                        .replace("<updated_json>", "")
                        .replace("</updated_json>", "")
                        .trim();
                ObjectMapper objectMapper = new ObjectMapper();
                StringBuilder sb = new StringBuilder();
                try {
                    JsonNode root = objectMapper.readTree(json);
                    root.fields().forEachRemaining(entry -> {
                        switch (entry.getKey()) {
                            case "disputeSubject" -> dispute.setDisputeSubject(entry.getValue().asText());
                            case "initiatorPosition" -> dispute.setInitiatorPosition(entry.getValue().asText());
                            case "isClear" -> {
                                if (entry.getValue().asBoolean()) {
                                    dispute.setMediationStage(MediationStage.builder()
                                            .mediationStage(MediationStageEnum.STARTED)
                                            .build());
                                    sb.append("Первый этап спора завершен. Теперь отправьте ссылку второй стороне спора " +
                                                    "для продолжения: \n\nhttps://wa.me/77066356718?text=")
                                            .append(dispute.getCodeIdentification());
                                }
                            }
                            case "additionalQuestion" -> {
                                if (entry.getValue().asText().isEmpty()) {
                                    // do nothing
                                } else {
                                    sb.append(entry.getValue().asText());
                                }
                            }
                        }
                    });
                } catch (JsonProcessingException e) {
                    log.error("Error while parsing JSON", e);
                }
                disputeRepository.save(dispute);
                return sb.toString();
            }
        }
    }

    private String continueConversationWithRespondent(Person person, TextMessage textMessage, List<Dispute> disputeRespondentList) {
        Dispute dispute = disputeRespondentList.getFirst();
        MediationStage stage = dispute.getMediationStage();
        if (!stage.getMediationStage().equals(MediationStageEnum.STARTED)) {
            return "Прения сторон завершены. Ожидаем решение по спору";
        } else {
            String userMessage = null;
            String respondentPosition = dispute.getRespondentPosition() + ". \n" + textMessage.getTextMessage();
            dispute.setRespondentPosition(respondentPosition);
            userMessage = getUserMessage(dispute);
            String anthropicResponse = anthropicService.generateText(userMessage);
            String json = anthropicResponse
                    .replace("<updated_json>", "")
                    .replace("</updated_json>", "")
                    .trim();
            ObjectMapper objectMapper = new ObjectMapper();
            StringBuilder sb = new StringBuilder();
            try {
                JsonNode root = objectMapper.readTree(json);
                dispute.setRespondentPosition(root.get("respondentPosition").asText());
                boolean isClear = root.get("isClear").asBoolean();
                if (isClear) {
                    dispute.setMediationStage(MediationStage.builder()
                            .mediationStage(MediationStageEnum.IN_PROGRESS)
                            .build());
                    sb.append("Второй этап спора завершен. Ожидайте решение по спору");
                } else {
                    sb.append(root.get("additionalQuestion").asText());
                }
            } catch (JsonProcessingException e) {
                log.error("Error while parsing JSON", e);
            }
            mediationStageRepository.save(dispute.getMediationStage());
            disputeRepository.save(dispute);
            return sb.toString();
        }
    }


}
