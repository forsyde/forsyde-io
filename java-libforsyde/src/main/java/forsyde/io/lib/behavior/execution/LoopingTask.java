package forsyde.io.lib.behavior.execution;


import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.behavior.AlgorithmicEntity;

import java.util.List;

@RegisterTrait(IForSyDeHierarchy.class)
public interface LoopingTask extends Task {

    @OutPort
    List<AlgorithmicEntity> initSequence();

    @OutPort
    List<AlgorithmicEntity> loopSequence();
}
