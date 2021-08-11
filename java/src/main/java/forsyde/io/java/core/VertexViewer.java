package forsyde.io.java.core;

import java.util.Map;
import java.util.Set;

public interface VertexViewer {

    default String getIdentifier() {
        return getViewedVertex().getIdentifier();
    };

    default Set<String> getPorts() {
        return getViewedVertex().getPorts();
    };

    default Set<Trait> getTraits() {
        return getViewedVertex().getTraits();
    };

    default Map<String, VertexProperty> getProperties() {
        return getViewedVertex().getProperties();
    }
    
    Vertex getViewedVertex();
}
