package forsyde.io.lib.behavior.moc.sdf;

import forsyde.io.core.annotations.*;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.behavior.moc.MoCEntity;

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

}
