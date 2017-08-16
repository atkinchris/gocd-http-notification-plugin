package http.notifications.executors;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import http.notifications.Util;

public class GetViewRequestExecutor implements RequestExecutor {
    private static final Gson GSON = new Gson();

    @Override
    public GoPluginApiResponse execute() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("template", Util.readResource("/plugin-settings.template.html"));
        DefaultGoPluginApiResponse defaultGoPluginApiResponse = new DefaultGoPluginApiResponse(200);
        defaultGoPluginApiResponse.setResponseBody(GSON.toJson(jsonObject));
        return defaultGoPluginApiResponse;
    }
}
