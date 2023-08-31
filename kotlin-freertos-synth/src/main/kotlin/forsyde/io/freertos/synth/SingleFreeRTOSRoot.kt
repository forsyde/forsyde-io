package forsyde.io.freertos.synth

import forsyde.io.lib.behavior.execution.Task
import forsyde.io.lib.implementation.synthetizable.PeriodicTask
import forsyde.io.lib.platform.runtime.FixedPriorityScheduledRuntime
import org.jgrapht.alg.connectivity.ConnectivityInspector

/**
 * This class represents the root of one FreeRTOS runtime.
 * That is, of one programmable entry point, regardless if it is working
 * in SMP mode and controls multiple processing elements.
 */
data class SingleFreeRTOSRoot(
    val mainRuntime: FixedPriorityScheduledRuntime,
    val periodicTaskGerators: Set<PeriodicTask>
) : CanGenerateFiles {


}