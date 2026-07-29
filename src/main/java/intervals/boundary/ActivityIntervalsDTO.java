package intervals.boundary;


import java.util.List;

public record ActivityIntervalsDTO(
        String id,
        List<IntervalDetails> icu_intervals
) {
}
