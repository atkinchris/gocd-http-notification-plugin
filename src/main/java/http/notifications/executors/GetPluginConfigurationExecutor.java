package http.notifications.executors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import http.notifications.Util;

import java.util.HashMap;
import java.util.Map;

public class GetPluginConfigurationExecutor implements RequestExecutor {
    private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public GoPluginApiResponse execute() {
        Map<String, Object> response = new HashMap<>();
        response.put("url", Util.buildField("URL", "", "1", true, false));
        return new DefaultGoPluginApiResponse(200, GSON.toJson(response));
    }
}
