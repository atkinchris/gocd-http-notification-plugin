package http.notifications.executors;

import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

interface RequestExecutor {
    GoPluginApiResponse execute();
}
