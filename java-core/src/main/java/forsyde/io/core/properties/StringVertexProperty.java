package forsyde.io.core.properties;

import forsyde.io.core.VertexProperty;

final public class StringVertexProperty implements VertexProperty {

    public StringVertexProperty(String string) {
        this.string = string;
    }

    public String string;

    @Override public String toString() {
        return string;
    }
}
