package forsyde.io.java.core;

final public class StringVertexProperty implements VertexPropertyElement, CharSequence {

    private String innerString;

    public StringVertexProperty(String i) {
        innerString = i;
    }

    @Override
    public int length() {
        return innerString.length();
    }

    @Override
    public char charAt(int index) {
        return innerString.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return innerString.subSequence(start, end);
    }
    
    @Override
    public String toString() {
        return innerString;
    }
}
