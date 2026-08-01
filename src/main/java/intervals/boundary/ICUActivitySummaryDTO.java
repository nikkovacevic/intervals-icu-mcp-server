package intervals.boundary;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ICUActivitySummaryDTO(
        String type,
        LocalDateTime start_date_local,
        String id,
        BigDecimal icu_atl,
        BigDecimal icu_ctl,
        BigDecimal icu_training_load,
        BigDecimal icu_rolling_ftp,
        BigDecimal icu_weighted_avg_watts,
        BigDecimal icu_average_watts,
        BigDecimal icu_variability_index,
        BigDecimal icu_intensity,
        BigDecimal icu_efficiency_factor,
        BigDecimal decoupling,
        BigDecimal average_heartrate,
        BigDecimal max_heartrate,
        BigDecimal average_cadence,
        List<ZoneTime> icu_zone_times,
        List<BigDecimal> icu_hr_zone_times,
        BigDecimal total_elevation_gain ,
        BigDecimal moving_time,
        BigDecimal distance,
        BigDecimal average_temp,
        BigDecimal carbs_used,
        BigDecimal perceived_exertion,
        BigDecimal feel,
        BigDecimal icu_resting_hr,
        BigDecimal icu_weight,
        List<String> interval_summary,
        BigDecimal hr_load,
        BigDecimal power_load
) {
}
