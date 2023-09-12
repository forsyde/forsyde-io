package forsyde.io.freertos.synth;

import forsyde.io.lib.hierarchy.implementation.synthetizable.PeriodicTask;
import forsyde.io.lib.hierarchy.platform.runtime.FixedPriorityScheduledRuntime;

import java.util.Set;

/**
 * This class represents the root of one FreeRTOS runtime.
 * That is, of one programmable entry point, regardless if it is working
 * in SMP mode and controls multiple processing elements.
 */
public record FreeRTOSMain(
        FixedPriorityScheduledRuntime mainRuntime,
        Set<PeriodicTask> periodicTaskGerators
) {
}
