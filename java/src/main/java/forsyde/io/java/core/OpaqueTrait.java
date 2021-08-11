package forsyde.io.java.core;

public class OpaqueTrait implements Trait, CharSequence {

    private final String opaqueTraitName;

    public OpaqueTrait(String s) {
        opaqueTraitName = s;
    }

    @Override
    public int length() {
        return opaqueTraitName.length();
    }

    @Override
    public char charAt(int index) {
        return opaqueTraitName.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return opaqueTraitName.subSequence(start, end);
    }

    @Override
    public boolean refines(Trait other) {
        return false;
    }

    @Override
    public String getName() {
        return opaqueTraitName;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Trait) {
            return this.getName().equals(((Trait) other).getName());
        }
        return false;
    }

}
