package forsyde.io.lib.behavior.sy;

import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.behavior.AlgorithmicEntity;
import forsyde.io.lib.behavior.MoCEntity;

import java.util.Set;

@RegisterTrait(IForSyDeHierarchy.class)
public interface SYComb extends MoCEntity {

    @OutPort
    Set<AlgorithmicEntity> combinators();
}
