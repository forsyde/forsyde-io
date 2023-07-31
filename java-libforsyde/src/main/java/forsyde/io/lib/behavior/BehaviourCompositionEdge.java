package forsyde.io.lib.behavior;


import forsyde.io.core.EdgeTrait;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

/**
 * This edge trait symbolizes a going up or down one composition level.
 * For example, in a ForSyDe Synchronous process network, all the processes have functions inside
 * them that "combine" the input to the output. Going from the process network level _down_ to the functions
 * is a composition of behaviours; this edge trait is used to refers to such connections.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface BehaviourCompositionEdge extends EdgeTrait {
}
