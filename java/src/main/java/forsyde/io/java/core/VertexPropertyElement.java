package forsyde.io.java.core;

import java.util.Optional;

public interface VertexPropertyElement {
    
    boolean mergeInPlace(VertexPropertyElement other);

    Optional<VertexPropertyElement> merge(VertexPropertyElement other);
}
