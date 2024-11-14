package kz.mediation.api.greenapi.model_examples.notification.message;

import kz.mediation.api.greenapi.model_examples.notification.message.messageData.StickerMessageData;
import kz.mediation.api.greenapi.model_examples.notification.message.quotedMessageData.QuotedMessageData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StickerMessage {
    private String typeMessage;
    private StickerMessageData fileMessageData;
    private QuotedMessageData quotedMessage;
}

