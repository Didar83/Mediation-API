package kz.mediation.api.mediation_executor.controller;

import kz.mediation.api.mediation_executor.config.GreenApi;
import kz.mediation.api.mediation_executor.model.whatsappReceiveMessage.Root;
import kz.mediation.api.mediation_executor.service.ReceiveWhatsappMessageService;
import kz.mediation.api.mediation_executor.service.mapper.MediationNotificationMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MediationReceiveMessageController {
    private final GreenApi greenApi;
    private final MediationNotificationMapper mediationNotificationMapper;
    private final ReceiveWhatsappMessageService receiveWhatsappMessageService;

    @Scheduled(initialDelay = 10000, fixedDelay = 5000)
    @SneakyThrows
    public void whatsappApiLongPolling() {
        try {
            var response = greenApi.greenApiReceive.receiveNotification();
            if (response.hasBody() && response.getBody() != null) {
                Root root = mediationNotificationMapper.getWhatsappMessage(response.getBody());
                receiveMessage(root);
            }
        } catch (Exception e) {
            log.error("whatsappApiLongPolling exception: {}", String.valueOf(e));
            Thread.sleep(5000);
        }
    }

    private void receiveMessage(Root root) {
        if (root.getBody() != null) {
            receiveWhatsappMessageService.handle(root);
        } else {
            greenApi.greenApiReceive.deleteNotification(root.getReceiptId());
        }
    }

}
