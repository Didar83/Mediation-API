package kz.mediation.api.greenapi.model_examples.notification.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstanceData {
    //todo: remove this class
    private Long idInstance;
    private String wid;
    private String typeInstance;
}
