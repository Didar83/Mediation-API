package kz.mediation.api.greenapi.model_examples.notification.message.quotedMessageData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.mediation.api.greenapi.model_examples.TemplateButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateQuotedMessageData extends QuotedMessageData {
    private String namespace;
    private String elementName;
    private String contentText;
    private String footer;
    private List<TemplateButton> buttons;
    private boolean isForwarded;
    private Long forwardingScore;
}

