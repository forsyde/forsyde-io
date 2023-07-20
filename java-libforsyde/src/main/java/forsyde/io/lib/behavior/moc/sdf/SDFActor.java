package forsyde.io.lib.behavior.moc.sdf;

import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.core.annotations.WithEdgeTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.behavior.AlgorithmicEntity;
import forsyde.io.lib.behavior.moc.MoCEntity;

import java.util.Map;
import java.util.Set;

@RegisterTrait(IForSyDeHierarchy.class)
public interface SDFActor extends MoCEntity {

    @Property
    Map<String, Integer> consumption();

    @Property
    Map<String, Integer> production();

    @OutPort
    @WithEdgeTrait(SDFNetworkEdge.class)
    Set<AlgorithmicEntity> combinators();

}
