package forsyde.io.lib.hierarchy.behavior;

import forsyde.io.core.EdgeTrait;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

/**
 * This edge trait connects a data like entity to other children that define it, e.g. fields of a recrod.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface DataLikeCompositionEdge extends EdgeTrait {
}
