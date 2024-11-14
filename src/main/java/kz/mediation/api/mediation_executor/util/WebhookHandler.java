package kz.mediation.api.mediation_executor.util;

import kz.mediation.api.greenapi.model_examples.notification.Notification;

public interface WebhookHandler {
    void handle(Notification notification);
}
