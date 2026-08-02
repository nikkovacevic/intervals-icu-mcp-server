package mcp;

import activity.boundary.ActivityAnalysisInput;
import activity.boundary.ActivityInputDTO;
import activity.control.ActivityService;
import dev.toonformat.jtoon.JToon;
import io.quarkiverse.mcp.server.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class McpTools {

    private static final Logger log = Logger.getLogger(McpTools.class);

    private final ActivityService activityService;

    @Inject
    public McpTools(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Tool(
            name = "get_latest_ride_data",
            description = "Fetches the user's latest cycling or activity data. " +
                    "Use this tool whenever the user asks to get, retrieve, " +
                    "or analyze their last ride details. The analysis should be done according to users cycling goals " +
                    "and plan which is stored in your memory"
    )
    public String getLatestRideData() {
        log.info("Claude is accessing get_latest_ride_data");
        ActivityAnalysisInput analysis = activityService.getLastRideData();
        return JToon.encode(analysis);
    }

    @Tool(
            name = "add_latest_ride_to_DB",
            description = "Saves the analyzed ride data and our conversation summary to the database. " +
                    "Trigger this tool ONLY when the user explicitly instructs you to save, log, or add the ride to the DB. " +
                    "You must construct the ActivityInputDTO using the context of our analysis: " +
                    "1. 'date': The date of the ride formatted strictly as YYYY-MM-DD. " +
                    "2. 'summary': A synthesized, concise paragraph capturing the key takeaways and insights from our chat about the ride. " +
                    "3. 'fatigueATL', 'fitnessCTL', 'formTSB', 'restingHeartRate': The exact numerical metrics extracted from the ride data. If any of these metrics are missing or unknown, you MUST pass null. " +
                    "Ensure the summary accurately reflects our discussion."
    )
    public String addRideToDB(ActivityInputDTO dto) {
        log.infof("Claude is accessing add_latest_ride_to_DB with %s", dto.toString());
        activityService.createActivity(dto);
        return String.format("Successfully added ride from %s to DB", dto.date().toString());
    }

}
