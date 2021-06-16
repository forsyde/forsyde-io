package forsyde.io.java.core;

import java.util.Optional;

final public class LongVertexProperty extends Number implements VertexPropertyElement {

    private long longNum;

    public LongVertexProperty(long i) {
        longNum = i;
    }

    @Override
    public int intValue() {
        return (int) longNum;
    }

    @Override
    public long longValue() {
        return longNum;
    }

    @Override
    public float floatValue() {
        return (float) longNum;
    }

    @Override
    public double doubleValue() {
        return (double) longNum;
    }

    @Override
    public boolean mergeInPlace(VertexPropertyElement other) {
        if (other instanceof LongVertexProperty) {
            LongVertexProperty numOther = (LongVertexProperty) other;
            if (this.longNum == numOther.longNum) {
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
        if (other instanceof LongVertexProperty) {
            LongVertexProperty numOther = (LongVertexProperty) other;
            if (this.longNum == numOther.longNum) {
                return Optional.of(new LongVertexProperty(longNum));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Object unwrap() {
        return longNum;
    }

}
