package activity.service;

import activity.boundary.ActivityDTO;
import intervals.boundary.ActivityIntervalsDTO;
import intervals.boundary.ActivitySummaryDTO;
import intervals.control.IntervalsService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;

import java.util.Optional;

@ApplicationScoped
public class ActivityService {

    private final IntervalsService intervalsService;

    @Inject
    public ActivityService(IntervalsService intervalsService) {
        this.intervalsService = intervalsService;
    }

    public ActivityDTO getLastRideData() {
        Optional<ActivitySummaryDTO> lastRide = intervalsService.getLastRide();
        if (lastRide.isEmpty()) {
            throw new BadRequestException("No rides in the past week!");
        }
        Optional<ActivityIntervalsDTO> lastRideIntervals = intervalsService.getLastRideIntervals(lastRide.get().id());
        if (lastRideIntervals.isEmpty()) {
            throw new IllegalStateException("Activity found, but no intervals found!");
        }

        return new ActivityDTO(lastRide.get(), lastRideIntervals.get());
    }
}
