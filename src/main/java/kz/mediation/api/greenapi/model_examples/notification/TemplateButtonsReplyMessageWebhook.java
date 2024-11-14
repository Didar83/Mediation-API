package kz.mediation.api.greenapi.model_examples.notification;

import kz.mediation.api.greenapi.model_examples.notification.message.quotedMessageData.TemplateButtonReplyQuotedMessageData;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateButtonsReplyMessageWebhook extends MessageWebhook {
    private TemplateButtonReplyQuotedMessageData messageData;
}
