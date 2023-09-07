package forsyde.io.lib.hierarchy.behavior.moc.sy;


import forsyde.io.core.annotations.InPort;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.core.annotations.WithEdgeTrait;
import forsyde.io.lib.hierarchy.behavior.BehaviourCompositionEdge;
import forsyde.io.lib.hierarchy.behavior.DataTypeLike;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.moc.MoCEntity;

import java.util.Optional;
import java.util.Set;

/**
 * An SY signal expresses a link between different SY processes in a SY network.
 * To ensure that the SY model is well-defined, all signals can only have one producer,
 * and multiple producers. This means that the consumers get a copy of whatever data is produced
 * onto this signal at every discrete step.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface SYSignal extends MoCEntity {

    @InPort
    @WithEdgeTrait(SYNetworkEdge.class)
    SYProcess producer();

    @OutPort
    @WithEdgeTrait(SYNetworkEdge.class)
    Set<SYProcess> consumers();

    @OutPort
    @WithEdgeTrait(BehaviourCompositionEdge.class)
    Optional<DataTypeLike> dataType();

}
