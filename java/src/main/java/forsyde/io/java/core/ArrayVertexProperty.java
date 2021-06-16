package forsyde.io.java.core;

import java.util.ArrayList;
import java.util.Iterator;
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

}
