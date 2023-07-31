package forsyde.io.lib.behavior.execution;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.core.annotations.WithEdgeTrait;
import forsyde.io.lib.IForSyDeHierarchy;

import java.util.Set;

@RegisterTrait(IForSyDeHierarchy.class)
public interface Stimulator extends VertexViewer {

    @OutPort
    @WithEdgeTrait(EventEdge.class)
    Set<Stimulatable> activated();
}
