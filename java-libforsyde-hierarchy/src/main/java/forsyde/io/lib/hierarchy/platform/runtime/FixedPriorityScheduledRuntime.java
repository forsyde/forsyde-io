package forsyde.io.lib.hierarchy.platform.runtime;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * As the name implies, runtimes with the "FixedPriorityScheduledRuntime" trait can schedule its processes
 * following a fixed priority policy. Whether the runtime allows for preemption or not, is a property of components
 * satisfying this trait.
 *
 * If you want to represent the presence of overheads for runtimes satisfying this trait, you should add
 * the "InstrumentedBehaviour" to a component that has "FixedPriorityScheduledRuntime".
 *
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface FixedPriorityScheduledRuntime extends AbstractRuntime {

    @Property
    default Boolean supportsPreemption() {return true;}

    @Property
    default Boolean allowsInterCoreMigration() {return false;}

    @Property
    default Integer minimumActivationInSecsNumerator() {return 0;}

    @Property
    default Integer minimumActivationInSecsDenominator() {return 1;}

    @Property
    default Map<String, Integer> priorityAssignments() {return new HashMap<>();}

    default Map<String, List<String>> taskToProcessingElementsAffinity() {
        return new HashMap<>();
    }

}
