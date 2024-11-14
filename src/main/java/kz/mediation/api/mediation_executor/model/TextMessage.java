package kz.mediation.api.mediation_executor.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TextMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 4096)
    private String textMessage;
    private String typeWebhook;
    private Long timestamp;
    private String idMessage;
    private String chatId;
    private String chatName;
    private String sender;
    private String senderName;
    private String senderContactName;
    private String typeMessage;
}
