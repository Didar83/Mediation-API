package kz.mediation.api.greenapi.model_examples.notification.message.messageData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.mediation.api.greenapi.model_examples.TemplateButton;
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
public class TemplateMessageData {
    private String namespace;
    private String elementName;
    private String contentText;
    private String footer;
    private List<TemplateButton> buttons;
    private boolean isForwarded;
    private Long forwardingScore;
}
