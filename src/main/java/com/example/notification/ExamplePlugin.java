package com.example.notification;

import com.thoughtworks.go.plugin.api.*;
import com.thoughtworks.go.plugin.api.annotation.*;
import com.thoughtworks.go.plugin.api.exceptions.*;
import com.thoughtworks.go.plugin.api.request.*;
import com.thoughtworks.go.plugin.api.response.*;
import com.thoughtworks.go.plugin.api.logging.Logger;
import java.util.*;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

@Extension
public class ExamplePlugin implements GoPlugin {
  private static Logger LOGGER = Logger.getLoggerFor(ExamplePlugin.class);

  private void postMessage(String message) {
    try {
      HttpClient httpClient = HttpClientBuilder.create().build();
      HttpPost request = new HttpPost("http://node:3000");
      StringEntity entity = new StringEntity(message, "UTF-8");
      request.addHeader("content-type", "application/json");
      request.setEntity(entity);

      HttpResponse response = httpClient.execute(request);
      LOGGER.info(response.toString());
    } catch (Exception ex) {
      LOGGER.error(ex.toString());
    }
  }

  public void initializeGoApplicationAccessor(GoApplicationAccessor accessor) {
    // Unused
  }

  public GoPluginIdentifier pluginIdentifier() {
    return new GoPluginIdentifier("notification", Arrays.asList("2.0"));
  }

  public GoPluginApiResponse handle(GoPluginApiRequest request) throws UnhandledRequestTypeException {
    switch (request.requestName()) {
      case "notifications-interested-in":
          return new DefaultGoPluginApiResponse(200, "{\"notifications\":[\"stage-status\"]}");
      case "stage-status":
          LOGGER.info(request.requestBody());
          postMessage(request.requestBody());
          return new DefaultGoPluginApiResponse(200, "{\"status\": \"success\"}");
      default:
          throw new UnhandledRequestTypeException(request.requestName());
    }
  }
}
