package kz.mediation.api.greenapi.model_examples.notification.message.quotedMessageData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kz.mediation.api.mediation_executor.util.QuotedMessageDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = QuotedMessageDeserializer.class)
public abstract class QuotedMessageData {
    private String typeMessage;
    private String stanzaId;
    private String participant;
}
