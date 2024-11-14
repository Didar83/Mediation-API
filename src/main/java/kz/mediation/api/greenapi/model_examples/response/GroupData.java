package kz.mediation.api.greenapi.model_examples.response;

import kz.mediation.api.greenapi.model_examples.Participant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupData {
    private String groupId;
    private String owner;
    private String subject;
    private Long creation;
    private List<Participant> participants;
    private Long subjectTime;
    private String subjectOwner;
    private String groupInviteLink;
}
