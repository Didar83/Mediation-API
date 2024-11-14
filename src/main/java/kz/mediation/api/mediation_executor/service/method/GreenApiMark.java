package kz.mediation.api.mediation_executor.service.method;

import kz.mediation.api.greenapi.model_examples.request.MessageReq;
import kz.mediation.api.greenapi.model_examples.response.ReadChatResp;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public class GreenApiMark {
    private String host;
    private String instanceId;
    private String instanceToken;
    private RestTemplate restTemplate;

    public ResponseEntity<ReadChatResp> readChat(MessageReq messageReq) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/readChat/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(messageReq, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, ReadChatResp.class);
    }
}
