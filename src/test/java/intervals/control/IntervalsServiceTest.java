package intervals.control;

import dev.toonformat.jtoon.JToon;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class IntervalsServiceTest {

    @Inject
    IntervalsService intervalsService;

    @Test
    public void testGetLastRide() {
        var lastRide = intervalsService.getLastRide();
        assertNotNull(lastRide, "Last ride should not be null");
    }

    @Test
    public void testGetLastRideIntervals() {
        var intervals = intervalsService.getLastRideIntervals("i157816842");
        String encoded = JToon.encode(intervals);
        assertNotNull(encoded, "Last ride should not be null");
    }
}