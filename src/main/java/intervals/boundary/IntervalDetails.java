package intervals.boundary;

import java.math.BigDecimal;

public record IntervalDetails(
        String type,
        BigDecimal moving_time,
        BigDecimal elapsed_time,
        BigDecimal average_watts,
        BigDecimal weighted_average_watts,
        BigDecimal min_watts,
        BigDecimal max_watts,
        BigDecimal average_watts_kg,
        BigDecimal average_heartrate,
        BigDecimal max_heartrate,
        BigDecimal average_cadence,
        BigDecimal decoupling,
        BigDecimal intensity,
        BigDecimal joules_above_ftp,
        BigDecimal wbal_end,
        BigDecimal total_elevation_gain,
        BigDecimal average_gradient,
        BigDecimal average_temp,
        BigDecimal training_load,
        String group_id
) {
}
