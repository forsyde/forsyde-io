package forsyde.io.core.properties;

import forsyde.io.core.VertexProperty;

import java.util.Map;
import java.util.stream.Collectors;

final public class StringMapVertexProperty implements VertexProperty {

    public StringMapVertexProperty(Map<String, VertexProperty> strMap) {
        this.strMap = strMap;
    }

    public Map<String, VertexProperty> strMap;

    @Override public String toString() {
        return "{" + strMap.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue().toString()).collect(Collectors.joining(", ")) + "}";
    }
}
