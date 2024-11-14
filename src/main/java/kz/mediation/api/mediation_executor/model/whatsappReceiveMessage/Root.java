package kz.mediation.api.mediation_executor.model.whatsappReceiveMessage;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Root {
    private int receiptId;
    private Body body;
}
