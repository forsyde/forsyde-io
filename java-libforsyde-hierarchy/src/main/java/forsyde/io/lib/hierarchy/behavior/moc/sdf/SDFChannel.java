package forsyde.io.lib.hierarchy.behavior.moc.sdf;

import forsyde.io.core.annotations.*;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.BehaviourCompositionEdge;
import forsyde.io.lib.hierarchy.behavior.DataTypeLike;
import forsyde.io.lib.hierarchy.behavior.moc.MoCEntity;

import java.util.Optional;

@RegisterTrait(IForSyDeHierarchy.class)
public interface SDFChannel extends MoCEntity {

    @Property
    default Integer numInitialTokens() {return 0;};
    @InPort
    @WithEdgeTrait(SDFNetworkEdge.class)
    Optional<SDFActor> producer();

    @OutPort
    @WithEdgeTrait(SDFNetworkEdge.class)
    Optional<SDFActor> consumer();

    @OutPort
    @WithEdgeTrait(BehaviourCompositionEdge.class)
    Optional<DataTypeLike> tokenDataType();

}
