package kz.mediation.api.mediation_executor.util;

import kz.mediation.api.greenapi.model_examples.notification.*;
import kz.mediation.api.mediation_executor.config.GreenApi;
import lombok.extern.slf4j.Slf4j;

import static kz.mediation.api.mediation_executor.util.Filter.*;

@Slf4j
public abstract class WebhookHandlerAbstract implements WebhookHandler {
    protected GreenApi greenApi;
    protected StateManager stateManager;
    protected Scene startScene;

    @Override
    public void handle(Notification notification) {
//        log.info("Handle notification, {}", notification); //Notification(
//        receiptId=1,
//        body=TextMessageWebhook(
//        messageData=TextMessage(
//        typeMessage=textMessage, textMessageData=TextMessageData(
//        textMessage=sjjdshjdjdjjdjjdjdjdj, isTemplateMessage=null
//        ), quotedMessage=null
//        )))

//        log.info("Handle notificationBody: {}", notification.getBody()); //
//        TextMessageWebhook(
//            messageData=TextMessage(
//                typeMessage=textMessage,
//                textMessageData=TextMessageData(
//                    textMessage=sjjdshjdjdjjdjjdjdjdj,
//                    isTemplateMessage=null
//                ), quotedMessage=null
//        ))
        var notificationBody = notification.getBody();
        log.debug("Notification body: {}", notificationBody);
        try {
            if (startScene == null) {
                log.error("Start scene isn't choose!!! Use setter \"bot.setStartScene()\"");
            }
            handleByScene(notificationBody);
        } catch(Exception e){
            log.error(e.getMessage());
        }
    }

    public void processStateInstanceChanged(StateInstanceChanged stateInstanceChanged) {
    }

    public void processDeviceInfo(DeviceInfo deviceInfo) {
    }

    private void handleByScene(NotificationBody notificationBody) {
        if (isIncomingMessageReceived(notificationBody)) {
            var messageWebhook = (MessageWebhook) notificationBody;
            var stateId = messageWebhook.getSenderData().getChatId();
            var currentState = stateManager.get(stateId).orElse(stateManager.create(stateId));
            Scene scene = currentState.getScene();
            if (scene == null) {
                scene = startScene;
            }
            var updatedState = scene.processIncomingMessage(messageWebhook, currentState);
            stateManager.updateStateData(stateId, updatedState.getData());
        } else if (isOutgoingMessageReceived(notificationBody)) {
            var messageWebhook = (MessageWebhook) notificationBody;
            var stateId = messageWebhook.getSenderData().getChatId();
            var currentState = stateManager.get(stateId).orElse(stateManager.create(stateId));
            var scene = currentState.getScene();
            if (scene == null) {
                scene = startScene;
            }
            var updatedState = scene.processOutgoingMessage(messageWebhook, currentState);
            stateManager.updateStateData(stateId, updatedState.getData());
        } else if (isOutgoingMessageStatus(notificationBody)) {
            var messageStatusWebhook = (OutgoingMessageStatus) notificationBody;
            var stateId = messageStatusWebhook.getChatId();
            var currentState = stateManager.get(stateId).orElse(stateManager.create(stateId));
            var scene = currentState.getScene();
            if (scene == null) {
                scene = startScene;
            }
            var updatedState = scene.processOutgoingMessageStatus(messageStatusWebhook, currentState);
            stateManager.updateStateData(stateId, updatedState.getData());
        } else if (isStateInstanceChanged(notificationBody)) {
            processStateInstanceChanged((StateInstanceChanged) notificationBody);
        } else if (isIncomingCall(notificationBody)) {
            var incomingCall = (IncomingCall) notificationBody;
            var stateId = incomingCall.getFrom();
            var currentState = stateManager.get(stateId).orElse(stateManager.create(stateId));
            var scene = currentState.getScene();
            if (scene == null) {
                scene = startScene;
            }
            var updatedState = scene.processIncomingCall((IncomingCall) notificationBody, currentState);
            stateManager.updateStateData(stateId, updatedState.getData());
        } else if (isIncomingBlock(notificationBody)) {
            var incomingBlock = (IncomingBlock) notificationBody;
            var stateId = incomingBlock.getChatId() + "@c.us";
            var currentState = stateManager.get(stateId).orElse(stateManager.create(stateId));
            var scene = currentState.getScene();
            if (scene == null) {
                scene = startScene;
            }
            var updatedState = scene.processIncomingBlock((IncomingBlock) notificationBody, currentState);
            stateManager.updateStateData(stateId, updatedState.getData());
        } else if (isDeviceInfo(notificationBody)) {
            log.info("Device info");
            processDeviceInfo((DeviceInfo) notificationBody);
        } else {
            log.error("Unknown notification type");

        }
    }
}
