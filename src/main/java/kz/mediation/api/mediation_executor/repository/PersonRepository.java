package kz.mediation.api.mediation_executor.repository;

import kz.mediation.api.mediation_executor.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findBySender(String sender);
}
