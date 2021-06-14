package forsyde.io.java.core;

import java.util.function.BooleanSupplier;

final public class BooleanVertexProperty implements VertexPropertyElement, BooleanSupplier {

    private boolean innerBoolean;

    public BooleanVertexProperty(boolean i) {
        innerBoolean = i;
    }

    @Override
    public boolean getAsBoolean() {
        return innerBoolean;
    }
    
}
