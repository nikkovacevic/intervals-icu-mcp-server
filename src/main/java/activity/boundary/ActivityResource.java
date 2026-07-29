package activity.boundary;

import activity.service.ActivityService;
import dev.toonformat.jtoon.JToon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/api/v1")
public class ActivityResource {

    private final ActivityService activityService;

    @Inject
    public ActivityResource(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GET
    @Path("/last-ride-data")
    public Response getLastRideData() {
        ActivityDTO analysis = activityService.getLastRideData();
        return Response.ok(JToon.encode(analysis)).build();
    }

}
