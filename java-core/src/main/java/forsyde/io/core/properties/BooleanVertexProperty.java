package forsyde.io.core.properties;

import forsyde.io.core.VertexProperty;

final public class BooleanVertexProperty implements VertexProperty {

    public BooleanVertexProperty(boolean boolValue) {
        this.boolValue = boolValue;
    }

    public boolean boolValue;

    @Override public String toString() {
        return String.valueOf(boolValue);
    }
}
