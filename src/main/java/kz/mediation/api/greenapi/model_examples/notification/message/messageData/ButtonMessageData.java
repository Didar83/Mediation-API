package kz.mediation.api.greenapi.model_examples.notification.message.messageData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ButtonMessageData {
    private String message;
    private String footer;
    private List<Button> buttons;
    private String chatId;
    private String quotedMessageId;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Button {
        private Integer buttonId;
        private String buttonText;
    }
}
