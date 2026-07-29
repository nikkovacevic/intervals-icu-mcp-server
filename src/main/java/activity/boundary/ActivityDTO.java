package activity.boundary;

import intervals.boundary.ActivityIntervalsDTO;
import intervals.boundary.ActivitySummaryDTO;

public record ActivityDTO(
        ActivitySummaryDTO summary,
        ActivityIntervalsDTO intervals
) {
}
