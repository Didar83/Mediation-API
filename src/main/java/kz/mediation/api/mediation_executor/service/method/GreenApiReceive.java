package kz.mediation.api.mediation_executor.service.method;

import kz.mediation.api.greenapi.model_examples.request.MessageReq;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public class GreenApiReceive {
    private final String host;
    private final String instanceId;
    private final String instanceToken;
    private final RestTemplate restTemplate;

    public ResponseEntity<String> receiveNotification() {
        String url = host +
                "/waInstance" +
                instanceId +
                "/receiveNotification/" +
                instanceToken;
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }

    public ResponseEntity<String> deleteNotification(Integer receiptId) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/deleteNotification/" +
                instanceToken +
                "/" + receiptId;
        return restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
    }

    public ResponseEntity<byte[]> downloadFile(MessageReq messageReq) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/downloadFile/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(messageReq, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
    }
}
