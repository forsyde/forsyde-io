package forsyde.io.java.core;

public interface TraitHierarchy {

    boolean refines(Trait subTrait, Trait superType);

    boolean containsTrait(Trait trait);

    Trait fromName(String traitName);

    default boolean containsTrait(String traitName) {
        return containsTrait(fromName(traitName));
    }

    default boolean refines(String subTraitName, String superTraitName) {
        return refines(fromName(subTraitName), fromName(superTraitName));
    }
}
