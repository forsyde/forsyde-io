package forsyde.io.lib.hierarchy.behavior.execution;


import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.behavior.BehaviourEntity;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.Set;

@RegisterTrait(IForSyDeHierarchy.class)
public interface LoopingTask extends Task {

    @OutPort
    Set<BehaviourEntity> initSequence();

    @OutPort
    Set<BehaviourEntity> loopSequence();
}
