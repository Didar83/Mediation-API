package kz.mediation.api.greenapi.model_examples.notification.message;

import kz.mediation.api.greenapi.model_examples.notification.message.messageData.TextMessageData;
import kz.mediation.api.greenapi.model_examples.notification.message.quotedMessageData.QuotedMessageData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextMessage {
    private String typeMessage;
    private TextMessageData textMessageData;
    private QuotedMessageData quotedMessage;
}

