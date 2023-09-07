package forsyde.io.java.generator;

import forsyde.io.java.generator.specs.TraitHierarchySpec;

public class ForSyDeIOMetaMerger {

    /**
     * This function merges all given trait hierarchies into one. If just one is given,
     * it will merge its children. Thus, this function also works to 'compact' one trait hierarchy
     * @param hierarchies input hierarchies
     */
    void mergeInPlace(TraitHierarchySpec... hierarchies) {
        for (int i = 1; i < hierarchies.length; i++) {

        }
    }

}
