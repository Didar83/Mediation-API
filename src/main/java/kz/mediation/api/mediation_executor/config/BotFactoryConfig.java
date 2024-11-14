package kz.mediation.api.mediation_executor.config;

import kz.mediation.api.mediation_executor.util.BotFactory;
import kz.mediation.api.mediation_executor.util.StateManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BotFactoryConfig {
    @Bean
    public BotFactory getBotFactory(RestTemplate restTemplate, StateManager stateManager) {
        return new BotFactory(restTemplate, stateManager);
    }
}
