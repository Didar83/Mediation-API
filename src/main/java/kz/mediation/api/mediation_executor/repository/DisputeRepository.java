package kz.mediation.api.mediation_executor.repository;

import kz.mediation.api.mediation_executor.model.Dispute;
import kz.mediation.api.mediation_executor.model.MediationStage;
import kz.mediation.api.mediation_executor.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DisputeRepository extends JpaRepository<Dispute, Long> {
    @Query("SELECT d FROM Dispute d WHERE d.disputeInitiator = ?1 OR d.disputeRespondent = ?1")
    List<Dispute> findByDisputeByPerson(Person person);

    List<Dispute> findByDisputeInitiator(Person person);

    List<Dispute> findByDisputeRespondent(Person person);

    Optional<Dispute> findByCodeIdentification(UUID codeIdentification);

    Optional<Dispute> findByMediationStage(MediationStage mediationStage);
//    @Modifying
//    Optional<Dispute>  save(Dispute dispute);
}
