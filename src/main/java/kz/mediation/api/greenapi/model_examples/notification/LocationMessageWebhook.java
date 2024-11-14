package kz.mediation.api.greenapi.model_examples.notification;

import kz.mediation.api.greenapi.model_examples.notification.message.LocationMessage;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationMessageWebhook extends MessageWebhook {
    private LocationMessage messageData;
}
