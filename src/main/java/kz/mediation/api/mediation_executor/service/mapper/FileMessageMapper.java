package kz.mediation.api.mediation_executor.service.mapper;

import kz.mediation.api.mediation_executor.model.FileMessage;
import kz.mediation.api.mediation_executor.model.whatsappReceiveMessage.FileMessageData;
import kz.mediation.api.mediation_executor.model.whatsappReceiveMessage.Root;

public class FileMessageMapper {
    public static FileMessage mapRootToFileMessage(Root root) {
        FileMessageData fileMessageData = (FileMessageData) root.getBody().getMessageData().getMessageDataType();
        return FileMessage
                .builder()
                .downloadUrl(fileMessageData.getDownloadUrl())
                .caption(fileMessageData.getCaption())
                .fileName(fileMessageData.getFileName())
                .jpegThumbnail(fileMessageData.getJpegThumbnail())
                .isAnimated(fileMessageData.getIsAnimated())
                .mimeType(fileMessageData.getMimeType())
                .forwardingScore(fileMessageData.getForwardingScore())
                .isForwarded(fileMessageData.getIsForwarded())
                .typeWebhook(root.getBody().getTypeWebhook())
                .timestamp(root.getBody().getTimestamp())
                .idMessage(root.getBody().getIdMessage())
                .chatId(root.getBody().getSenderData().getChatId())
                .chatName(root.getBody().getSenderData().getChatName())
                .sender(root.getBody().getSenderData().getSender())
                .senderContactName(root.getBody().getSenderData().getSenderContactName())
                .typeMessage(root.getBody().getMessageData().getTypeMessage())
                .build();
    }
}
