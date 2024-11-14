package kz.mediation.api.mediation_executor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FileMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String downloadUrl;
    private String caption;
    private String fileName;
    private String jpegThumbnail;
    private Boolean isAnimated;
    private String mimeType;
    private Integer forwardingScore;
    private Boolean isForwarded;

    private String typeWebhook; // "imageMessage", "audioMessage", "videoMessage", "documentMessage"
    private Long timestamp;
    private String idMessage;
    private String chatId;
    private String chatName;
    private String sender;
    private String senderName;
    private String senderContactName;
    private String typeMessage;
}
