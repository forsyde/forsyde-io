package forsyde.io.lib.hierarchy.visualization;

import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.core.annotations.WithEdgeTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.Set;

@RegisterTrait(IForSyDeHierarchy.class)
public interface GreyBox extends Visualizable {

    @OutPort
    @WithEdgeTrait(VisualContainment.class)
    Set<Visualizable> contained();
}
