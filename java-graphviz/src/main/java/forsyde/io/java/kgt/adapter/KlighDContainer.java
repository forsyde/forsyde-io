package forsyde.io.java.kgt.adapter;

import java.util.Set;

public class KlighDContainer {

    private final Set<KlighDNodeView> roots;

    public KlighDContainer(Set<KlighDNodeView> roots) {
        this.roots = roots;
    }

    public Set<KlighDNodeView> getRoots() {
        return roots;
    }
}
