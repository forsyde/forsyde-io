package forsyde.io.java.core.properties;

import forsyde.io.java.core.VertexProperty;

final public class StringVertexProperty implements VertexProperty {

    public StringVertexProperty(String string) {
        this.string = string;
    }

    public String string;

    @Override public String toString() {
        return string;
    }
}
