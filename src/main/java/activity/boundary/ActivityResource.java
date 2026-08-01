package activity.boundary;

import activity.control.ActivityService;
import activity.entity.Activity;
import dev.toonformat.jtoon.JToon;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
@Path("/api/v1")
public class ActivityResource {

    private final ActivityService activityService;

    @Inject
    public ActivityResource(ActivityService activityService) {
        this.activityService = activityService;
    }

    @POST
    @Path("/activities")
    public Response createActivity(ActivityInputDTO dto) {
        activityService.createActivity(dto);
        return Response.status(201).build();
    }

    @GET
    @Path("/activities")
    public Response getLast10Rides() {
        List<Activity> activities = activityService.getLast10Activities();
        return Response.ok(activities).build();
    }

    @GET
    @Path("/last-ride-data")
    public Response getLastRideData() {
        ActivityAnalysisInput analysis = activityService.getLastRideData();
        return Response.ok(JToon.encode(analysis)).build();
    }

}
