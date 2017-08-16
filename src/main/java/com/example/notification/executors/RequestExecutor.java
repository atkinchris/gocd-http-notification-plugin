package com.example.notification.executors;

import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

public interface RequestExecutor {
    GoPluginApiResponse execute() throws Exception;
}
