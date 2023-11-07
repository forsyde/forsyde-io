package forsyde.io.lib.hierarchy.behavior.data;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.DataTypeLike;

/**
 * This trait captures a real-like datum.
 * By real-like, it means that it represents a real number, but the actual encoding is left open to
 * be present in refinements of this trait.
 * In other words, _do not assume_ that this trait implies that the vertex being viewed is a IEEE 754 floating point.
 * Rather, look what refinements exist for this trait or create another to properly capture the desired real number
 * encoding.
 *
 * The only property enforced in this trait is the total number of bits, which is universal across any real number
 * encoding.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface RealLike extends DataTypeLike {

    /**
     * The _total_ number of bits encoded in this representation.
     * If a particular encoding separates bits in different categories, include all the categories.
     * Using 32-bit IEEE 754 as an example, this number is 32, despite the sign bit, the 8 exponent and 23 fraction bits.
     */
    @Property
    Integer numberOfBits();
}
