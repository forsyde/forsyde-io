package forsyde.io.lib.behavior.sdf;

import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.behavior.AlgorithmicEntity;
import forsyde.io.lib.behavior.MoCEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RegisterTrait(IForSyDeHierarchy.class)
public interface SDFActor extends MoCEntity {

    @Property
    Map<String, Integer> consumption();

    @Property
    Map<String, Integer> production();

    @OutPort
    Set<AlgorithmicEntity> combinators();

}
