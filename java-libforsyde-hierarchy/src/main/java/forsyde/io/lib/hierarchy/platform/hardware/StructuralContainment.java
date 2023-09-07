package forsyde.io.lib.hierarchy.platform.hardware;

import forsyde.io.core.EdgeTrait;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

/**
 * A StructuralContainment represnts a structural hierarchy marker.
 * This edge trait is used in conjunction with the `Structure` vertex trait to distinguish
 * between "virtual" edges in the plaform, which only symbolize human comprehension aid, to actual
 * physical links that are important for analysis and synthesis.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface StructuralContainment extends EdgeTrait {
}
