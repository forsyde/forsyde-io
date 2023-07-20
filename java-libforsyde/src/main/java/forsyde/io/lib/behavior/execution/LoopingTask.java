package forsyde.io.lib.behavior.execution;


import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.behavior.BehaviourEntity;

import java.util.Set;

@RegisterTrait(IForSyDeHierarchy.class)
public interface LoopingTask extends Task {

    @OutPort
    Set<BehaviourEntity> initSequence();

    @OutPort
    Set<BehaviourEntity> loopSequence();
}
