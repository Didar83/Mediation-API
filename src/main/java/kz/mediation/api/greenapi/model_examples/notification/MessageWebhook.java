package kz.mediation.api.greenapi.model_examples.notification;

import kz.mediation.api.greenapi.model_examples.notification.message.InstanceData;
import kz.mediation.api.greenapi.model_examples.notification.message.SenderData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@SuperBuilder
public abstract class MessageWebhook extends NotificationBody {
    private InstanceData instanceData;
    private Long timestamp;
    private String idMessage;
    private SenderData senderData;
}

