package forsyde.io.lib.platform.hardware;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.core.annotations.WithEdgeTrait;
import forsyde.io.lib.IForSyDeHierarchy;

import java.util.Set;

@RegisterTrait(IForSyDeHierarchy.class)
public interface Structure extends HardwareModule {

    @OutPort
    @WithEdgeTrait(StructuralContainment.class)
    Set<HardwareModule> containedModules();
}
