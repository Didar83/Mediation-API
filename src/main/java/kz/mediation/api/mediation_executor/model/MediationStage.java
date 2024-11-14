package kz.mediation.api.mediation_executor.model;

import jakarta.persistence.*;
import kz.mediation.api.mediation_executor.util.enums.MediationStageEnum;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MediationStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MediationStageEnum mediationStage;
//    @Enumerated(EnumType.STRING)
//    private MediationStageEnum initiatorMediationStage;
//    @Enumerated(EnumType.STRING)
//    private MediationStageEnum respondentMediationStage;
}
