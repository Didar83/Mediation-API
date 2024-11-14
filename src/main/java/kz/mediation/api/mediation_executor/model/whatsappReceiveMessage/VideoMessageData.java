package kz.mediation.api.mediation_executor.model.whatsappReceiveMessage;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonTypeName("videoMessage")
@Builder
public class VideoMessageData implements MessageDataType {
    private String downloadUrl;
    private String caption;
    private String fileName;
    private String jpegThumbnail;
    private Boolean isAnimated;
    private String mimeType;
    private Integer forwardingScore;
    private Boolean isForwarded;
}
