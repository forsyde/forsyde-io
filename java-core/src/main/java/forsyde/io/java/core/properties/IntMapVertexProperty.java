package forsyde.io.java.core.properties;

import forsyde.io.java.core.VertexProperty;

import java.util.Map;
import java.util.stream.Collectors;

final public class IntMapVertexProperty implements VertexProperty {

    public IntMapVertexProperty(Map<Integer, VertexProperty> intMap) {
        this.intMap = intMap;
    }

    public Map<Integer, VertexProperty> intMap;

    @Override public String toString() {
        return "{" + intMap.entrySet().stream().map(e -> e.getKey().toString() + ": " + e.getValue().toString()).collect(Collectors.joining(", ")) + "}";
    }
}
