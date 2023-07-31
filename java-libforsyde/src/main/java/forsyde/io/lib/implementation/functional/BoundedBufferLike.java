package forsyde.io.lib.implementation.functional;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

/**
 * A BoundedBuffer is a refinement of the potentially infinite BufferLike queue.
 * It adds a maximum number of elements for this buffer which must be respects at all times.
 * It also enables nice implementations in software and hardware, e.g. using circular buffers.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface BoundedBufferLike extends BufferLike{

    @Property
    Integer maxElements();

    default Long maxSizeInBits() {
        return elementSizeInBits() * maxElements().longValue();
    }
}
