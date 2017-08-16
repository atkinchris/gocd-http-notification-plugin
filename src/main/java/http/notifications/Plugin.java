package http.notifications;

import com.thoughtworks.go.plugin.api.*;
import com.thoughtworks.go.plugin.api.annotation.*;
import com.thoughtworks.go.plugin.api.exceptions.*;
import com.thoughtworks.go.plugin.api.request.*;
import com.thoughtworks.go.plugin.api.response.*;
import http.notifications.executors.*;

import java.util.*;

@Extension
public class Plugin implements GoPlugin {
    private static final String REQUEST_NOTIFICATIONS_INTERESTED_IN = "notifications-interested-in";
    private static final String REQUEST_STAGE_STATUS = "stage-status";
    private static final String REQUEST_GET_VIEW = "go.plugin-settings.get-view";
    private static final String REQUEST_GET_CONFIGURATION = "go.plugin-settings.get-configuration";
    private static final String REQUEST_VALIDATE_CONFIGURATION = "go.plugin-settings.validate-configuration";

    public void initializeGoApplicationAccessor(GoApplicationAccessor accessor) {
        // Unused
    }

    public GoPluginIdentifier pluginIdentifier() {
        return new GoPluginIdentifier("notification", Collections.singletonList("2.0"));
    }

    public GoPluginApiResponse handle(GoPluginApiRequest request) throws UnhandledRequestTypeException {
        try {
            switch (request.requestName()) {
                case REQUEST_GET_VIEW:
                    return new GetViewRequestExecutor().execute();
                case REQUEST_GET_CONFIGURATION:
                    return new GetPluginConfigurationExecutor().execute();
                case REQUEST_NOTIFICATIONS_INTERESTED_IN:
                    return new NotificationInterestedInExecutor().execute();
                case REQUEST_STAGE_STATUS:
                    return new StageStatusRequestExecutor(request).execute();
                case REQUEST_VALIDATE_CONFIGURATION:
                    return new ValidateConfigurationExecutor().execute();
                default:
                    throw new UnhandledRequestTypeException(request.requestName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
