package kz.mediation.api.mediation_executor.service.method;

import kz.mediation.api.greenapi.model_examples.request.MessageReq;
import kz.mediation.api.greenapi.model_examples.response.*;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
public class GreenApiService {
    private String host;
    private String instanceId;
    private String instanceToken;
    private RestTemplate restTemplate;

    public ResponseEntity<CheckWhatsAppResp> checkWhatsapp(Long phoneNumber) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/checkWhatsapp/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestBody = new HashMap<String, Long>();
        requestBody.put("phoneNumber", phoneNumber);
        var requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, CheckWhatsAppResp.class);
    }

    public ResponseEntity<GetAvatarResp> getAvatar(String chatId) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/getAvatar/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestBody = new HashMap<String, String>();
        requestBody.put("chatId", chatId);
        var requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, GetAvatarResp.class);
    }

    public ResponseEntity<List<GetContactsResp>> getContacts() {
        String url = host +
                "/waInstance" +
                instanceId +
                "/getContacts/" +
                instanceToken;
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
    }

    public ResponseEntity<GetContactInfoResp> getContactInfo(String chatId) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/getContactInfo/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestBody = new HashMap<String, String>();
        requestBody.put("chatId", chatId);
        var requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, GetContactInfoResp.class);
    }

    public ResponseEntity<String> deleteMessage(MessageReq messageReq) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/deleteMessage/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(messageReq, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }

    public ResponseEntity<String> archiveChat(String chatId) {
        var url = new StringBuilder();
        url
                .append(host)
                .append("/waInstance").append(instanceId)
                .append("/archiveChat/")
                .append(instanceToken);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestBody = new HashMap<String, String>();
        requestBody.put("chatId", chatId);
        var requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(url.toString(), HttpMethod.POST, requestEntity, String.class);
    }

    public ResponseEntity<String> unarchiveChat(String chatId) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/unarchiveChat/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestBody = new HashMap<String, String>();
        requestBody.put("chatId", chatId);
        var requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }

    public ResponseEntity<SetDisappearingChatResp> setDisappearingChat(String chatId, Long ephemeralExpiration) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/setDisappearingChat/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestBody = new HashMap<>();
        requestBody.put("chatId", chatId);
        requestBody.put("ephemeralExpiration", ephemeralExpiration);
        var requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SetDisappearingChatResp.class);
    }
}
