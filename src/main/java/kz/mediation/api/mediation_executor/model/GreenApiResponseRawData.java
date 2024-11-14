package kz.mediation.api.mediation_executor.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GreenApiResponseRawData {
    private final LocalDateTime messageSentAt = LocalDateTime.now();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 4096)
    private String textMessage;
    private String typeWebhook;
}
