package kz.mediation.api.mediation_executor.controller;

import kz.mediation.api.mediation_executor.util.Scene;
import kz.mediation.api.mediation_executor.util.WebhookHandlerAbstract;
import kz.mediation.api.greenapi.model_examples.notification.*;
import lombok.extern.slf4j.Slf4j;

import static kz.mediation.api.mediation_executor.util.Filter.*;

@Slf4j
public class MediationWebhookHandlerImpl extends WebhookHandlerAbstract {

    @Override
    public void handle(Notification notification) {
        var notificationBody = notification.getBody();
        log.debug("Notification body: {}", notificationBody);
        handleByScene(notificationBody);
    }

    private void handleByScene(NotificationBody notificationBody) {
        if (isIncomingMessageReceived(notificationBody)) {
            var messageWebhook = (MessageWebhook) notificationBody;
            var stateId = messageWebhook.getSenderData().getChatId(); //chatId=77056688119@c.us
            var currentState = stateManager.get(stateId).orElse(stateManager.create(stateId));
            Scene scene = currentState.getScene();
            if (scene == null) {
                scene = startScene;
            }
//            var updatedState = scene.processIncomingMessage(messageWebhook, currentState);
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
            log.error("Unknown notification type {}", notificationBody);
        }
    }
}

