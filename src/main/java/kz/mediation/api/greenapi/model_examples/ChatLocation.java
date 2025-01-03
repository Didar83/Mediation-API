package kz.mediation.api.greenapi.model_examples;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatLocation {
    private String nameLocation;
    private String address;
    private Double latitude;
    private Double longitude;
    private String jpegThumbnail;
}
