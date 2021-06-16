package forsyde.io.java.core;

import java.util.Optional;

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

    @Override
    public boolean mergeInPlace(VertexPropertyElement other) {
        if (other instanceof StringVertexProperty) {
            StringVertexProperty otherString = (StringVertexProperty) other;
            return this.innerString.equals(otherString.innerString);
        }
        {
            return false;
        }
    }

    @Override
    public Optional<VertexPropertyElement> merge(VertexPropertyElement other) {
        StringVertexProperty newOne = new StringVertexProperty(this.innerString);
        if (newOne.mergeInPlace(other)) {
            return Optional.of(newOne);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Object unwrap() {
        return innerString;
    }
}
