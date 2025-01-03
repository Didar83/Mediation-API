package kz.mediation.api.greenapi.model_examples.notification.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvatarInfo {
    private Boolean chatId;
    private Boolean existsWhatsapp;
    private String urlAvatar;
    private String reason;
}
