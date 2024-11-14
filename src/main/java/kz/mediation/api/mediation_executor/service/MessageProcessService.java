package kz.mediation.api.mediation_executor.service;

import kz.mediation.api.greenapi.model_examples.request.OutgoingMessage;
import kz.mediation.api.mediation_executor.model.Person;
import kz.mediation.api.mediation_executor.model.TextMessage;
import kz.mediation.api.mediation_executor.model.whatsappReceiveMessage.Root;
import kz.mediation.api.mediation_executor.repository.FileMessageRepository;
import kz.mediation.api.mediation_executor.repository.PersonRepository;
import kz.mediation.api.mediation_executor.repository.TextMessageRepository;
import kz.mediation.api.mediation_executor.service.mapper.FileMessageMapper;
import kz.mediation.api.mediation_executor.service.mapper.TextMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageProcessService {
    private final SendWhatsappAnswerService sendWhatsappAnswerService;
    private final FileMessageRepository fileMessageRepository;
    private final TextMessageRepository textMessageRepository;
    private final PersonRepository personRepository;
    private final DisputeProcessService disputeProcessService;

    public void proceed(Root root) {
        String typeMessage = root.getBody().getMessageData().getTypeMessage();
        switch (typeMessage) {
            case "textMessage" -> proceedTextMessage(root);
            case "extendedTextMessage" -> proceedExtendedTextMessage(root);
            case "imageMessage", "videoMessage", "documentMessage" -> proceedFileMessage(root);
            default -> {
                log.error("Unknown type message in MessageProcessService.proceed(Root root): {}", typeMessage);
                tryToParseUnknownTypeMessage(root);
            }
        }
    }

    private void proceedTextMessage(Root root) {
        var textMessage = TextMessageMapper.mapRootToTextMessage(root);
        Optional<Person> person = personRepository.findBySender(textMessage.getSender());
        String answerMessageToUser = getAnswerToUser(textMessage, person);
        OutgoingMessage outgoingMessage = new OutgoingMessage.Builder()
                .chatId(textMessage.getChatId())
                .message(answerMessageToUser)
                .quotedMessageId(null)
                .linkPreview(false)
                .build();
        sendWhatsappAnswerService.sendAnswer(outgoingMessage);
    }

    private void proceedExtendedTextMessage(Root root) {
        log.info("Proceeding extended text message {}", root);
        var textMessage = TextMessageMapper.mapExtendedTextMessage(root);
        Optional<Person> person = personRepository.findBySender(textMessage.getSender());
        String answerMessageToUser = getAnswerToUser(textMessage, person);
        OutgoingMessage outgoingMessage = new OutgoingMessage.Builder()
                .chatId(textMessage.getChatId())
                .message(answerMessageToUser)
                .quotedMessageId(null)
                .linkPreview(false)
                .build();
        sendWhatsappAnswerService.sendAnswer(outgoingMessage);
    }

    private String getAnswerToUser(TextMessage textMessage, Optional<Person> person) {
        String answer = "";
        textMessageRepository.save(textMessage);
        UUID disputeIdentification = getDisputeIdentification(textMessage);
        if (disputeIdentification == null) {
            answer = disputeProcessService.continueConversation(person, textMessage);
        } else {
            answer = disputeProcessService.startDisputeWithCodeIdentification(person, textMessage, disputeIdentification);
        }
        return answer;
    }

    private UUID getDisputeIdentification(TextMessage textMessage) {
        UUID disputeIdentification = null;
        try {
            disputeIdentification = UUID.fromString(textMessage.getTextMessage().trim());
        } catch (IllegalArgumentException e) {
            log.info("The message from {} is not a dispute identification", textMessage.getChatId());
        }
        return disputeIdentification;
    }

    private void proceedFileMessage(Root root) {
        //todo: добавить обработку файлов
        fileMessageRepository.save(FileMessageMapper.mapRootToFileMessage(root));
    }

    private void tryToParseUnknownTypeMessage(Root root) {

    }
}
