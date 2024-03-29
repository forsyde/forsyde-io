package forsyde.io.lib.hierarchy.implementation.functional;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;


/**
 * This trait refines "RegisterLike" by ensuring that the register-like component now holds
 * chunkable data. In software this could mean that we have a random-access array where its entries
 * can be read and overwritten at will; in hardware this could mean a literal array of registers that can
 * be read and written at will. Be sure to also read the "RegisterLike" documentation to understand what this
 * is refining.
 *
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface RegisterArrayLike extends RegisterLike {

    @Property
    Long elementSizeInBits();

    default Integer maxNumberOfElements() {
        return (int) (sizeInBits() / elementSizeInBits());
    }
}
