package forsyde.io.java.core;

import java.util.Optional;
import java.util.function.BooleanSupplier;

@Deprecated
final public class BooleanVertexProperty implements VertexPropertyElement, BooleanSupplier {

    private boolean innerBoolean;

    public BooleanVertexProperty(boolean i) {
        innerBoolean = i;
    }

    @Override
    public boolean getAsBoolean() {
        return innerBoolean;
    }

    @Override
    public boolean mergeInPlace(VertexPropertyElement other) {
        if (other instanceof BooleanVertexProperty) {
            BooleanVertexProperty boolOther = (BooleanVertexProperty) other;
            return this.innerBoolean == boolOther.innerBoolean;
        } else {
            return false;
        }
    }

    @Override
    public Optional<VertexPropertyElement> merge(VertexPropertyElement other) {
        BooleanVertexProperty newOne = new BooleanVertexProperty(this.getAsBoolean());
        if (newOne.mergeInPlace(other)) {
            return Optional.of(newOne);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Object unwrap() {
        return innerBoolean;
    }

}
