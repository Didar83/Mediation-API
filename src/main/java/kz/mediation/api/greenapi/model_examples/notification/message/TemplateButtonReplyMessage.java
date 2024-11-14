package kz.mediation.api.greenapi.model_examples.notification.message;

import kz.mediation.api.greenapi.model_examples.notification.message.quotedMessageData.TemplateButtonReplyQuotedMessageData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateButtonReplyMessage {
    private String typeMessage;
    private TemplateButtonReplyQuotedMessageData templateMessage;
}

