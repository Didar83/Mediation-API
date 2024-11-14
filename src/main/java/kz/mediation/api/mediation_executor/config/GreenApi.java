package kz.mediation.api.mediation_executor.config;

import kz.mediation.api.mediation_executor.service.method.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GreenApi {
    public GreenApiAccount greenApiAccount;
    public GreenApiSend greenApiSend;
    public GreenApiJournal greenApiJournal;
    public GreenApiGroup greenApiGroup;
    public GreenApiQueue greenApiQueue;
    public GreenApiMark greenApiMark;
    public GreenApiReceive greenApiReceive;
    public GreenApiService greenApiService;

    @Autowired
    public GreenApi(RestTemplate restTemplate,
                    @Value("${Green-API.hostMedia}") String hostMedia,
                    @Value("${Green-API.host}") String host,
                    @Value("${Green-API.instanceId}") String instanceId,
                    @Value("${Green-API.instanceToken}") String instanceToken) {
        this.greenApiAccount = new GreenApiAccount(host, instanceId, instanceToken, restTemplate);
        this.greenApiSend = new GreenApiSend(host, hostMedia, instanceId, instanceToken, restTemplate);
        this.greenApiJournal = new GreenApiJournal(host, instanceId, instanceToken, restTemplate);
        this.greenApiGroup = new GreenApiGroup(host, instanceId, instanceToken, restTemplate);
        this.greenApiQueue = new GreenApiQueue(host, instanceId, instanceToken, restTemplate);
        this.greenApiMark = new GreenApiMark(host, instanceId, instanceToken, restTemplate);
        this.greenApiReceive = new GreenApiReceive(host, instanceId, instanceToken, restTemplate);
        this.greenApiService = new GreenApiService(host, instanceId, instanceToken, restTemplate);
    }
}
