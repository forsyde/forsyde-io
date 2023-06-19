package forsyde.io.lib.behavior.execution;

import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

@RegisterTrait(IForSyDeHierarchy.class)
public interface Task extends Stimulator, Stimulatable{
}
