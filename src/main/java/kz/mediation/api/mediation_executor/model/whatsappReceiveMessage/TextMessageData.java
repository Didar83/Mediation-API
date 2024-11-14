package kz.mediation.api.mediation_executor.model.whatsappReceiveMessage;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonTypeName("textMessage")
@Builder
public class TextMessageData implements MessageDataType {
    private String textMessage;
}
