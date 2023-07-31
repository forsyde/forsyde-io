package forsyde.io.lib.platform.runtime;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

import java.util.ArrayList;
import java.util.List;

/**
 * A super loop runtime captures the entry-point for programmable devices that have no runtime at all, i.e. almost or completely bare-metal.
 * These runtimes will generally represent "superloop" approaches, where the processes being scheduled are inside
 * a big while loop that runs forever, executing the processes unconditionally.
 * This does not exclude the fact that a process might stall its processing element while waiting for data or any
 * other activation condition.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface SuperLoopRuntime extends AbstractRuntime {

    @Property
    default List<String> superLoopEntries() {return new ArrayList<>();}

}
