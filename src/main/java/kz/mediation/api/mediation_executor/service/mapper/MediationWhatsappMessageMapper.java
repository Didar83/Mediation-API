package kz.mediation.api.mediation_executor.service.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import kz.mediation.api.mediation_executor.exception.GreenApiClientException;
import kz.mediation.api.mediation_executor.model.whatsappReceiveMessage.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MediationWhatsappMessageMapper {
    private final MediationWhatsappIncomingMessageMapper mediationWhatsappIncomingMessageMapper;
    private final MediationWhatsappOutgoingMessageMapper mediationWhatsappOutgoingMessageMapper;

    public Root whatsappMessageHandle(JsonNode notification, int receiptId) {
        String typeWebhook = notification.has("typeWebhook") ? notification.get("typeWebhook").asText() : "";
        return switch (typeWebhook) {
            case "incomingMessageReceived" ->
                    mediationWhatsappIncomingMessageMapper.handleIncomingMessage(notification, receiptId, typeWebhook);
            case "outgoingMessageReceived" ->
                    mediationWhatsappOutgoingMessageMapper.handleOutgoingMessage(notification, receiptId, typeWebhook);
            case "outgoingAPIMessageReceived" ->
                    mediationWhatsappOutgoingMessageMapper.handleOutgoingApiMessage(notification, receiptId, typeWebhook);
            case "outgoingMessageStatus" ->
                    mediationWhatsappOutgoingMessageMapper.handleOutgoingMessageStatus(notification, receiptId, typeWebhook);
            case "stateInstanceChanged" ->
                    mediationWhatsappOutgoingMessageMapper.handleStateInstanceChanged(notification, receiptId, typeWebhook);
            case "statusInstanceChanged" ->
                    mediationWhatsappOutgoingMessageMapper.handleStatusInstanceChanged(notification, receiptId, typeWebhook);
            case "deviceInfo" ->
                    mediationWhatsappOutgoingMessageMapper.handleDeviceInfo(notification, receiptId, typeWebhook);
            case "incomingCall" ->
                    mediationWhatsappOutgoingMessageMapper.handleIncomingCall(notification, receiptId, typeWebhook);
            case "incomingBlock" ->
                    mediationWhatsappOutgoingMessageMapper.handleIncomingBlock(notification, receiptId, typeWebhook);
            default -> {
                log.error(new GreenApiClientException("Webhook unknown type: " + typeWebhook).toString());
                yield new Root(receiptId, null);
            }
        };
    }


}
