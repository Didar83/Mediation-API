package kz.mediation.api.greenapi.model_examples.notification;

import kz.mediation.api.greenapi.model_examples.notification.message.InstanceData;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutgoingMessageStatus extends NotificationBody {
    private String chatId;
    private InstanceData instanceData;
    private Long timestamp;
    private String idMessage;
    private String status;
    private Boolean sendByApi;
    private String description;
}
