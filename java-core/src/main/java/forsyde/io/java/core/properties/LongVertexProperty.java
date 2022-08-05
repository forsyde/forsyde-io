package forsyde.io.java.core.properties;

import forsyde.io.java.core.VertexProperty;

final public class LongVertexProperty implements VertexProperty {

    public LongVertexProperty(long longValue) {
        this.longValue = longValue;
    }

    public long longValue;

    @Override public String toString() {
        return String.valueOf(longValue);
    }
}
