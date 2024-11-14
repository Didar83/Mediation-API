package kz.mediation.api.greenapi.model_examples.notification.message.quotedMessageData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupInviteQuotedMessageData extends QuotedMessageData {
    private String groupJid;
    private String inviteCode;
    private String inviteExpiration;
    private String groupName;
    private String caption;
    private Boolean name;
    private Integer jpegThumbnail;
}
