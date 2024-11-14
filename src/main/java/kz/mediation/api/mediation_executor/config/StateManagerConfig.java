package kz.mediation.api.mediation_executor.config;

import kz.mediation.api.mediation_executor.util.StateManager;
import kz.mediation.api.mediation_executor.util.StateManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StateManagerConfig {
    @Bean
    public StateManager stateManager() {
        return new StateManagerImpl();
    }
}
