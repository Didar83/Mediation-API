package kz.mediation.api.greenapi.model_examples.notification.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SenderData {
    private String chatId;
    private String sender;
    private String chatName;
    private String senderName;
    private String senderContactName;
}
