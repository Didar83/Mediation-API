package kz.mediation.api.greenapi.model_examples.notification;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    private Integer receiptId;
    private NotificationBody body;
}
