package forsyde.io.lib.hierarchy.behavior.moc.sy;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.moc.MoCEntity;

import java.util.List;

/**
 * A SY process is either a SY combination or a SY delay.
 * This is enough to build _any_ single-rate synchronous system, no matter how unwieldy.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface SYProcess extends MoCEntity {

    @Property
    List<String> outputPorts();

    @Property
    List<String> inputPorts();

}
