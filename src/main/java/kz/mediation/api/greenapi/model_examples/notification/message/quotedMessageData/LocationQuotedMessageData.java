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
public class LocationQuotedMessageData extends QuotedMessageData {
    private Double latitude;
    private Double longitude;
    private String jpegThumbnail;
    private Boolean isForwarded;
    private Integer forwardingScore;
    private String nameLocation;
    private String address;
}
