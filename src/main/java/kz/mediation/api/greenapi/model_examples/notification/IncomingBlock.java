package kz.mediation.api.greenapi.model_examples.notification;

import kz.mediation.api.greenapi.model_examples.notification.message.InstanceData;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomingBlock extends NotificationBody {
    private InstanceData instanceData;
    private Long timestamp;
    private String chatId;
    private String chatState;
}

