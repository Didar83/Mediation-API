package kz.mediation.api.mediation_executor.service;

import kz.mediation.api.greenapi.model_examples.request.OutgoingMessage;
import kz.mediation.api.mediation_executor.config.GreenApi;
import kz.mediation.api.mediation_executor.repository.MediationStageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class SendWhatsappAnswerService {
    private final GreenApi greenApi;
    private final MediationStageRepository mediationStageRepository;

    public void sendAnswer(OutgoingMessage outgoingMessage) {
        greenApi.greenApiSend.sendMessage(outgoingMessage);
    }

}
