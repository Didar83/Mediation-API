package kz.mediation.api.greenapi.model_examples.notification.message.messageData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileMessageData {
    private String downloadUrl;
    private String caption;
    private String fileName;
    private String jpegThumbnail;
    private String mimeType;
    private String isForwarded;
    private Integer forwardingScore;
}
