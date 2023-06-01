module forsyde.io.java.graphviz {
    requires java.base;
    requires java.xml;

    requires guru.nidi.graphviz;
    requires org.ainslec.picocog;

    requires forsyde.io.java.core;
    requires forsyde.io.java.libforsyde;

    exports forsyde.io.java.graphviz.drivers;
    exports forsyde.io.java.kgt.drivers;
}