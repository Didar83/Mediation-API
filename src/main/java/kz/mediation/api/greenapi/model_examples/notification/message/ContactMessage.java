package kz.mediation.api.greenapi.model_examples.notification.message;

import kz.mediation.api.greenapi.model_examples.notification.message.messageData.ContactMessageData;
import kz.mediation.api.greenapi.model_examples.notification.message.quotedMessageData.QuotedMessageData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactMessage {
    private String typeMessage;
    private ContactMessageData contactMessageData;
    private QuotedMessageData quotedMessage;
}
