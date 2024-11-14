package kz.mediation.api.mediation_executor.service.mapper;

import kz.mediation.api.mediation_executor.model.TextMessage;
import kz.mediation.api.mediation_executor.model.whatsappReceiveMessage.ExtendedTextMessageData;
import kz.mediation.api.mediation_executor.model.whatsappReceiveMessage.Root;
import kz.mediation.api.mediation_executor.model.whatsappReceiveMessage.TextMessageData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TextMessageMapper {
    public static TextMessage mapRootToTextMessage(Root rootMessage) {
        TextMessageData textMessageData = (TextMessageData) rootMessage
                .getBody()
                .getMessageData()
                .getMessageDataType();
        return TextMessage
                .builder()
                .textMessage(textMessageData.getTextMessage())
                .typeWebhook(rootMessage.getBody().getTypeWebhook())
                .timestamp(rootMessage.getBody().getTimestamp())
                .idMessage(rootMessage.getBody().getIdMessage())
                .chatId(rootMessage.getBody().getSenderData().getChatId())
                .chatName(rootMessage.getBody().getSenderData().getChatName())
                .sender(rootMessage.getBody().getSenderData().getSender())
                .senderName(rootMessage.getBody().getSenderData().getSenderName())
                .senderContactName(rootMessage.getBody().getSenderData().getSenderContactName())
                .typeMessage(rootMessage.getBody().getMessageData().getTypeMessage())
                .build();
    }

    public static TextMessage mapExtendedTextMessage(Root rootMessage) {
        ExtendedTextMessageData extendedTextMessageData = (ExtendedTextMessageData) rootMessage
                .getBody()
                .getMessageData()
                .getMessageDataType();
        TextMessage textMessage = TextMessage
                .builder()
                .textMessage(extendedTextMessageData.getText())
                .typeWebhook(rootMessage.getBody().getTypeWebhook())
                .timestamp(rootMessage.getBody().getTimestamp())
                .idMessage(rootMessage.getBody().getIdMessage())
                .chatId(rootMessage.getBody().getSenderData().getChatId())
                .chatName(rootMessage.getBody().getSenderData().getChatName())
                .sender(rootMessage.getBody().getSenderData().getSender())
                .senderName(rootMessage.getBody().getSenderData().getSenderName())
                .senderContactName(rootMessage.getBody().getSenderData().getSenderContactName())
                .typeMessage(rootMessage.getBody().getMessageData().getTypeMessage())
                .build();
        log.info("Extended text message: {}", textMessage);
        return textMessage;
    }
}
