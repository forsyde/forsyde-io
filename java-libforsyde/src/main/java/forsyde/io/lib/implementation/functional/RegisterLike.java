package forsyde.io.lib.implementation.functional;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

@RegisterTrait(IForSyDeHierarchy.class)
public interface RegisterLike extends VertexViewer {

    @Property
    Long sizeInBits();
}
