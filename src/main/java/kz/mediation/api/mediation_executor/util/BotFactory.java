package kz.mediation.api.mediation_executor.util;

import kz.mediation.api.mediation_executor.config.GreenApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class BotFactory {
    private final RestTemplate restTemplate;
    private final StateManager stateManager;
    @Value("${Green-API.hostMedia}")
    private String hostMedia;
    @Value("${Green-API.host}")
    private String host;

    public Bot createBot(String instanceId,
                         String instanceToken,
                         WebhookHandlerAbstract handler,
                         Boolean cleanNotificationQueue) {
        var greenApi = new GreenApi(restTemplate, hostMedia, host, instanceId, instanceToken);
        var notificationMapper = new NotificationMapper();
        var webhookConsumer = new WebhookConsumer(greenApi, notificationMapper);
        handler.greenApi = greenApi;
        handler.stateManager = stateManager;
        return Bot.builder()
                .greenApi(greenApi)
                .webhookHandler(handler)
                .notificationMapper(notificationMapper)
                .webhookConsumer(webhookConsumer)
                .cleanNotificationQueue(cleanNotificationQueue)
                .stateManager(stateManager)
                .build();
    }

    public Bot createBot(String instanceId, String instanceToken, WebhookHandlerAbstract handler) {
        return createBot(instanceId, instanceToken, handler, true);
    }

    public Bot createBot(String instanceId, String instanceToken) {
        return createBot(instanceId, instanceToken, new DefaultWebhookHandlerImpl(), true);
    }
}

