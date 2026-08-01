package activity.boundary;

import activity.entity.Activity;
import intervals.boundary.ICUActivityIntervalsDTO;
import intervals.boundary.ICUActivitySummaryDTO;

import java.util.List;

public record ActivityAnalysisInput(
        ICUActivitySummaryDTO summary,
        ICUActivityIntervalsDTO intervals,
        List<Activity> pastActivities
) {
}
