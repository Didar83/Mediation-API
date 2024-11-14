package kz.mediation.api.greenapi.model_examples.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import kz.mediation.api.greenapi.model_examples.Contact;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutgoingContact extends Outgoing {
    private final Contact contact;
}
