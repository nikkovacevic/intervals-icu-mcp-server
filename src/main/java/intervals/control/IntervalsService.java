package intervals.control;

import intervals.boundary.ActivityIntervalsDTO;
import intervals.boundary.ActivitySummaryDTO;
import intervals.boundary.IntervalsClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class IntervalsService {

    private final List<String> listOfFields = List.of(
    "type",
    "start_date_local",
    "id",
    "icu_atl",
    "icu_ctl",
    "icu_training_load",
    "icu_rolling_ftp",
    "icu_weighted_avg_watts",
    "icu_average_watts",
    "icu_variability_index",
    "icu_intensity",
    "icu_efficiency_factor",
    "decoupling",
    "average_heartrate",
    "max_heartrate",
    "average_cadence",
    "icu_zone_times",
    "icu_hr_zone_times",
    "total_elevation_gain",
    "moving_time",
    "distance",
    "average_temp",
    "carbs_used",
    "carbs_ingested",
    "perceived_exertion",
    "feel",
    "icu_resting_hr",
    "icu_weight",
    "interval_summary",
    "hr_load",
    "power_load"
    );

    private final IntervalsClient intervalsClient;

    @Inject
    public IntervalsService(@RestClient IntervalsClient intervalsClient) {
        this.intervalsClient = intervalsClient;
    }

    public Optional<ActivitySummaryDTO> getLastRide() {
        LocalDate startOfCurrentWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        List<ActivitySummaryDTO> activities = intervalsClient.getActivities(startOfCurrentWeek.toString(), listOfFields, 7);
        return activities.stream()
                .filter(activitySummary -> "Ride".equals(activitySummary.type()))
                .max(Comparator.comparing(ActivitySummaryDTO::start_date_local));
    }

    public Optional<ActivityIntervalsDTO> getLastRideIntervals(String activityId) {
        if (activityId == null) {
            return Optional.empty();
        }
        ActivityIntervalsDTO activityIntervals = intervalsClient.getActivityIntervals(activityId);
        return Optional.of(activityIntervals);
    }
}
