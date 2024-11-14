package kz.mediation.api.mediation_executor.service.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import kz.mediation.api.mediation_executor.exception.GreenApiClientException;
import kz.mediation.api.mediation_executor.model.GreenApiResponseRawData;
import kz.mediation.api.mediation_executor.model.whatsappReceiveMessage.*;
import kz.mediation.api.mediation_executor.repository.GreenApiResponseRawDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MediationWhatsappIncomingMessageMapper {
    private final GreenApiResponseRawDataRepository greenApiResponseRawDataRepository;

    public Root handleIncomingMessage(JsonNode notification, int receiptId, String typeWebhook) {
        return getRoot(notification, receiptId, typeWebhook);
    }

    public Root getRoot(JsonNode notification, int receiptId, String typeWebhook) {
        GreenApiResponseRawData rawData = new GreenApiResponseRawData();
        rawData.setTypeWebhook(typeWebhook);
        rawData.setTextMessage(notification.toString());
        greenApiResponseRawDataRepository.save(rawData);
        Body body = extractBodyFromNotification(notification, typeWebhook);
        return new Root(receiptId, body);
    }

    private Body extractBodyFromNotification(JsonNode notification, String typeWebhook) {
        Body body = new Body();
        long timestamp = 0L;
        String idMessage = "";
        InstanceData instanceData = getInstanceData(notification);
        SenderData senderData = getSenderData(notification);
        MessageData messageData = getMessageData(notification);
        try {
            timestamp = notification.get("timestamp").asLong();
            idMessage = notification.get("idMessage").asText();
            body.setTypeWebhook(typeWebhook);
            body.setInstanceData(instanceData);
            body.setTimestamp(timestamp);
            body.setIdMessage(idMessage);
            body.setSenderData(senderData);
            body.setMessageData(messageData);
        } catch (Exception e) {
            log.error(new GreenApiClientException("Error while extractBodyFromNotification", e).toString());
        }
        return body;
    }

    private MessageData getMessageData(JsonNode notification) {
        MessageData messageData = new MessageData();
        try {
            JsonNode messageDataNode = notification.get("messageData");
            log.info("messageDataNode {}", messageDataNode);
            String typeMessage = messageDataNode.get("typeMessage").asText();
            messageData.setTypeMessage(typeMessage);
            MessageDataType messageDataType = getMessageDataType(typeMessage, messageDataNode);
            messageData.setMessageDataType(messageDataType);
        } catch (Exception e) {
            log.error(new GreenApiClientException("Error while parsing MessageData", e).toString());
            log.info("notification: {}", notification);
        }
        return messageData;
    }

    private MessageDataType getMessageDataType(String typeMessage, JsonNode messageDataNode) {
        return switch (typeMessage) {
            case "textMessage" -> extractTextMessage(messageDataNode);
            case "extendedTextMessage" -> extractExtendedTextMessage(messageDataNode);
            case "imageMessage", "videoMessage", "documentMessage" -> extractFileMessage(messageDataNode);
            default -> {
                log.error(new GreenApiClientException("MessageDataType unknown type: " + typeMessage).toString());
                yield null;
            }
        };
    }

    private MessageDataType extractFileMessage(JsonNode messageDataNode) {
        JsonNode documentMessageNode = messageDataNode.get("fileMessageData");
        FileMessageData fileMessageData = null;
        try {
            fileMessageData = new FileMessageData();
            fileMessageData.setDownloadUrl(documentMessageNode.get("downloadUrl").asText());
            fileMessageData.setCaption(documentMessageNode.get("caption").asText());
            fileMessageData.setFileName(documentMessageNode.get("fileName").asText());
            fileMessageData.setJpegThumbnail(documentMessageNode.get("jpegThumbnail").asText());
            fileMessageData.setIsAnimated(documentMessageNode.get("isAnimated").asBoolean());
            fileMessageData.setMimeType(documentMessageNode.get("mimeType").asText());
            fileMessageData.setForwardingScore(documentMessageNode.get("forwardingScore").asInt());
            fileMessageData.setIsForwarded(documentMessageNode.get("isForwarded").asBoolean());
        } catch (Exception e) {
            log.error("Error while parsing FileMessageData", e);
        }
        return fileMessageData;
    }

    private MessageDataType extractTextMessage(JsonNode messageDataNode) {
        JsonNode textMessageNode = messageDataNode.get("textMessageData");
        TextMessageData textMessageData = null;
        try {
            textMessageData = new TextMessageData();
            textMessageData.setTextMessage(textMessageNode.get("textMessage").asText());
        } catch (Exception e) {
            log.error("Error while parsing TextMessageData", e);
        }
        return textMessageData;
    }

    private MessageDataType extractExtendedTextMessage(JsonNode messageDataNode) {
        JsonNode textMessageNode = messageDataNode.get("extendedTextMessageData");
        ExtendedTextMessageData extendedTextMessageData = null;
        try {
            extendedTextMessageData = new ExtendedTextMessageData();
            extendedTextMessageData.setText(textMessageNode.get("text").asText());
            extendedTextMessageData.setDescription(textMessageNode.get("description").asText());
            extendedTextMessageData.setTitle(textMessageNode.get("title").asText());
            extendedTextMessageData.setJpegThumbnail(textMessageNode.get("jpegThumbnail").asText());
            extendedTextMessageData.setForwardingScore(textMessageNode.get("forwardingScore").asInt());
            extendedTextMessageData.setPreviewType(textMessageNode.get("previewType").asText());
            extendedTextMessageData.setForwarded(textMessageNode.get("isForwarded").asBoolean());
        } catch (Exception e) {
            log.error("Error while parsing ExtendedTextMessageData", e);
        }
        return extendedTextMessageData;
    }

    private InstanceData getInstanceData(JsonNode notification) {
        InstanceData instanceData = new InstanceData();
        try {
            JsonNode instanceDataNode = notification.get("instanceData");
            instanceData.setIdInstance(instanceDataNode.get("idInstance").asLong());
            instanceData.setWid(instanceDataNode.get("wid").asText());
            instanceData.setTypeInstance(instanceDataNode.get("typeInstance").asText());
        } catch (Exception e) {
            log.error(new GreenApiClientException("Error while parsing InstanceData", e).toString());
        }
        return instanceData;
    }

    private SenderData getSenderData(JsonNode notification) {
        SenderData senderData = new SenderData();
        try {
            JsonNode senderDataNode = notification.get("senderData");
            senderData.setChatId(senderDataNode.get("chatId").asText());
            senderData.setChatName(senderDataNode.get("chatName").asText());
            senderData.setSender(senderDataNode.get("sender").asText());
            senderData.setSenderName(senderDataNode.get("senderName").asText());
            senderData.setSenderContactName(senderDataNode.get("senderContactName").asText());
        } catch (Exception e) {
            log.error(new GreenApiClientException("Error while parsing SenderData", e).toString());
        }
        return senderData;
    }

}
