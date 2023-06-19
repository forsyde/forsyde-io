package forsyde.io.lib.implementation.functional;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

@RegisterTrait(IForSyDeHierarchy.class)
public interface ArrayBufferLike extends BufferLike{

    @Property
    Integer maxElements();

    default Long elementSize() {
        return maxBufferSize() / maxElements().longValue();
    }
}
