package kz.mediation.api.mediation_executor.util;

import kz.mediation.api.greenapi.model_examples.notification.Notification;
import kz.mediation.api.mediation_executor.config.GreenApi;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Log4j2
public class WebhookConsumer {
    private final GreenApi greenApi;
    private final NotificationMapper notificationMapper;
    private boolean running;

    @SneakyThrows
    public void start(WebhookHandler webhookHandler) {
        running = true;
        //todo: refactor this code to Service layer

        while (running) {
            try {
                var response = greenApi.greenApiReceive.receiveNotification();
                if (Objects.equals(response.getBody(), "null")) {
                    log.info("receiveNotification timeout");
                } else {
                    log.info("receiveNotification response: {}", response.getBody());
                    Notification notification = notificationMapper.get(response.getBody());
                    if (notification.getBody() == null) {
                        log.info("Can't map webhook from json!");
                        greenApi.greenApiReceive.deleteNotification(notification.getReceiptId());
                    } else {
                        webhookHandler.handle(notification);
                        greenApi.greenApiReceive.deleteNotification(notification.getReceiptId());
                    }
                }
            } catch (Exception e) {
                log.error("WebhookConsumer exception: {}", String.valueOf(e));
                Thread.sleep(5000);
            }
        }
    }

    public void stop() {
        if (running) {
            running = false;
        } else {
            log.warn("WebhookConsumer already stopped");
        }
    }
}

