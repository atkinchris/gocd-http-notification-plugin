package http.notifications.executors;

import com.thoughtworks.go.plugin.api.request.*;
import com.thoughtworks.go.plugin.api.response.*;
import com.thoughtworks.go.plugin.api.logging.Logger;
import java.util.*;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;

public class StageStatusRequestExecutor implements RequestExecutor {
    private static final Logger LOGGER = Logger.getLoggerFor(StageStatusRequestExecutor.class);
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    private final GoPluginApiRequest request;

    public StageStatusRequestExecutor(GoPluginApiRequest request) {
        this.request = request;
    }

    @Override
    public GoPluginApiResponse execute() {
      HashMap<String, Object> responseJson = new HashMap<>();

      try {
          postMessage();
          responseJson.put("status", "success");
      } catch (Exception e) {
          responseJson.put("status", "failure");
          responseJson.put("messages", Collections.singletonList(e.getMessage()));
      }

      return new DefaultGoPluginApiResponse(200, GSON.toJson(responseJson));
    }

    private void postMessage() {
      try {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://node:3000");
        StringEntity entity = new StringEntity(this.request.requestBody(), "UTF-8");
        request.addHeader("content-type", "application/json");
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        LOGGER.info(response.toString());
      } catch (Exception ex) {
        LOGGER.error(ex.toString());
      }
    }
}
