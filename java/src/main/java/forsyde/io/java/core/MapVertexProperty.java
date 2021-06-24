package forsyde.io.java.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

final public class MapVertexProperty extends HashMap<String, VertexPropertyElement> implements VertexPropertyElement {

    @Override
    public boolean mergeInPlace(VertexPropertyElement other) {
        if (other instanceof MapVertexProperty) {
            boolean mergeDefined = true;
            MapVertexProperty otherMap = (MapVertexProperty) other;
            for (String key : otherMap.keySet()) {
                if (containsKey(key)) {
                    mergeDefined = get(key).mergeInPlace(otherMap.get(key));
                } else {
                    put(key, otherMap.get(key));
                }
            }
            return mergeDefined;
        } else {
            return false;
        }
    }

    @Override
    public Optional<VertexPropertyElement> merge(VertexPropertyElement other) {
        MapVertexProperty newOne = new MapVertexProperty();
        if (newOne.mergeInPlace(this) && newOne.mergeInPlace(other)) {
            return Optional.of(newOne);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Object unwrap() {
        HashMap<String, Object> unwrapped = new HashMap<>();
        this.forEach((k, v) -> unwrapped.put(k, v.unwrap()));
        return unwrapped;
    }

    static public MapVertexProperty fromConformingMapObject(Map<String, Object> i) {
        MapVertexProperty o = new MapVertexProperty();
        for (String k: i.keySet()) {
            Object value = i.get(k);
            if (value instanceof Integer) {
                o.put(k, new IntegerVertexProperty((Integer) value));
            } else if (value instanceof Long) {
                o.put(k, new LongVertexProperty((Long) value));
            } else if (value instanceof Float) {
                o.put(k, new FloatVertexProperty((Float) value));
            } else if (value instanceof Double) {
                o.put(k, new DoubleVertexProperty((Double) value));
            } else if (value instanceof Map) {
                o.put(k, MapVertexProperty.fromConformingMapObject((Map) value));
            } else if (value instanceof List) {
                o.put(k, ArrayVertexProperty.fromConformingList((List) value));
            } else if (value instanceof Boolean) {
                o.put(k, new BooleanVertexProperty((Boolean) value));
            } else {
                o.put(k, new StringVertexProperty(value.toString()));
            }
        }
        return o;
    }

}
