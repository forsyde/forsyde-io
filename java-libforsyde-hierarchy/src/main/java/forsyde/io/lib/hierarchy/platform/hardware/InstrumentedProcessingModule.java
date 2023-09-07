package forsyde.io.lib.hierarchy.platform.hardware;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.Map;

/**
 * A InstrumentedProcessingModule enriches `GenericProcessingModule` with provision numbers so that
 * an analysis and synthesis tool is able to estimate the total amount of execution time is required to execute
 * a bunch of instructions in this processing element.
 *
 * If this processing element exhibits higher level of parallelism (see `GenericProcessingModule`), then the provisions
 * should always be provided _per parallel "thread"_.
 * For example, if the processing element is a typical dual-core, the model instructions per cycle property
 * should be as the intructions per cycle _per core_, not their summed total.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface InstrumentedProcessingModule extends GenericProcessingModule {

    @Property
    Map<String, Map<String, Double>> modalInstructionsPerCycle();

//    @Property
//    default Map<String, Map<String, Integer>> modalInstructionsPerCycleDenominators() {
//        return modalInstructionsPerCycleNumerators().entrySet().stream().collect(Collectors.toMap(
//                Map.Entry::getKey,
//                e -> e.getValue().entrySet().stream().collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        ee -> 1
//                ))
//        ));
//    };
}
