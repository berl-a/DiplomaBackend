package system.controller.simple_frontend_models;

import java.util.HashMap;
import java.util.Map;

public class Response {

    private Map<String, Object> result = new HashMap<>();

    public Response() {}

    public Response(String key, Object value) {
        result.put(key, value);
    }

    public void put(String key, Object value) {
        result.put(key, value);
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
