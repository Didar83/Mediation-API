package kz.mediation.api.greenapi.model_examples;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {
    private Long phoneContact;
    private String firstName;
    private String middleName;
    private String lastName;
    private String company;
}
