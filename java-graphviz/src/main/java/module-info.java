module forsyde.io.java.graphviz {
    requires java.base;
    requires java.xml;

    requires guru.nidi.graphviz;
    requires org.ainslec.picocog;
    requires de.cau.cs.kieler.klighd;
    requires de.cau.cs.kieler.klighd.kgraph;
    requires de.cau.cs.kieler.klighd.krendering;
    requires de.cau.cs.kieler.klighd.standalone;

    requires transitive forsyde.io.core;
    requires transitive forsyde.io.libforsyde;


    exports forsyde.io.visual.graphviz;
    exports forsyde.io.visual.kgt.drivers;
}