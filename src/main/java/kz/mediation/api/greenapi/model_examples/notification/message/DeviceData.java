package kz.mediation.api.greenapi.model_examples.notification.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceData {
    private String platform;
    private String deviceManufacturer;
    private String deviceModel;
    private String osVersion;
    private String waVersion;
    private Integer battery;
}

