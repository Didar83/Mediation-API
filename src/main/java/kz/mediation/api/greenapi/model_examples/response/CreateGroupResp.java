package kz.mediation.api.greenapi.model_examples.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateGroupResp {
    private Boolean created;
    private String chatId;
    private String groupInviteLink;
}

