package forsyde.io.java.core;

import java.util.HashMap;
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

}
