package kz.mediation.api.greenapi.model_examples.notification;

import kz.mediation.api.greenapi.model_examples.notification.message.ContactMessage;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactMessageWebhook extends MessageWebhook {
    private ContactMessage messageData;
}
