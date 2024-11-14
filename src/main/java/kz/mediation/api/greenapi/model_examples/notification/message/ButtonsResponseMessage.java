package kz.mediation.api.greenapi.model_examples.notification.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ButtonsResponseMessage {
    private String typeMessage;
    private ButtonsResponseMessageData buttonsResponseMessage;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class ButtonsResponseMessageData {
        private String selectedButtonId;
        private String selectedButtonText;
        private String stanzaId;
    }
}
