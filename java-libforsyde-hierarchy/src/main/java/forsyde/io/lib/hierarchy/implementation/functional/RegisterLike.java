package forsyde.io.lib.hierarchy.implementation.functional;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

/**
 * A RegisterLike element is one that holds a piece of data until it is overwritten.
 * That is, this represents a register in hardware or a memory location/variable in software.
 * Keep in mind that this trait assumes that all reads and writes to the register-like element
 * are done as a single element. For example, if this represents a NxM matrix, reading and writing
 * to this register-like element entails reading or writing _the entire matrix_.
 *
 * For a more array-like behavior, check the "RegisterArrayLike" trait.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface RegisterLike extends VertexViewer {

    @Property
    Long sizeInBits();
}
