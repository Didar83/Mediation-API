package kz.mediation.api.greenapi.model_examples.notification.message.messageData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class TextMessageData {
    private String textMessage;
    private Boolean isTemplateMessage;
}
