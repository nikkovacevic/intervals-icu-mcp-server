package mcp;

import activity.boundary.ActivityDTO;
import activity.service.ActivityService;
import dev.toonformat.jtoon.JToon;
import io.quarkiverse.mcp.server.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class McpTools {

    private final ActivityService activityService;

    @Inject
    public McpTools(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Tool(
            name = "get_latest_ride_data",
            description = "Fetches the user's latest cycling or activity data. " +
                    "Use this tool whenever the user asks to get, retrieve, " +
                    "or analyze their last ride details"
    )
    public String getLatestRideData() {
        ActivityDTO analysis = activityService.getLastRideData();
        return JToon.encode(analysis);
    }

}
