package kz.mediation.api.greenapi.model_examples.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WhatsappAccountSetting {
    private String avatar;
    private String phone;
    private String stateInstance;
    private String deviceId;
}
