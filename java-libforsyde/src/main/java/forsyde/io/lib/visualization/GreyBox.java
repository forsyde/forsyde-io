package forsyde.io.lib.visualization;

import forsyde.io.java.core.VertexViewer;
import forsyde.io.java.core.annotations.GenerateViewer;
import forsyde.io.java.core.annotations.OutPort;
import forsyde.io.java.core.annotations.RegisterTrait;
import forsyde.io.java.core.annotations.WithEdgeTrait;
import forsyde.io.lib.IForSyDeHierarchy;

import java.util.Set;

@RegisterTrait(IForSyDeHierarchy.class)
public interface GreyBox extends Visualizable {

    @OutPort
    @WithEdgeTrait(VisualContainment.class)
    Set<Visualizable> contained();
}
