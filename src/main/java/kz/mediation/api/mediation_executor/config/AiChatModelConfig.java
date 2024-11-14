package kz.mediation.api.mediation_executor.config;

import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.anthropic.api.AnthropicApi;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AiChatModelConfig {
    @Value("${spring.ai.anthropic.api-key}")
    private String anthropicApiKey;
    @Value("${spring.ai.openai.api-key}")
    private String openAiApiKey;
    @Value("${spring.ai.default.model}")
    private String defaultModel;

    @Bean
    public AnthropicChatModel anthropicChatModel() {
        AnthropicApi anthropicApi = new AnthropicApi(anthropicApiKey);
        return new AnthropicChatModel(anthropicApi);
    }

    @Bean
    public OpenAiChatModel openAiChatModel() {
        OpenAiApi openAiApi = new OpenAiApi(openAiApiKey);
        return new OpenAiChatModel(openAiApi);
    }

    @Bean
    @Primary
    public ChatModel defaultChatModel(AnthropicChatModel anthropicChatModel,
                                      OpenAiChatModel openAiChatModel) {
        return switch (defaultModel.toLowerCase()) {
            case "anthropic" -> anthropicChatModel;
            case "openai" -> openAiChatModel;
            default -> throw new IllegalStateException(
                    "Unsupported model type: " + defaultModel +
                            ". Supported values are: 'anthropic' or 'openai'"
            );
        };
    }
}
