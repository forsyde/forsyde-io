package forsyde.io.lib.hierarchy.behavior.data;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.DataTypeLike;

/**
 * This trait captures datum like strings.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface StringLike extends DataTypeLike {
    
    /**
     * A property that describes the encoding that this String like data type.
     * Likely entries here are "UTF8", "UTF16" etc. The default is "UTF8".
     */
    @Property
    default String encoding() {
        return "UTF8";
    }

    /**
     * If larger than 0, it signals the maximum length of characters in this string.
     * This can be larger than the number of bytes and bits necessary depending on the encoding.
     */
    @Property
    default Integer maximumLength() {
        return -1;
    }
}
