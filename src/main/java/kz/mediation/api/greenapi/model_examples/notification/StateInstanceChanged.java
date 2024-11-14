package kz.mediation.api.greenapi.model_examples.notification;

import kz.mediation.api.greenapi.model_examples.notification.message.InstanceData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StateInstanceChanged extends NotificationBody {
    private InstanceData instanceData;
    private Long timestamp;
    private String statusInstance;
}
