package forsyde.io.core.properties;

import forsyde.io.core.VertexProperty;

import java.util.List;
import java.util.stream.Collectors;

final public class ArrayVertexProperty implements VertexProperty {
    public List<VertexProperty> values;

    public ArrayVertexProperty(List<VertexProperty> values) {
        this.values = values;
    }

    @Override public String toString() {
        return "[" + values.stream().map(VertexProperty::toString).collect(Collectors.joining(", ")) + "]";
    }
}
