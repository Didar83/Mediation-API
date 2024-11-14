package kz.mediation.api.mediation_executor.service.method;

import kz.mediation.api.greenapi.model_examples.ChatHistoryMessage;
import kz.mediation.api.greenapi.model_examples.request.GetChatHistoryReq;
import kz.mediation.api.greenapi.model_examples.request.MessageReq;
import kz.mediation.api.greenapi.model_examples.response.ChatMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
public class GreenApiJournal {
    private String host;
    private String instanceId;
    private String instanceToken;
    private RestTemplate restTemplate;

    public ResponseEntity<List<ChatHistoryMessage>> getChatHistory(GetChatHistoryReq getChatHistoryReq) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/getChatHistory/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(getChatHistoryReq, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<>() {
        });
    }

    public ResponseEntity<ChatMessage> getMessage(MessageReq messageReq) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/getMessage/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(messageReq, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, ChatMessage.class);
    }

    public ResponseEntity<List<ChatMessage>> lastIncomingMessages(Integer minutes) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/lastIncomingMessages/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(minutes, headers);
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<>() {
        });
    }

    public ResponseEntity<List<ChatMessage>> lastOutgoingMessages(Integer minutes) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/lastOutgoingMessages/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(minutes, headers);
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<>() {
        });
    }
}

