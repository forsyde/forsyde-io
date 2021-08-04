package forsyde.io.java.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

final public class ArrayVertexProperty extends ArrayList<VertexPropertyElement> implements VertexPropertyElement {

    public ArrayVertexProperty(int initLenth) {
        super(initLenth);
    }

    @Override
    public boolean mergeInPlace(VertexPropertyElement other) {
        if (other instanceof ArrayVertexProperty) {
            boolean mergeDefined = true;
            ArrayVertexProperty arrayOther = (ArrayVertexProperty) other;
            Iterator<VertexPropertyElement> otherIt = arrayOther.iterator();
            Iterator<VertexPropertyElement> thisIt = this.iterator();
            while (otherIt.hasNext() && thisIt.hasNext()) {
                mergeDefined = mergeDefined && thisIt.next().mergeInPlace(otherIt.next());
            }
            // if other is bigger than this
            otherIt.forEachRemaining(e -> this.add(e));
            return mergeDefined;
        } else {
            return false;
        }
    }

    @Override
    public Optional<VertexPropertyElement> merge(VertexPropertyElement other) {
        ArrayVertexProperty newOne = new ArrayVertexProperty(this.size());
        if (newOne.mergeInPlace(this) && newOne.mergeInPlace(other)) {
            return Optional.of(newOne);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Object unwrap() {
        ArrayList<Object> unwrapped = new ArrayList<>(this.size());
        this.forEach(v -> v.unwrap());
        return unwrapped;
    }

    static public ArrayVertexProperty fromConformingList(List<? extends Object> i) {
        ArrayVertexProperty o = new ArrayVertexProperty(i.size());
        for (Object value : i) {
            if (value instanceof Integer) {
                o.add(new IntegerVertexProperty((Integer) value));
            } else if (value instanceof Long) {
                o.add(new LongVertexProperty((Long) value));
            } else if (value instanceof Float) {
                o.add(new FloatVertexProperty((Float) value));
            } else if (value instanceof Double) {
                o.add(new DoubleVertexProperty((Double) value));
            } else if (value instanceof Map) {
                o.add(MapVertexProperty.fromConformingMapObject((Map) value));
            } else if (value instanceof List) {
                o.add(ArrayVertexProperty.fromConformingList((List) value));
            } else if (value instanceof Boolean) {
                o.add(new BooleanVertexProperty((Boolean) value));
            } else {
                o.add(new StringVertexProperty(value.toString()));
            }
        }
        return o;
    }

}
