package forsyde.io.java.core;

import java.util.Optional;

final public class NumberVertexProperty extends Number implements VertexPropertyElement {
    
    private int intNum;
    private long longNum;
    private float floatNum;
    private double doubleNum;
    public NumberVertexPropertyType numType;

    public NumberVertexProperty(int i) {
        intNum = i;
        numType = NumberVertexPropertyType.INTEGER;
    }

    public NumberVertexProperty(long i) {
        longNum = i;
        numType = NumberVertexPropertyType.LONG;
    }

    public NumberVertexProperty(float i) {
        floatNum = i;
        numType = NumberVertexPropertyType.FLOAT;
    }

    public NumberVertexProperty(double i) {
        doubleNum = i;
        numType = NumberVertexPropertyType.DOUBLE;
    }

    public boolean isInt() {
        return numType == NumberVertexPropertyType.INTEGER;
    }

    public boolean isLong() {
        return numType == NumberVertexPropertyType.LONG;
    }

    public boolean isFloat() {
        return numType == NumberVertexPropertyType.FLOAT;
    }

    public boolean isDouble() {
        return numType == NumberVertexPropertyType.DOUBLE;
    }

    @Override
    public int intValue() {
        switch (numType) {
            case DOUBLE:
                return (int) doubleNum;
            case LONG:
                return (int) longNum;
            case FLOAT:
                return (int) floatNum;
            case INTEGER:
                return (int) intNum;
        }
        return intNum;
    }

    @Override
    public long longValue() {
        switch (numType) {
            case DOUBLE:
                return (long) doubleNum;
            case LONG:
                return (long) longNum;
            case FLOAT:
                return (long) floatNum;
            case INTEGER:
                return (long) intNum;
        }
        return longNum;
    }

    @Override
    public float floatValue() {
        switch (numType) {
            case DOUBLE:
                return (float) doubleNum;
            case LONG:
                return (float) longNum;
            case FLOAT:
                return (float) floatNum;
            case INTEGER:
                return (float) intNum;
        }
        return floatNum;
    }

    @Override
    public double doubleValue() {
        switch (numType) {
            case DOUBLE:
                return (double) doubleNum;
            case LONG:
                return (double) longNum;
            case FLOAT:
                return (double) floatNum;
            case INTEGER:
                return (double) intNum;
        }
        return doubleNum;
    }

    protected enum NumberVertexPropertyType {
        INTEGER,
        LONG,
        FLOAT,
        DOUBLE
    }

    @Override
    public boolean mergeInPlace(VertexPropertyElement other) {
        if (other instanceof NumberVertexProperty) {
            NumberVertexProperty numOther = (NumberVertexProperty) other;
            if (this.isInt() && numOther.isInt() && this.intNum == numOther.intNum) {
                return true;
            } else if (this.isDouble() && numOther.isDouble() && this.doubleNum == numOther.doubleNum) {
                return true;
            } else if (this.isFloat() && numOther.isFloat() && this.floatNum == numOther.floatNum) {
                return true;
            } else if (this.isLong() && numOther.isLong() && this.longNum == numOther.longNum) {
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
        if (other instanceof NumberVertexProperty) {
        NumberVertexProperty numOther = (NumberVertexProperty) other;
            if (this.isInt() && numOther.isInt() && this.intNum == numOther.intNum) {
                return Optional.of(new NumberVertexProperty(intNum));
            } else if (this.isDouble() && numOther.isDouble() && this.doubleNum == numOther.doubleNum) {
                return Optional.of(new NumberVertexProperty(doubleNum));
            } else if (this.isFloat() && numOther.isFloat() && this.floatNum == numOther.floatNum) {
                return Optional.of(new NumberVertexProperty(floatNum));
            } else if (this.isLong() && numOther.isLong() && this.longNum == numOther.longNum) {
                return Optional.of(new NumberVertexProperty(longNum));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

}
