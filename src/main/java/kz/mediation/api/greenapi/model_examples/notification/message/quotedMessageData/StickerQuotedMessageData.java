package kz.mediation.api.greenapi.model_examples.notification.message.quotedMessageData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StickerQuotedMessageData extends QuotedMessageData {
    private String downloadUrl;
    private Boolean isAnimated;
    private String jpegThumbnail;
    private String mimeType;
    private Boolean isForwarded;
    private Integer forwardingScore;
}
