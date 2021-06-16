package forsyde.io.java.core;

import java.util.Optional;

final public class DoubleVertexProperty extends Number implements VertexPropertyElement {

    private double doubleNum;

    public DoubleVertexProperty(double i) {
        doubleNum = i;
    }

    @Override
    public int intValue() {
        return (int) doubleNum;

    }

    @Override
    public long longValue() {
        return (long) doubleNum;

    }

    @Override
    public float floatValue() {
        return (float) doubleNum;

    }

    @Override
    public double doubleValue() {
        return (double) doubleNum;

    }

    @Override
    public boolean mergeInPlace(VertexPropertyElement other) {
        if (other instanceof DoubleVertexProperty) {
            DoubleVertexProperty numOther = (DoubleVertexProperty) other;
            if (this.doubleNum == numOther.doubleNum) {
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
        if (other instanceof DoubleVertexProperty) {
            DoubleVertexProperty numOther = (DoubleVertexProperty) other;
            if (this.doubleNum == numOther.doubleNum) {
                return Optional.of(new DoubleVertexProperty(doubleNum));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Object unwrap() {
        return doubleNum;
    }

}
