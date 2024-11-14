package kz.mediation.api.mediation_executor.model;

import jakarta.persistence.*;
import kz.mediation.api.mediation_executor.util.enums.LanguageEnum;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String sender;
    private String senderName;
    @Enumerated(EnumType.STRING)
    private LanguageEnum language;

}

//        "senderData": {
//            "chatId": "77056688119@c.us",
//            "chatName": "Дидар Акулов",
//            "sender": "77056688119@c.us",
//            "senderName": "Didar",
//            "senderContactName": "Дидар Акулов"
//        }
