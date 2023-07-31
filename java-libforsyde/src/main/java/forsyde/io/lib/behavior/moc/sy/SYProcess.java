package forsyde.io.lib.behavior.moc.sy;

import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.behavior.moc.MoCEntity;

/**
 * A SY process is either a SY combination or a SY delay.
 * This is enough to build _any_ single-rate synchronous system, no matter how unwieldy.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface SYProcess extends MoCEntity {

}
