package forsyde.io.lib.hierarchy.decision;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.platform.hardware.GenericMemoryModule;

/**
 * This trait simply adds to a memory element information post-mapping about its usage.
 * For example, how much memory of the available element has been used by mapped software.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface UtilizedMemoryElement extends GenericMemoryModule {

    @Property
    default long utilizedSpaceInBits() {
        return 0L;
    }
}
