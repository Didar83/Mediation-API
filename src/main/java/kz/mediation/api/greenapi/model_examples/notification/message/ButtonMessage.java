package kz.mediation.api.greenapi.model_examples.notification.message;

import kz.mediation.api.greenapi.model_examples.notification.message.messageData.ButtonMessageData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ButtonMessage {
    private String typeMessage;
    private ButtonMessageData buttonMessageData;
}
