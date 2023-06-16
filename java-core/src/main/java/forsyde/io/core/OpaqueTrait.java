package forsyde.io.core;

import java.util.Objects;

/**
 * An OpaqueTrait is a Trait which has no fixed semantic value in the tool chain yet,
 * but can be used as any other Trait, devoid of getters, setters etc.
 *
 * In a sense, OpaqueTrait's are custom Traits which are not part of the source
 * code yet.
 */
public final class OpaqueTrait implements Trait, CharSequence, EdgeTrait, VertexTrait {

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
        return opaqueTraitName.equals(other.getName());
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

    @Override
    public int hashCode() {
        return Objects.hash(opaqueTraitName);
    }
}
