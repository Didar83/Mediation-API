package kz.mediation.api.mediation_executor.model.whatsappReceiveMessage;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonTypeName("extendedTextMessageData")
@Builder
public class ExtendedTextMessageData implements MessageDataType {
    private String text;
    private String description;
    private String title;
    private String previewType;
    private String jpegThumbnail;
    private int forwardingScore;
    private boolean isForwarded;
}
