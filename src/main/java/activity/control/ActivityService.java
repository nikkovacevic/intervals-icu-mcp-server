package activity.control;

import activity.boundary.ActivityAnalysisInput;
import activity.boundary.ActivityInputDTO;
import activity.entity.Activity;
import intervals.boundary.ICUActivityIntervalsDTO;
import intervals.boundary.ICUActivitySummaryDTO;
import intervals.control.IntervalsService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ActivityService {

    private static final Logger log = Logger.getLogger(ActivityService.class);

    private final IntervalsService intervalsService;
    private final ActivityRepository repository;

    @Inject
    public ActivityService(IntervalsService intervalsService, ActivityRepository repository) {
        this.intervalsService = intervalsService;
        this.repository = repository;
    }

    public void createActivity(ActivityInputDTO dto) {
        String generatedUUID = UUID.randomUUID().toString();
        Activity entity = new Activity(
                generatedUUID,
                dto.date().toString(),
                dto.summary(),
                dto.fatigueATL(),
                dto.fitnessCTL(),
                dto.formTSB(),
                dto.restingHeartRate()
        );

        repository.create(entity);
    }

    public List<Activity> getLast10Activities() {
        return repository.getLast10Activities();
    }

    public ActivityAnalysisInput getLastRideData() {
        log.info("Getting last ride data");
        Optional<ICUActivitySummaryDTO> lastRide = intervalsService.getLastRide();
        if (lastRide.isEmpty()) {
            log.error("No rides in the past 7 days!");
            throw new BadRequestException("No rides in the past 7 days!");
        }
        log.info("Last ride fetched successfully");
        Optional<ICUActivityIntervalsDTO> lastRideIntervals = intervalsService.getLastRideIntervals(lastRide.get().id());
        if (lastRideIntervals.isEmpty()) {
            log.error("Activity found, but no intervals found!");
            throw new IllegalStateException("Activity found, but no intervals found!");
        }
        log.info("Last ride intervals fetched successfully");
        List<Activity> pastActivities = repository.getLast10Activities();
        log.info("Summary of past activities fetched successfully");

        return new ActivityAnalysisInput(lastRide.get(), lastRideIntervals.get(), pastActivities);
    }
}
