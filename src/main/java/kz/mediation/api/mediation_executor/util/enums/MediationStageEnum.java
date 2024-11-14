package kz.mediation.api.mediation_executor.util.enums;

import lombok.Getter;

@Getter
public enum MediationStageEnum {
    NONE, // initiator start to create dispute
    STARTED, // respondent start to create dispute
    IN_PROGRESS, // send result to parties and close dispute
    FINISHED // the dispute is closed
}
