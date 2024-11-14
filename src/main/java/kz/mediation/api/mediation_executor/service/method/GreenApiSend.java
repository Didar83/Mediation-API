package kz.mediation.api.mediation_executor.service.method;


import kz.mediation.api.greenapi.model_examples.request.*;
import kz.mediation.api.greenapi.model_examples.response.SendFileByUploadResp;
import kz.mediation.api.greenapi.model_examples.response.SendMessageResp;
import kz.mediation.api.greenapi.model_examples.response.UploadFileResp;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@AllArgsConstructor
@Log4j2
public class GreenApiSend {
    private String host;
    private String hostMedia;
    private String instanceId;
    private String instanceToken;
    private RestTemplate restTemplate;

    public ResponseEntity<SendMessageResp> sendMessage(OutgoingMessage message) {
        String url = host +
                "/waInstance"
                + instanceId +
                "/sendMessage/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(message, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SendMessageResp.class);
    }

    public ResponseEntity<SendMessageResp> sendButtons(OutgoingButton buttons) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/sendButtons/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(buttons, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SendMessageResp.class);
    }

    public ResponseEntity<SendMessageResp> sendTemplateButtons(OutgoingTemplateButton buttons) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/sendTemplateButtons/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(buttons, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SendMessageResp.class);
    }

    public ResponseEntity<SendMessageResp> sendListMessage(OutgoingListMessage dto) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/sendListMessage/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(dto, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SendMessageResp.class);
    }

    public ResponseEntity<SendMessageResp> sendContact(OutgoingContact contact) {
        String url = host +
                "/waInstance" + instanceId +
                "/sendContact/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(contact, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SendMessageResp.class);
    }

    public ResponseEntity<SendFileByUploadResp> sendFileByUpload(OutgoingFileByUpload dto) {
        String url = hostMedia +
                "/waInstance" +
                instanceId +
                "/sendFileByUpload/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        var form = new LinkedMultiValueMap<>();
        form.add("chatId", dto.getChatId());
        form.add("file", new FileSystemResource(dto.getFile()));
        form.add("fileName", dto.getFileName());
        form.add("caption", dto.getCaption());
        form.add("quotedMessageId", dto.getQuotedMessageId());
        var requestEntity = new HttpEntity<>(form, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SendFileByUploadResp.class);
    }

    public ResponseEntity<SendMessageResp> sendFileByUrl(OutgoingFileByUrl fileByUrl) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/sendFileByUrl/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(fileByUrl, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SendMessageResp.class);
    }

    public ResponseEntity<UploadFileResp> uploadFile(File file) throws IOException {
        String url = hostMedia +
                "/waInstance" +
                instanceId +
                "/uploadFile/" +
                instanceToken;
        var byteArrayResource = new ByteArrayResource(Files.readAllBytes(file.toPath()));
        var headers = new HttpHeaders();
        headers.setContentType(MediaTypeFactory.getMediaType(file.getName())
                .orElse(MediaType.APPLICATION_OCTET_STREAM));
        var requestEntity = new HttpEntity<>(byteArrayResource, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, UploadFileResp.class);
    }

    public ResponseEntity<SendMessageResp> sendLocation(OutgoingLocation location) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/sendLocation/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(location, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SendMessageResp.class);
    }

    public ResponseEntity<SendMessageResp> sendPoll(OutgoingPoll poll) {
        var url = host +
                "/waInstance" +
                instanceId +
                "/sendPoll/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(poll, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SendMessageResp.class);
    }
}

