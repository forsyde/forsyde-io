package forsyde.io.lib.hierarchy.behavior.execution;

import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

@RegisterTrait(IForSyDeHierarchy.class)
public interface Task extends Stimulator, Stimulatable{
}
