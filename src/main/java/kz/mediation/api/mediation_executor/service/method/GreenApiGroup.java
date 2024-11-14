package kz.mediation.api.mediation_executor.service.method;

import kz.mediation.api.greenapi.model_examples.request.ChangeGroupNameReq;
import kz.mediation.api.greenapi.model_examples.request.ChangeGroupPictureReq;
import kz.mediation.api.greenapi.model_examples.request.ChangeParticipantReq;
import kz.mediation.api.greenapi.model_examples.request.CreateGroupReq;
import kz.mediation.api.greenapi.model_examples.response.*;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class GreenApiGroup {
    private String host;
    private String instanceId;
    private String instanceToken;
    private RestTemplate restTemplate;

    public ResponseEntity<CreateGroupResp> createGroup(CreateGroupReq createGroupReq) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/createGroup/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(createGroupReq, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, CreateGroupResp.class);
    }

    public ResponseEntity<ChangeGroupNameResp> updateGroupName(ChangeGroupNameReq dto) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/updateGroupName/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(dto, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, ChangeGroupNameResp.class);
    }

    public ResponseEntity<GroupData> getGroupData(String groupId) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/getGroupData/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("groupId", groupId);
        var requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, GroupData.class);
    }

    public ResponseEntity<AddGroupParticipantResp> addGroupParticipant(ChangeParticipantReq dto) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/addGroupParticipant/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(dto, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, AddGroupParticipantResp.class);
    }

    public ResponseEntity<RemoveGroupParticipantResp> removeGroupParticipant(ChangeParticipantReq dto) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/removeGroupParticipant/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(dto, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, RemoveGroupParticipantResp.class);
    }

    public ResponseEntity<SetGroupAdminResp> setGroupAdmin(ChangeParticipantReq dto) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/setGroupAdmin/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(dto, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SetGroupAdminResp.class);
    }

    public ResponseEntity<RemoveGroupAdminResp> removeGroupAdmin(ChangeParticipantReq dto) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/removeAdmin/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var requestEntity = new HttpEntity<>(dto, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, RemoveGroupAdminResp.class);
    }

    public ResponseEntity<SetGroupPictureResp> setGroupPicture(ChangeGroupPictureReq dto) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/setGroupPicture/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        var form = new LinkedMultiValueMap<>();
        form.add("file", new FileSystemResource(dto.getFile()));
        form.add("groupId", dto.getGroupId());
        var requestEntity = new HttpEntity<>(form, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, SetGroupPictureResp.class);
    }

    public ResponseEntity<LeaveGroupResp> leaveGroup(String groupId) {
        String url = host +
                "/waInstance" +
                instanceId +
                "/leaveGroup/" +
                instanceToken;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("groupId", groupId);
        var requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, LeaveGroupResp.class);
    }
}

