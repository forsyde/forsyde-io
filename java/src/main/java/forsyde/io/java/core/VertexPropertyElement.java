package forsyde.io.java.core;

import java.util.Optional;

@Deprecated
public interface VertexPropertyElement {

    boolean mergeInPlace(VertexPropertyElement other);

    Object unwrap();

    Optional<VertexPropertyElement> merge(VertexPropertyElement other);
}
