package kz.mediation.api.greenapi.model_examples.notification.message.quotedMessageData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.mediation.api.greenapi.model_examples.ChatContact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactsArrayQuotedMessageData extends QuotedMessageData {
    private List<ChatContact> contacts;
    private Boolean isForwarded;
    private Integer forwardingScore;
}
