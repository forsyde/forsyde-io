package forsyde.io.lib.hierarchy.behavior.execution;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

@RegisterTrait(IForSyDeHierarchy.class)
public interface Upsample extends Stimulatable, Stimulator {

    @Property
    default Long initialPredecessorHolds() {return 0L;};

    @Property
    default Long repetitivePredecessorHolds() {return 1L;};
}
