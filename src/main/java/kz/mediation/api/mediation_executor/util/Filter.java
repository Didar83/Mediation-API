package kz.mediation.api.mediation_executor.util;

import kz.mediation.api.greenapi.model_examples.notification.*;

import java.util.regex.Pattern;

public class Filter {
    public static boolean isIncomingMessageReceived(NotificationBody notificationBody) {
        return notificationBody instanceof MessageWebhook messageWebhook &&
                !messageWebhook.getSenderData().getSender().equals(messageWebhook.getInstanceData().getWid());
    }

    public static boolean isOutgoingMessageReceived(NotificationBody notificationBody) {
        return notificationBody instanceof MessageWebhook messageWebhook &&
                messageWebhook.getSenderData().getSender().equals(messageWebhook.getInstanceData().getWid());
    }

    public static boolean isOutgoingMessageStatus(NotificationBody notificationBody) {
        return notificationBody instanceof OutgoingMessageStatus;
    }

    public static boolean isStateInstanceChanged(NotificationBody notificationBody) {
        return notificationBody instanceof StateInstanceChanged;
    }

    public static boolean isIncomingCall(NotificationBody notificationBody) {
        return notificationBody instanceof IncomingCall;
    }

    public static boolean isIncomingBlock(NotificationBody notificationBody) {
        return notificationBody instanceof IncomingBlock;
    }

    public static boolean isDeviceInfo(NotificationBody notificationBody) {
        return notificationBody instanceof DeviceInfo;
    }

    public static boolean isMessageTextExpected(MessageWebhook messageWebhook, String expectedMessage) {
        if (messageWebhook instanceof TextMessageWebhook textMessageWebhook) {
            return textMessageWebhook.getMessageData().getTextMessageData().getTextMessage().equals(expectedMessage);

        } else if (messageWebhook instanceof ExtendedTextMessageWebhook msg) {
            return msg.getMessageData().getExtendedTextMessageData().getText().equals(expectedMessage);
        }

        return false;
    }

    public static boolean isMessageTextRegex(MessageWebhook messageWebhook, Pattern regexPattern) {
        if (messageWebhook instanceof TextMessageWebhook textMessageWebhook) {
            return regexPattern.matcher(textMessageWebhook.getMessageData().getTextMessageData().getTextMessage()).find();

        } else if (messageWebhook instanceof ExtendedTextMessageWebhook msg) {
            return regexPattern.matcher(msg.getMessageData().getExtendedTextMessageData().getText()).find();
        }

        return false;
    }

    public static boolean isSenderIdExpected(MessageWebhook messageWebhook, String expectedSenderId) {
        return messageWebhook.getSenderData().getSender().equals(expectedSenderId);
    }
}

