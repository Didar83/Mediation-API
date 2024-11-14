package kz.mediation.api.mediation_executor.util;

import kz.mediation.api.mediation_executor.config.GreenApi;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@AllArgsConstructor
@Builder
@Log4j2
public class Bot {
    private Boolean cleanNotificationQueue;
    private WebhookConsumer webhookConsumer;
    private NotificationMapper notificationMapper;
    private WebhookHandlerAbstract webhookHandler;
    private Scene startScene;
    public GreenApi greenApi;
    private StateManager stateManager;

    public void startReceiveNotification() {
        if (cleanNotificationQueue) {
            deleteAllNotification();
        }
        webhookConsumer.start(notification -> webhookHandler.handle(notification));
    }

    public void stopReceiveNotification() {
        webhookConsumer.stop();
        log.info("Receiving stopped");
    }

    @SneakyThrows
    private void deleteAllNotification() {
        boolean cleaning = true;
        log.info("deleting notifications...");
        while (cleaning) {
            try {
                var response = greenApi.greenApiReceive.receiveNotification();
                if (Objects.equals(response.getBody(), "null")) {
                    cleaning = false;
                    log.info("deleting notifications finished!");
                } else {
                    var notification = notificationMapper.get(response.getBody());
                    greenApi.greenApiReceive.deleteNotification(notification.getReceiptId());
                }
            } catch (Exception e) {
                log.error("Unexpected error: " + e.getMessage());
                Thread.sleep(5000);
            }
        }
    }

    public void setStartScene(Scene scene) {
        this.startScene = scene;
        startScene.setGreenApi(greenApi);
        startScene.setStateManager(stateManager);
        webhookHandler.startScene = startScene;
    }
}

