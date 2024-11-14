package kz.mediation.api.greenapi.model_examples.notification.message;

import kz.mediation.api.greenapi.model_examples.notification.message.messageData.ExtendedTextMessageDataOld;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuotedMessageOld<T> {
    private String typeMessage;
    private ExtendedTextMessageDataOld extendedTextMessageData;
    private T quotedMessage;
}
