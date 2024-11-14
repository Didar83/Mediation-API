package kz.mediation.api.greenapi.model_examples.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import kz.mediation.api.greenapi.model_examples.Button;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutgoingButton extends Outgoing {
    private final String message;
    private final String footer;
    private final List<Button> buttons;
}
