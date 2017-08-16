package http.notifications.executors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

public class NotificationInterestedInExecutor implements RequestExecutor {
    private static final Gson GSON = new Gson();

    @Override
    public GoPluginApiResponse execute() {
        JsonObject jsonObject = new JsonObject();
        JsonArray notifications = new JsonArray();
        notifications.add("stage-status");
        jsonObject.add("notifications", notifications);

        DefaultGoPluginApiResponse defaultGoPluginApiResponse = new DefaultGoPluginApiResponse(200);
        defaultGoPluginApiResponse.setResponseBody(GSON.toJson(jsonObject));
        return defaultGoPluginApiResponse;
    }
}
