package com.example.notification;

import com.thoughtworks.go.plugin.api.*;
import com.thoughtworks.go.plugin.api.annotation.*;
import com.thoughtworks.go.plugin.api.exceptions.*;
import com.thoughtworks.go.plugin.api.request.*;
import com.thoughtworks.go.plugin.api.response.*;
import java.util.*;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

@Extension
public class ExamplePlugin implements GoPlugin {
  private GoApplicationAccessor accessor;

  private void postMessage(String message) {
    try {
      HttpClient httpClient = HttpClientBuilder.create().build();
      StringEntity requestEntity = new StringEntity(message, "application/json");
      HttpPost postMethod = new HttpPost("http://localhost:3000");

      postMethod.setEntity(requestEntity);
      httpClient.execute(postMethod);
    } catch (Exception ex) {
      //handle exception here
    }
  }

  public void initializeGoApplicationAccessor(GoApplicationAccessor accessor) {
    this.accessor = accessor;
  }

  public GoPluginIdentifier pluginIdentifier() {
    return new GoPluginIdentifier("notification", Arrays.asList("2.0"));
  }

  public GoPluginApiResponse handle(GoPluginApiRequest request) throws UnhandledRequestTypeException {
    switch (request.requestName()) {
      case "notifications-interested-in":
          return new DefaultGoPluginApiResponse(200, "{\"notifications\":[\"stage-status\"]}");
      case "stage-status":
          postMessage(request.requestBody());
          return new DefaultGoPluginApiResponse(200, "{\"status\": \"success\"}");
      default:
          throw new UnhandledRequestTypeException(request.requestName());
    }
  }
}
