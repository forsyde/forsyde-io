package forsyde.io.java.core;

import java.util.Set;

public interface TraitHierarchy {

    Trait fromName(String traitName);

    Set<Trait> traits();

}
