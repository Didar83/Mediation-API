package kz.mediation.api.mediation_executor.util;

import java.util.Map;
import java.util.Optional;

public interface StateManager {
    Optional<State> get(String stateId);

    StateImpl create(String stateId);
    void update(String stateId);
    void delete(String stateId);
    Optional<Map<String, Object>> getStateData(String stateId);
    void setStateData(String stateId, Map<String, Object> stateData);
    void updateStateData(String stateId, Map<String, Object> stateData);
    void deleteStateData(String stateId);
}
