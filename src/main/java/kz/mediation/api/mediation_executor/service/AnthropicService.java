package kz.mediation.api.mediation_executor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class AnthropicService {
    private final AnthropicChatModel anthropicChatModel;

    public String generateText(String userMessage) {
        String generatedText = "";
        try {
            List<Message> messages = new ArrayList<>();
            messages.add(new UserMessage(userMessage));
            Prompt prompt = new Prompt(messages);
            ChatResponse chatResponse = anthropicChatModel.call(prompt);
            List<Generation> generations = chatResponse.getResults();
            if (generations != null && !generations.isEmpty()) {
                Generation generation = generations.getFirst();
                AssistantMessage assistantMessage = generation.getOutput();
                generatedText = assistantMessage.getContent();
            }
        } catch (Exception e) {
            log.error("Error while AnthropicService.generateText()", e);
        }
        return generatedText;
    }
}
