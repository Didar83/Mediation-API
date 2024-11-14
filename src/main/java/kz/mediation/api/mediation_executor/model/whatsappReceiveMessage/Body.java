package kz.mediation.api.mediation_executor.model.whatsappReceiveMessage;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonTypeName("body")
@Builder
public class Body {
    private String typeWebhook;
    private InstanceData instanceData;
    private Long timestamp;
    private String idMessage;
    private SenderData senderData;
    private MessageData messageData;
}
