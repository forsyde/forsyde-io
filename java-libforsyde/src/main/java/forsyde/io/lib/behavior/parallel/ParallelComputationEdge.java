package forsyde.io.lib.behavior.parallel;

import forsyde.io.core.EdgeTrait;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

/**
 * This edge symbolizes the movement of parallelizable data.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface ParallelComputationEdge extends EdgeTrait {
}
