package kz.mediation.api.mediation_executor.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class StateImpl implements State {
    private Map<String, Object> data;

    @Override
    public Scene getScene() {
        var scene = data.get("scene");
        if (scene != null) {
            return (Scene) scene;
        }
        return null;
    }

    @Override
    public void setScene(Scene scene) {
        data.put("scene", scene);
    }
}
