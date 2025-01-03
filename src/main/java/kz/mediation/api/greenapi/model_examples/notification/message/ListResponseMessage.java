package kz.mediation.api.greenapi.model_examples.notification.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListResponseMessage {
    private String typeMessage;
    private ListResponseMessageData listResponseMessage;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class ListResponseMessageData {
        private String title;
        private String listType;
        private String singleSelectReply;
        private String stanzaId;
    }
}
