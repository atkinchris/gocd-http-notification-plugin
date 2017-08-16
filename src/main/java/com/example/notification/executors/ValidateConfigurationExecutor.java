package com.example.notification.executors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

public class ValidateConfigurationExecutor implements RequestExecutor {
  private static final Gson GSON = new Gson();

  @Override
  public GoPluginApiResponse execute() throws Exception {
      JsonArray response = new JsonArray();

      DefaultGoPluginApiResponse defaultGoPluginApiResponse = new DefaultGoPluginApiResponse(200);
      defaultGoPluginApiResponse.setResponseBody(GSON.toJson(response));
      return defaultGoPluginApiResponse;
  }
}
