package kz.mediation.api.mediation_executor.service.method;

import kz.mediation.api.greenapi.model_examples.QueueMessage;
import kz.mediation.api.greenapi.model_examples.response.ClearMessagesQueueResp;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
public class GreenApiQueue {
    private String host;
    private String instanceId;
    private String token;
    private RestTemplate restTemplate;

    public ResponseEntity<List<QueueMessage>> showMessagesQueue() {
        String url = host +
                "/waInstance" +
                instanceId +
                "/showMessagesQueue/" +
                token;
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
    }

    public ResponseEntity<ClearMessagesQueueResp> clearMessagesQueue() {
        String url = host +
                "/waInstance" +
                instanceId +
                "/clearMessagesQueue/" +
                token;
        return restTemplate.exchange(url, HttpMethod.GET, null, ClearMessagesQueueResp.class);
    }
}

