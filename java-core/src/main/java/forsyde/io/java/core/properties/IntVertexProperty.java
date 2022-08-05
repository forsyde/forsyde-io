package forsyde.io.java.core.properties;

import forsyde.io.java.core.VertexProperty;

final public class IntVertexProperty implements VertexProperty {

    public IntVertexProperty(int intValue) {
        this.intValue = intValue;
    }

    public int intValue;

    @Override public String toString() {
        return String.valueOf(intValue);
    }
}
