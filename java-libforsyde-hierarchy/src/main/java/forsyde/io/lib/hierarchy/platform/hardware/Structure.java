package forsyde.io.lib.hierarchy.platform.hardware;

import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.core.annotations.WithEdgeTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.Set;

/**
 * A structure is simply a collection of platform elements without
 * any specific meaning. It is helpful to create hierarchies in the platform
 * for "categorization" but it does not imply any semantic hierarchy by itself.
 *
 * Structures have outgoing edges to its children, or contained, hardware modules.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface Structure extends HardwareModule {

    /**
     * Returns the contained modules for this Structure vertex.
     */
    @OutPort
    @WithEdgeTrait(StructuralContainment.class)
    Set<HardwareModule> containedModules();
}
