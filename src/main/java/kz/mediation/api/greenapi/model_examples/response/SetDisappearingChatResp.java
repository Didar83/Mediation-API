package kz.mediation.api.greenapi.model_examples.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetDisappearingChatResp {
    private String chatId;
    private Boolean disappearingMessagesInChat;
    private Long ephemeralExpiration;
}

