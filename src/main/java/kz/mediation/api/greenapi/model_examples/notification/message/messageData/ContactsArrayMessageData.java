package kz.mediation.api.greenapi.model_examples.notification.message.messageData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.mediation.api.greenapi.model_examples.ChatContact;
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
public class ContactsArrayMessageData {
    private List<ChatContact> contacts;
    private Boolean isForwarded;
    private Integer forwardingScore;
}
