package intervals.boundary;


import java.util.List;

public record ICUActivityIntervalsDTO(
        String id,
        List<IntervalDetails> icu_intervals
) {
}
