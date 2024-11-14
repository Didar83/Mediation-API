package kz.mediation.api.greenapi.model_examples.notification.message;

import kz.mediation.api.greenapi.model_examples.notification.message.messageData.QuotedDataReaction;
import kz.mediation.api.greenapi.model_examples.notification.message.messageData.ReactionMessageData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactionMessage {
    private String typeMessage;
    private ReactionMessageData extendedTextMessageData;
    private QuotedDataReaction quotedMessage;
}
