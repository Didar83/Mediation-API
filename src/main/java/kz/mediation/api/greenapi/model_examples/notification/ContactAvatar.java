package kz.mediation.api.greenapi.model_examples.notification;

import kz.mediation.api.greenapi.model_examples.notification.message.AvatarInfo;
import kz.mediation.api.greenapi.model_examples.notification.message.InstanceData;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactAvatar extends NotificationBody {
    private InstanceData instanceData;
    private Long timestamp;
    private AvatarInfo avatarInfo;
}
