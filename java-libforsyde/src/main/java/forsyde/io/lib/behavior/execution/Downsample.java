package forsyde.io.lib.behavior.execution;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

@RegisterTrait(IForSyDeHierarchy.class)
public interface Downsample extends Stimulatable, Stimulator {

    @Property
    default Long initialPredecessorSkips() {return 0L;};

    @Property
    default Long repetitivePredecessorSkips() {return 1L;};
}
