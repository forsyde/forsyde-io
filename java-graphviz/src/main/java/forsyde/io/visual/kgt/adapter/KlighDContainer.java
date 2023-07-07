package forsyde.io.visual.kgt.adapter;

import org.ainslec.picocog.PicoWriter;

import java.util.Set;

public class KlighDContainer {

    private final Set<KlighDNodeView> roots;

    public KlighDContainer(Set<KlighDNodeView> roots) {
        this.roots = roots;
    }

    public Set<KlighDNodeView> getRoots() {
        return roots;
    }

    public void write(PicoWriter picoWriter) {
        picoWriter.writeln_r("knode forsyde {");
        // topWriter.writeln("krectangle");
        picoWriter.writeln("klabel \"ForSyDe Model\"");
        for (KlighDNodeView root : getRoots()) {
            root.write(picoWriter.createDeferredWriter());
        }
        picoWriter.writeln_l("}");
    }
}
