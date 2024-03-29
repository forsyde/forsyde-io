package forsyde.io.lib.hierarchy.behavior.execution;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.InPort;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.core.annotations.WithEdgeTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.Set;

@RegisterTrait(IForSyDeHierarchy.class)
public interface Stimulatable extends VertexViewer {

    @InPort
    @WithEdgeTrait(EventEdge.class)
    Set<Stimulator> activators();

    @Property
    default Boolean hasORSemantics() {return false;}
}
