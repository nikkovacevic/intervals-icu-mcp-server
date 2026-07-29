package intervals.boundary;

import io.quarkus.rest.client.reactive.ClientBasicAuth;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/")
@ApplicationScoped
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RegisterRestClient(configKey = "intervals-client")
@ClientBasicAuth(username = "${intervals-client.username}", password = "${intervals-client.password}")
public interface IntervalsClient {

    @GET
    @Path("/athlete/0/activities")
    List<ActivitySummaryDTO> getActivities(@QueryParam("oldest") String oldest, @QueryParam("fields") List<String> fields, @QueryParam("limit") int i);

    @GET
    @Path("/activity/{activityId}/intervals")
    ActivityIntervalsDTO getActivityIntervals(@PathParam("activityId") String activityId);
}
