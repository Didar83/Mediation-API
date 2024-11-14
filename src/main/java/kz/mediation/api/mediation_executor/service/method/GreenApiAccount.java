package kz.mediation.api.mediation_executor.service.method;

import kz.mediation.api.greenapi.model_examples.request.InstanceSettingsReq;
import kz.mediation.api.greenapi.model_examples.response.*;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.HashMap;

@AllArgsConstructor
public class GreenApiAccount {
    private String host;
    private String instanceId;
    private String instanceToken;
    private RestTemplate restTemplate;

    public ResponseEntity<GreenApiInstanceSetting> getSettings() {
        String url = host +
                "/waInstance" +
                instanceId +
                "/getSettings/" +
                instanceToken;
        return restTemplate.exchange(url, HttpMethod.GET, null, GreenApiInstanceSetting.class);
    }

    public ResponseEntity<WhatsappAccountSetting> getWaSettings() {
        String url = host +
                "/waInstance"
                + instanceId +
                "/getWaSettings/" +
                instanceToken;
        return restTemplate.exchange(url, HttpMethod.GET, null, WhatsappAccountSetting.class);
    }

    public ResponseEntity<SetSettingsResp> setSetting(InstanceSettingsReq instanceSettings) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/setSettings/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(instanceSettings, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SetSettingsResp.class);
    }

    public ResponseEntity<StateInstanceResp> getStateInstance() {
        String url = host +
                "/waInstance"
                + instanceId +
                "/getStateInstance/" +
                instanceToken;
        return restTemplate.exchange(url, HttpMethod.GET, null, StateInstanceResp.class);
    }

    public ResponseEntity<StatusInstanceResp> getStatusInstance() {
        String url = host +
                "/waInstance" +
                instanceId +
                "/getStatusInstance/" +
                instanceToken;
        return restTemplate.exchange(url, HttpMethod.GET, null, StatusInstanceResp.class);
    }

    public ResponseEntity<RebootResp> reboot() {
        String url = host +
                "/waInstance" +
                instanceId +
                "/reboot/" +
                instanceToken;
        return restTemplate.exchange(url, HttpMethod.GET, null, RebootResp.class);
    }

    public ResponseEntity<LogoutResp> logout() {
        String url = host +
                "/waInstance" +
                instanceId +
                "/logout/" +
                instanceToken;
        return restTemplate.exchange(url, HttpMethod.GET, null, LogoutResp.class);
    }

    public ResponseEntity<Qr> getQrCode() {
        String url = host +
                "/waInstance" +
                instanceId +
                "/qr/" +
                instanceToken;
        return restTemplate.exchange(url, HttpMethod.GET, null, Qr.class);
    }

    public ResponseEntity<SetProfilePictureResp> setProfilePicture(File file) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/setProfilePicture/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        var form = new LinkedMultiValueMap<>();
        form.add("file", new FileSystemResource(file));
        var requestEntity = new HttpEntity<>(form, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SetProfilePictureResp.class);
    }

    public ResponseEntity<GetAuthorizationCodeResp> getAuthorizationCode(Long phoneNumber) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/GetAuthorizationCode/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var body = new HashMap<>();
        body.put("phoneNumber", phoneNumber);
        var requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, GetAuthorizationCodeResp.class);
    }
}
