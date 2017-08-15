package com.example.notification;

import com.thoughtworks.go.plugin.api.*;
import com.thoughtworks.go.plugin.api.annotation.*;
import com.thoughtworks.go.plugin.api.exceptions.*;
import com.thoughtworks.go.plugin.api.request.*;
import com.thoughtworks.go.plugin.api.response.*;
import java.util.*;

@Extension
public class ExamplePlugin implements GoPlugin {
  private GoApplicationAccessor accessor;

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
          System.out.println(request.requestBody());
          return new DefaultGoPluginApiResponse(200);
      default:
          throw new UnhandledRequestTypeException(request.requestName());
    }
  }
}
