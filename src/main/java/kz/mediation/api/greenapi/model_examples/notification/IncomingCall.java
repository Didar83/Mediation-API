package kz.mediation.api.greenapi.model_examples.notification;

import kz.mediation.api.greenapi.model_examples.notification.message.InstanceData;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomingCall extends NotificationBody {
    private InstanceData instanceData;
    private Long timestamp;
    private String idMessage;
    private String from;
    private String status;
}
