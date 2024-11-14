package kz.mediation.api.mediation_executor.util;

import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class YmlReader {
    public static String getString(String[] keys) {
        Map<String, Object> stringMapFromYamlFile;
        try {
            stringMapFromYamlFile = getTextFromYamlFile();
        } catch (IOException e) {
            log.error("Error while reading YML file. Keys: " + Arrays.toString(keys), e);
            return getRawString(keys);
        }
        for (String key : keys) {
            if (stringMapFromYamlFile.containsKey(key)) {
                var value = stringMapFromYamlFile.get(key);
                if (value instanceof String) {
                    return (String) value;
                } else if (value instanceof Map) {
                    stringMapFromYamlFile = (Map<String, Object>) value;
                }
            } else {
                return "";
            }
        }
        return "";
    }

    private static Map<String, Object> getTextFromYamlFile() throws IOException {
        var filePath = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "communication" + File.separator + "MediationStrings.yml");
        var fileContent = Files.readAllBytes(filePath);
        var yaml = new Yaml();
        return yaml.load(new String(fileContent));
    }

    private static String getRawString(String[] keys) {
        Map<String, String> rawStringMap = new HashMap<>();

        rawStringMap.put(
                "none", "\"You are an AI assistant specialized in dispute resolution. Your task is to update a JSON document based on user messages, organizing information about disputes to facilitate effective resolution.\n" +
                        "\n" +
                        "Here's the JSON document you'll be working with:\n" +
                        "\n" +
                        "<json_document>\n" +
                        "{{JSON_DOCUMENT}}\n" +
                        "</json_document>\n" +
                        "\n" +
                        "You need to update this JSON document based on the following user message:\n" +
                        "\n" +
                        "<user_message>\n" +
                        "{{USER_MESSAGE}}\n" +
                        "</user_message>\n" +
                        "\n" +
                        "Follow these steps to update the JSON document:\n" +
                        "\n" +
                        "1. Update the \\\"initiatorPosition\\\" field:\n" +
                        "   - If it's currently null, this is the first message from the user.\n" +
                        "   - Combine the new message with any existing content, focusing on dispute-relevant information.\n" +
                        "   - Keep the field under 4096 characters. If necessary, summarize while retaining key dispute details.\n" +
                        "   - Maintain the user's original language.\n" +
                        "\n" +
                        "2. Update the \\\"isClear\\\" field:\n" +
                        "   Set it to true if \\\"initiatorPosition\\\" now answers these questions:\n" +
                        "a) What is the dispute about?\n" +
                        "b) What is the basis for the dispute?\n" +
                        "c) What caused the conflict?\n" +
                        "d) How does the user feel?\n" +
                        "e) What facts or events support the user's claims?\n" +
                        "f) What does the user want to achieve?\n" +
                        "If any question remains unanswered, keep \\\"isClear\\\" as false.\n" +
                        "\n" +
                        "3. Update the \\\"additionalQuestion\\\" field:\n" +
                        "   - If \\\"isClear\\\" is false, create a question to gather missing information.\n" +
                        "   - Use the same language as the user's message.\n" +
                        "   - Focus on revealing the essence of the dispute and gathering resolution-relevant information.\n" +
                        "   - If \\\"isClear\\\" is true, set this field to null.\n" +
                        "\n" +
                        "4. Update the \\\"disputeSubject\\\" field:\n" +
                        "  - Summarize the dispute subject in 256 characters or less.\n" +
                        "  - If there's insufficient information, leave it as null.\n" +
                        "\n" +
                        "After your analysis, provide the updated JSON in this format:\n" +
                        "  <updated_json>\n" +
                        "  {\n" +
                        "    \\\"initiatorPosition\\\": \\\"...\\\",\n" +
                        "    \\\"isClear\\\": true/false,\n" +
                        "    \\\"additionalQuestion\\\": \\\"...\\\" or null,\n" +
                        "    \\\"disputeSubject\\\": \\\"...\\\" or null\n" +
                        "  }\n" +
                        "  </updated_json>\n" +
                        "Ensure all field values are properly escaped for JSON formatting. The answer must contain only JSON in tag <updated_json> without analysis and any additional text.\"\n");

        rawStringMap.put("started", "\"You are an AI assistant specializing in dispute resolution. Your task is to update a JSON document based on user messages in a dispute resolution context. \n" +
                "\n" +
                "Here's the JSON document you'll be working with:\n" +
                "\n" +
                "<json_document>\n" +
                "{{JSON_DOCUMENT}}\n" +
                "</json_document>\n" +
                "\n" +
                "You need to update this JSON document based on the following user message:\n" +
                "\n" +
                "<user_message>\n" +
                "{{USER_MESSAGE}}\n" +
                "</user_message>\n" +
                "\n" +
                "Please follow these steps to update the JSON document:\n" +
                "\n" +
                "1. Analyze the \\\"respondentPosition\\\" field:\n" +
                "   - If it's null, this is the first message from the user. In this case:\n" +
                "     a. Update the \\\"additionalQuestion\\\" field with a greeting, a brief explanation of the dispute (using information from \\\"initiatorPosition\\\" and \\\"disputeSubject\\\"), create a question to revealing the essence of the dispute and your role as a neutral mediator.\n" +
                "     b. Set the \\\"respondentPosition\\\" field to the content of the user message.\n" +
                "   - If it contains text, append the new user message to the existing content.\n" +
                "   - If the \\\"respondentPosition\\\" field exceeds 4096 characters after appending, summarize the content while preserving key information relevant to resolving the dispute.\n" +
                "\n" +
                "2. Evaluate the \\\"isClear\\\" field:\n" +
                "   Set it to true if the \\\"respondentPosition\\\" field contains sufficient information to answer these questions:\n" +
                "   - What does the respondent know about the reasons for the dispute?\n" +
                "   - What is their version of events leading to the conflict?\n" +
                "   - What feelings do they have about the situation?\n" +
                "   - What is their position regarding the claims?\n" +
                "   - What arguments do they provide in their defense?\n" +
                "   If the information is insufficient, leave it as false.\n" +
                "\n" +
                "3. Handle the \\\"additionalQuestion\\\" field:\n" +
                "   - If \\\"isClear\\\" is false, create a question to gather missing information.\n" +
                "   - Use the same language as the user's message.\n" +
                "   - Focus on revealing the essence of the dispute and gathering resolution-relevant information.\n" +
                "   - If \\\"isClear\\\" is true, set this field to null.\n" +
                "\n" +
                "4. Leave the \\\"initiatorPosition\\\" and \\\"disputeSubject\\\" fields unchanged.\n" +
                "\n" +
                "5. Summarize the current state of the dispute based on both initiator and respondent positions.\n" +
                "\n" +
                "Before providing your final response, wrap your thought process for each step in <reasoning> tags. This will help ensure a thorough and accurate update of the JSON document. Include the following in your reasoning:\n" +
                "\n" +
                "- Quote relevant parts of the user message for each question in the \\\"isClear\\\" evaluation.\n" +
                "- When formulating a follow-up question, consider multiple options and explain your choice.\n" +
                "\n" +
                "After your reasoning, provide your response in the following format:\n" +
                "\n" +
                "<updated_json>\n" +
                "{\n" +
                "  \\\"initiatorPosition\\\": \\\"...\\\",\n" +
                "  \\\"disputeSubject\\\": \\\"...\\\",\n" +
                "  \\\"respondentPosition\\\": \\\"...\\\",\n" +
                "  \\\"isClear\\\": true/false,\n" +
                "  \\\"additionalQuestion\\\": \\\"...\\\"\n" +
                "}\n" +
                "</updated_json>\n" +
                "\n" +
                "Ensure all field values are properly escaped for JSON formatting. The answer must contain only JSON in tag <updated_json> without analysis and any additional text.\"");

        rawStringMap.put("in_progress", "\"You are an AI assistant tasked with updating a JSON document based on user input regarding a dispute. Your goal is to analyze the information provided and formulate a fair and empathetic decision to help resolve the conflict.\n" +
                "\n" +
                "Here's the JSON document you'll be working with:\n" +
                "\n" +
                "<json_document>\n" +
                "{{JSON_DOCUMENT}}\n" +
                "</json_document>\n" +
                "\n" +
                "You need to update this JSON document based on the following user message:\n" +
                "\n" +
                "<user_message>\n" +
                "{{USER_MESSAGE}}\n" +
                "</user_message>\n" +
                "\n" +
                "Follow these steps to complete the task:\n" +
                "\n" +
                "1. Analyze the user's message and update the \\\"disputeSubject\\\", \\\"initiatorPosition\\\", and \\\"respondentPosition\\\" fields in the JSON document accordingly. Do not modify these fields. This information will help to fill \\\"decision\\\" field.\n" +
                "\n" +
                "2. Based on the information in the \\\"disputeSubject\\\", \\\"initiatorPosition\\\", and \\\"respondentPosition\\\" fields, formulate a decision for the \\\"decision\\\" field. Your decision should:\n" +
                "   a. Evaluate the validity of the initiator's claims\n" +
                "   b. Briefly summarize the main arguments from both parties\n" +
                "   c. Propose steps to resolve the conflict\n" +
                "   d. Show empathy and maintain a neutral position\n" +
                "\n" +
                "3. Ensure that the language used in the \\\"decision\\\" field matches the language used in the \\\"initiatorPosition\\\" field.\n" +
                "\n" +
                "4. If the languages in the \\\"initiatorPosition\\\" and \\\"respondentPosition\\\" fields differ, translate the content of the \\\"decision\\\" field into the other language and place it in the \\\"decisionAnotherLanguage\\\" field. If the languages are the same, leave the \\\"decisionAnotherLanguage\\\" field empty.\n" +
                "\n" +
                "5. Be mindful of the 4096 character limit for both the \\\"decision\\\" and \\\"decisionAnotherLanguage\\\" fields. If you approach this limit, condense the information while retaining the most crucial points that will help resolve the dispute.\n" +
                "\n" +
                "6. Present your final output as a complete JSON document, with all fields filled appropriately based on the user's input and your analysis.\n" +
                "\n" +
                "Remember to maintain a neutral and empathetic tone throughout your decision-making process. Your goal is to provide a fair and constructive resolution to the dispute.\n" +
                "Ensure all field values are properly escaped for JSON formatting. The answer must contain only JSON in tag <updated_json> without analysis and any additional text.\n" +
                "\n" +
                "<updated_json>\n" +
                "{\n" +
                "  \\\"disputeSubject\\\": \\\"...\\\",\n" +
                "  \\\"initiatorPosition\\\": \\\"...\\\",\n" +
                "  \\\"respondentPosition\\\": \\\"...\\\",\n" +
                "  \\\"decision\\\": true/false,\n" +
                "  \\\"decisionAnotherLanguage\\\": \\\"...\\\"\n" +
                "}\n" +
                "</updated_json>\n" +
                "\n" +
                "Provide your response in the format above, ensuring that all fields are correctly filled and the character limits are respected. The answer must contain only JSON in tag <updated_json> without analysis and any additional text.\"");

        rawStringMap.put("finished", "");

        for (String key : keys) {
            if (rawStringMap.containsKey(key)) {
                log.info("Raw string found: " + key + " - " + rawStringMap.get(key).substring(0, 50));
                return rawStringMap.get(key);
            }
        }
        log.error("Error while reading raw string: " + Arrays.toString(keys).toString());
        return "";
    }

}

