package kz.mediation.api.mediation_executor.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.mediation.api.mediation_executor.exception.GreenApiClientException;
import kz.mediation.api.mediation_executor.model.whatsappReceiveMessage.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MediationNotificationMapper {
    private final ObjectMapper objectMapper;
    private final MediationWhatsappMessageMapper mediationWhatsappMessageMapper;

    public Root getWhatsappMessage(String responseBody) {
        int receiptId = -1;
        try {
            var notification = objectMapper.readTree(responseBody);
            if (notification.has("body") && notification.has("receiptId")) {
                receiptId = notification.get("receiptId").asInt();
                return mediationWhatsappMessageMapper.whatsappMessageHandle(notification.get("body"), receiptId);
            }
        } catch (JsonProcessingException e) {
            log.error(new GreenApiClientException("Webhook unknown type, please write to green-api support " + responseBody, e).toString());
        }
        return new Root(receiptId, null);
    }
}
