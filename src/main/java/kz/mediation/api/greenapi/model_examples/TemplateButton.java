package kz.mediation.api.greenapi.model_examples;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemplateButton {
    private Integer index;
    private UrlButton urlButton;
    private CallButton callButton;
    private QuickReplyButton quickReplyButton;
}
