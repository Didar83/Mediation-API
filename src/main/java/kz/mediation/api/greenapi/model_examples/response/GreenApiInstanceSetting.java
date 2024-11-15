package kz.mediation.api.greenapi.model_examples.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GreenApiInstanceSetting {
    private String wid;
    private String webhookUrl;
    private Long delaySendMessagesMilliseconds;
    private String markIncomingMessagesReaded;
    private String markIncomingMessagesReadedOnReply;
    private String sharedSession;
    private String proxyInstance;
    private String outgoingWebhook;
    private String outgoingMessageWebhook;
    private String outgoingAPIMessageWebhook;
    private String incomingWebhook;
    private String deviceWebhook;
    private String statusInstanceWebhook;
    private String stateWebhook;
    private String enableMessagesHistory;
    private String keepOnlineStatus;
    private String pollMessageWebhook;
    private String incomingBlockWebhook;
}
