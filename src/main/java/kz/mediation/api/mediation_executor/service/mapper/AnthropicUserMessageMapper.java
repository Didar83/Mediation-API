package kz.mediation.api.mediation_executor.service.mapper;

import kz.mediation.api.mediation_executor.model.Dispute;
import kz.mediation.api.mediation_executor.util.YmlReader;
import kz.mediation.api.mediation_executor.util.enums.MediationStageEnum;

public class AnthropicUserMessageMapper {

    public static String getUserMessage(Dispute dispute) {
        StringBuilder sb = new StringBuilder();
        MediationStageEnum mediationStageEnum = dispute.getMediationStage().getMediationStage();
        if (mediationStageEnum.equals(MediationStageEnum.FINISHED)) {
            return "Спор окончен. Решение по спору: " + dispute.getDecision();
        }
        String rawSystemMessage = YmlReader.getString(new String[]{mediationStageEnum.name().toLowerCase()});
        if (mediationStageEnum.equals(MediationStageEnum.NONE)) {
            sb.append("{\n\"initiatorPosition\": \"")
                    .append(dispute.getInitiatorPosition()).append("\",\n")
                    .append("\"isClear\": false,\n")
                    .append("\"additionalQuestion\": \"\",\n")
                    .append("\"disputeSubject\": \"")
                    .append(dispute.getDisputeSubject()).append("\"\n}");
            String jsonDocumentFormatNone = """
                    {
                    	"initiatorPosition": "...",
                    	"isClear": true/false,
                    	"additionalQuestion": "...",
                    	"disputeSubject": "..."
                    }
                    """;
            return rawSystemMessage
                    .replace("{{JSON_DOCUMENT}}", jsonDocumentFormatNone)
                    .replace("{{USER_MESSAGE}}", sb.toString());
        } else if (mediationStageEnum.equals(MediationStageEnum.STARTED)) {
            sb.append("{\n\"respondentPosition\": \"")
                    .append(dispute.getRespondentPosition()).append("\",\n")
                    .append("\"isClear\": false, \n")
                    .append("\"initiatorPosition\": \"")
                    .append(dispute.getInitiatorPosition()).append("\",\n")
                    .append("\"additionalQuestion\": \"\"\n")
                    .append("\"disputeSubject\": \"")
                    .append(dispute.getDisputeSubject()).append("\"\n}");
            String jsonDocumentFormatStarted = """
                    {
                    	"respondentPosition": "...",
                    	"isClear": true/false,
                    	"initiatorPosition": "...",
                    	"additionalQuestion": "...",
                    	"disputeSubject": "...",
                    }
                    """;
            return rawSystemMessage
                    .replace("{{JSON_DOCUMENT}}", jsonDocumentFormatStarted)
                    .replace("{{USER_MESSAGE}}", sb.toString());
        } else {
            sb.append("{\n\"disputeSubject\": \"")
                    .append(dispute.getDisputeSubject()).append("\",\n")
                    .append("\"initiatorPosition\": \"")
                    .append(dispute.getInitiatorPosition()).append("\",\n")
                    .append("\"respondentPosition\": \"")
                    .append(dispute.getRespondentPosition()).append("\",\n")
                    .append("\"decision\": \"\"\n")
                    .append("\"decisionAnotherLanguage\": \"\"\n}");
            String jsonDocumentFormatInProgress = """
                    {
                     	"disputeSubject": "...",
                     	"initiatorPosition": "...",
                     	"respondentPosition": "...",
                     	"decision": "...",
                     	"decisionAnotherLanguage": "..."
                     }
                    """;
            return rawSystemMessage
                    .replace("{{JSON_DOCUMENT}}", jsonDocumentFormatInProgress)
                    .replace("{{USER_MESSAGE}}", sb.toString());
        }
    }
}
