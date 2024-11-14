package kz.mediation.api.mediation_executor.util;

import java.util.Map;

public interface State {
    Map<String, Object> getData();
    void setData(Map<String, Object> data);
    Scene getScene();
    void setScene(Scene scene);
}
