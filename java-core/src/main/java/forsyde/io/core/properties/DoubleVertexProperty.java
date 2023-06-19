package forsyde.io.core.properties;

import forsyde.io.core.VertexProperty;

final public class DoubleVertexProperty implements VertexProperty {

    public DoubleVertexProperty(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public double doubleValue;

    @Override public String toString() {
        return String.valueOf(doubleValue);
    }
}
