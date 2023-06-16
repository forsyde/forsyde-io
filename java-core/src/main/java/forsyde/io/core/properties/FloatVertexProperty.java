package forsyde.io.core.properties;

import forsyde.io.core.VertexProperty;

final public class FloatVertexProperty implements VertexProperty {

    public FloatVertexProperty(float floatValue) {
        this.floatValue = floatValue;
    }

    public float floatValue;

    @Override public String toString() {
        return String.valueOf(floatValue);
    }
}
