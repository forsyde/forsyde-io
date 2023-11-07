package forsyde.io.lib.hierarchy.behavior.data;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

/**
 * This trait captures a 32 bit IEEE 754 floating point number.
 */

@RegisterTrait(IForSyDeHierarchy.class)
public interface IEEE754Binary64FloatingPoint extends RealLike {

    @Override
    @Property
    default Integer numberOfBits() {
        return 64;
    }
}
