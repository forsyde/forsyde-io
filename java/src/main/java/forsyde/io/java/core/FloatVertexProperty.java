package forsyde.io.java.core;

import java.util.Optional;

@Deprecated
final public class FloatVertexProperty extends Number implements VertexPropertyElement {

    private float floatNum;

    public FloatVertexProperty(float i) {
        floatNum = i;
    }

    @Override
    public int intValue() {
        return (int) floatNum;
    }

    @Override
    public long longValue() {
        return (long) floatNum;
    }

    @Override
    public float floatValue() {
        return (float) floatNum;
    }

    @Override
    public double doubleValue() {
        return (double) floatNum;
    }

    @Override
    public boolean mergeInPlace(VertexPropertyElement other) {
        if (other instanceof FloatVertexProperty) {
            FloatVertexProperty numOther = (FloatVertexProperty) other;
            if (this.floatNum == numOther.floatNum) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Optional<VertexPropertyElement> merge(VertexPropertyElement other) {
        if (other instanceof FloatVertexProperty) {
            FloatVertexProperty numOther = (FloatVertexProperty) other;
            if (this.floatNum == numOther.floatNum) {
                return Optional.of(new FloatVertexProperty(floatNum));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Object unwrap() {
        return floatNum;
    }

}
