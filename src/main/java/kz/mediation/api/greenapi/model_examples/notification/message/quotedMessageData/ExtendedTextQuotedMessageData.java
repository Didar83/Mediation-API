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
public class ExtendedTextQuotedMessageData extends QuotedMessageData {
    private String text;
    private String description;
    private String title;
    private String jpegThumbnail;
    private String isForwarded;
    private Integer forwardingScore;
    private String previewType;
    private Boolean containsAutoReply;
    private String mediaType;
    private Boolean showAdAttribution;
    private String sourceId;
    private String sourceType;
    private String sourceUrl;
    private String thumbnailUrl;
}

