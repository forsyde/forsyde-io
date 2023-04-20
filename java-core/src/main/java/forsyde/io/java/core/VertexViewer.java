package forsyde.io.java.core;

import java.util.Map;
import java.util.Set;

public interface VertexViewer {
	
    Vertex getViewedVertex();

    default String getIdentifier() {
        return getViewedVertex().getIdentifier();
    };

    default Set<String> getPorts() {
        return getViewedVertex().getPorts();
    }

    default void addPorts(String... strings) {
        getViewedVertex().addPorts(strings);
    }

    default Set<Trait> getTraits() {
        return getViewedVertex().getTraits();
    };

    default Map<String, Object> getProperties() {
        return getViewedVertex().getProperties();
    }

}
