package forsyde.io.java.core;

import java.util.Optional;

@Deprecated
final public class IntegerVertexProperty extends Number implements VertexPropertyElement {

    private int intNum;

    public IntegerVertexProperty(int i) {
        intNum = i;
    }

    @Override
    public int intValue() {
        return intNum;
    }

    @Override
    public long longValue() {
        return (long) intNum;
    }

    @Override
    public float floatValue() {
        return (float) intNum;
    }

    @Override
    public double doubleValue() {
        return (double) intNum;
    }

    @Override
    public boolean mergeInPlace(VertexPropertyElement other) {
        if (other instanceof IntegerVertexProperty) {
            IntegerVertexProperty numOther = (IntegerVertexProperty) other;
            if (intNum == numOther.intValue()) {
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
        if (other instanceof IntegerVertexProperty) {
            IntegerVertexProperty numOther = (IntegerVertexProperty) other;
            if (intNum == numOther.intValue()) {
                return Optional.of(new IntegerVertexProperty(intNum));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Object unwrap() {
        return intNum;
    }

}