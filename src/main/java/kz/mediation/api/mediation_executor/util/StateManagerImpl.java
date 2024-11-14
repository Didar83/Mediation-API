package kz.mediation.api.mediation_executor.util;


import kz.mediation.api.mediation_executor.exception.BotStateException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StateManagerImpl implements StateManager {
    private final Map<String, StateImpl> states;
    private final Map<String, Object> initStateData;

    public StateManagerImpl() {
        this.states = new HashMap<>();
        this.initStateData = new HashMap<>();
    }

    public StateManagerImpl(Map<String, Object> initStateData) {
        this.states = new HashMap<>();
        this.initStateData = initStateData;
    }

    @Override
    public Optional<State> get(String chatId) {
        return Optional.ofNullable(states.get(chatId));
    }

    @Override
    public StateImpl create(String chatId) {
        //todo: refactor this code
        states.put(chatId, new StateImpl(new HashMap<>(initStateData)));
        return (StateImpl) get(chatId).orElseThrow(BotStateException::new);
    }

    @Override
    public void update(String chatId) {
        //todo: create method for update
    }

    @Override
    public void delete(String chatId) {
        states.remove(chatId);
    }

    @Override
    public Optional<Map<String, Object>> getStateData(String chatId) {
        var state = states.get(chatId);
        return state != null ? Optional.ofNullable(state.getData()) : Optional.empty();
    }

    @Override
    public void setStateData(String chatId, Map<String, Object> stateData) {
        var state = states.get(chatId);
        if (state != null) {
            state.setData(stateData);
        }
    }

    @Override
    public void updateStateData(String chatId, Map<String, Object> newStateData) {
        var state = states.get(chatId);
        if (state != null) {
            if (state.getData() == null) {
                state.setData(newStateData);
            } else {
                state.getData().putAll(newStateData);
            }
        }
    }

    @Override
    public void deleteStateData(String chatId) {
        StateImpl state = states.get(chatId);
        if (state != null) {
            state.setData(new HashMap<>(initStateData));
        }
    }
}
