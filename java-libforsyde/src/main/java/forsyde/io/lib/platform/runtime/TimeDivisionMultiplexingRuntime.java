package forsyde.io.lib.platform.runtime;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A TimeDivisionMultiplexingRuntime is capable of giving "slices" of time to the processes running on it.
 * The size of this slices are constant and are assumed to be contained within a certain range.
 * Moreover, all such runtimes also have a maximum "frame" in which it can schedule time slices for its processes.
 *
 * A frame size of 0 and a maximum time slice of 0 indicate that both are unbounded.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface TimeDivisionMultiplexingRuntime extends AbstractRuntime {

    @Property
    default Long minimumTimeSliceInClockCycles() {return 1L;}

    @Property
    default Long frameSizeInClockCycles() {return 0L;}

    @Property
    default Long maximumTimeSliceInClockCycles() {return frameSizeInClockCycles();}

    @Property
    default List<String> timeSliceProcess() {return new ArrayList<>();}

    @Property
    default List<Long> timeSlicesSizesInClockCycles() {return new ArrayList<>();}

}
