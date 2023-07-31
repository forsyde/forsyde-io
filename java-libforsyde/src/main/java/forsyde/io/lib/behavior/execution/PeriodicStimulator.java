package forsyde.io.lib.behavior.execution;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

@RegisterTrait(IForSyDeHierarchy.class)
public interface PeriodicStimulator extends Stimulator {

    @Property
    Long periodNumerator();

    @Property
    default Long periodDenominator() {return 1L;};

    @Property
    default Long offsetNumerator() {return 0L;};

    @Property
    default Long offsetDenominator() {return 1L;};
}
