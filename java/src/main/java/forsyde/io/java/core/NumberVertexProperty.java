package forsyde.io.java.core;

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
        return intNum;
    }

    @Override
    public long longValue() {
        return longNum;
    }

    @Override
    public float floatValue() {
        return floatNum;
    }

    @Override
    public double doubleValue() {
        return doubleNum;
    }

    protected enum NumberVertexPropertyType {
        INTEGER,
        LONG,
        FLOAT,
        DOUBLE
    }

}
