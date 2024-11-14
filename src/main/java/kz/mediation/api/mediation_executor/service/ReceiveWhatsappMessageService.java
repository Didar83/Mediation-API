package kz.mediation.api.mediation_executor.service;

import kz.mediation.api.mediation_executor.config.GreenApi;
import kz.mediation.api.mediation_executor.model.whatsappReceiveMessage.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiveWhatsappMessageService {
    private final MessageProcessService messageProcessService;
    private final GreenApi greenApi;


    public void handle(Root root) {
        messageProcessService.proceed(root);
        greenApi.greenApiReceive.deleteNotification(root.getReceiptId());
    }
}
