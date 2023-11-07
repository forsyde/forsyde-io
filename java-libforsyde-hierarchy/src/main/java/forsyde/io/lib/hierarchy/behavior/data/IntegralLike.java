package forsyde.io.lib.hierarchy.behavior.data;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.DataTypeLike;

/**
 * This trait captures an integral datum type.
 * That is, this is an integer with any number of encoding bits.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface IntegralLike extends DataTypeLike {

    /**
     * Denotes if the integer encoding also captures negative integers.
     * Unless specifically noted, the signed integer representation is two's complement.
     */
    @Property
    default Boolean isSigned() {
        return true;
    }

    /**
     * The number of bits in this encoding.
     * For example, if the vertex being veiwed is a 32-bit integer variable, this number is 32.
     * There is a high chance that this number os always powers of 2, as most comercial off-the-shelf machines
     * are built around these, but the range is _open_ so that a designer is able to use any number they desire
     * to seek a balance between performance vs precision.
     */
    @Property
    Integer numberOfBits();
}
