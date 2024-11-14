package kz.mediation.api.mediation_executor.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Dispute {
    @Column(unique = true)
    private final UUID codeIdentification = UUID.randomUUID();
    private final LocalDateTime disputeCreatedAt = LocalDateTime.now();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Person disputeInitiator;
    @ManyToOne
    private Person disputeRespondent;
    private LocalDateTime disputeClosedAt;
    @Column(length = 256)
    private String disputeSubject; //gpt
    @Column(length = 4096)
    private String initiatorPosition; //gpt
    @Column(length = 4096)
    private String respondentPosition; //gpt
    @Column(length = 4096)
    private String decision; //gpt
    @Column(length = 4096)
    private String decisionAnotherLanguage; //gpt
    @OneToOne(cascade = CascadeType.ALL)
    private MediationStage mediationStage;
    private boolean isActive;
}
