package kz.mediation.api.mediation_executor.service.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import kz.mediation.api.mediation_executor.model.GreenApiResponseRawData;
import kz.mediation.api.mediation_executor.model.whatsappReceiveMessage.Root;
import kz.mediation.api.mediation_executor.repository.GreenApiResponseRawDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MediationWhatsappOutgoingMessageMapper {
    private final GreenApiResponseRawDataRepository greenApiResponseRawDataRepository;

    public Root handleOutgoingMessage(JsonNode notification, int receiptId, String typeWebhook) {
        return getRoot(notification, receiptId, typeWebhook);
    }

    public Root getRoot(JsonNode notification, int receiptId, String typeWebhook) {
        GreenApiResponseRawData rawData = new GreenApiResponseRawData();
        rawData.setTypeWebhook(typeWebhook);
        rawData.setTextMessage(notification.toString());
        greenApiResponseRawDataRepository.save(rawData);
        return new Root(receiptId, null);
    }


    //todo: implement the following methods
    public Root handleOutgoingApiMessage(JsonNode notification, int receiptId, String typeWebhook) {
        return getRoot(notification, receiptId, typeWebhook);
    }

    public Root handleOutgoingMessageStatus(JsonNode notification, int receiptId, String typeWebhook) {
        return getRoot(notification, receiptId, typeWebhook);
    }

    public Root handleStateInstanceChanged(JsonNode notification, int receiptId, String typeWebhook) {
        return getRoot(notification, receiptId, typeWebhook);
    }

    public Root handleStatusInstanceChanged(JsonNode notification, int receiptId, String typeWebhook) {
        return getRoot(notification, receiptId, typeWebhook);
    }

    public Root handleDeviceInfo(JsonNode notification, int receiptId, String typeWebhook) {
        return getRoot(notification, receiptId, typeWebhook);
    }

    public Root handleIncomingCall(JsonNode notification, int receiptId, String typeWebhook) {
        return getRoot(notification, receiptId, typeWebhook);
    }

    public Root handleIncomingBlock(JsonNode notification, int receiptId, String typeWebhook) {
        return getRoot(notification, receiptId, typeWebhook);
    }
}
