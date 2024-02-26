package forsyde.io.lib.hierarchy.behavior;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

/**
 * This trait is categorical and simply implies that the vertex is likely representative
 * of a data value, whether an array, a record, a composition of both etc.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface DataValueLike extends VertexViewer {
    
}
