package forsyde.io.lib.behavior.execution;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;


/**
 * Once this trait is connected to a stimulatable element,
 * this element becomes perpetually read to be executed after "initialLatency" seconds.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface ContinousStimulator extends Stimulator {

    @Property
    default Long initialLatencyNumerator() {return 0L;}

    @Property
    default Long initialLatencyDenominator() {return 0L;}
}
