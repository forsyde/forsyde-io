package forsyde.io.lib.behavior.moc.sdf;

import forsyde.io.core.annotations.InPort;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.behavior.moc.MoCEntity;

import java.util.Optional;

@RegisterTrait(IForSyDeHierarchy.class)
public interface SDFChannel extends MoCEntity {

    @Property
    default Integer numInitialTokens() {return 0;};
    @InPort
    Optional<SDFActor> producer();

    @OutPort
    Optional<SDFActor> consumer();

}
