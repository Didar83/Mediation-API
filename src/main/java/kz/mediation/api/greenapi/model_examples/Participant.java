package kz.mediation.api.greenapi.model_examples;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Participant {
    private String id;
    private Boolean isAdmin;
    private Boolean isSuperAdmin;
}
