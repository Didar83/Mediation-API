package kz.mediation.api.mediation_executor.model.whatsappReceiveMessage;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class InstanceData {
    private Long idInstance;
    private String wid;
    private String typeInstance;
}
