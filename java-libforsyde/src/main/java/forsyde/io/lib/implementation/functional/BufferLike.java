package forsyde.io.lib.implementation.functional;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

/**
 * A BufferLike element is one that is capable of queuing an unbounded amount of data.
 * This then represents a queue of "dynamic" size.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface BufferLike extends VertexViewer {

    @Property
    Long elementSizeInBits();

}
