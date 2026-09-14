package activity.boundary;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.math.BigDecimal;
import java.time.LocalDate;

@RegisterForReflection
public record ActivityInputDTO(
        LocalDate date,
        String summary,
        String classification,
        String success,
        BigDecimal fatigueATL,
        BigDecimal fitnessCTL,
        BigDecimal formTSB,
        BigDecimal restingHeartRate
) {
}
