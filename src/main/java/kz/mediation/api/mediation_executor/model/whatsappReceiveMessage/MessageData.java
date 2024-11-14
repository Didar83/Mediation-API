package kz.mediation.api.mediation_executor.model.whatsappReceiveMessage;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonTypeName("messageData")
public class MessageData {
    private String typeMessage;
    private MessageDataType messageDataType;
}
