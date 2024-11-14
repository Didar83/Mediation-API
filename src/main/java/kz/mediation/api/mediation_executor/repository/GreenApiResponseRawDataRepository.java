package kz.mediation.api.mediation_executor.repository;

import kz.mediation.api.mediation_executor.model.GreenApiResponseRawData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenApiResponseRawDataRepository extends JpaRepository<GreenApiResponseRawData, Long> {
}
