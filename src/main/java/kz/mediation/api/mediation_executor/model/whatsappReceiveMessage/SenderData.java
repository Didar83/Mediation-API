package kz.mediation.api.mediation_executor.model.whatsappReceiveMessage;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonTypeName("senderData")
@Builder
public class SenderData {
    private String chatId;
    private String chatName;
    private String sender;
    private String senderName;
    private String senderContactName;
}
