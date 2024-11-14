package kz.mediation.api.mediation_executor.repository;

import kz.mediation.api.mediation_executor.model.MediationStage;
import kz.mediation.api.mediation_executor.util.enums.MediationStageEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediationStageRepository extends JpaRepository<MediationStage, Long> {
    List<MediationStage> findByMediationStage(MediationStageEnum mediationStage);
}
