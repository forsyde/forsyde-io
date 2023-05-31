module forsyde.io.java.core {
    requires java.base;
    requires java.xml;

    requires static java.compiler;

    requires transitive org.jgrapht.core;

    requires org.antlr.antlr4.runtime;
    requires jakarta.xml.bind;
    requires org.glassfish.jaxb.runtime;

//    requires static lombok;

    exports forsyde.io.java.core;
    exports forsyde.io.java.adapters;
    exports forsyde.io.java.drivers;
    exports forsyde.io.java.migrations;
    exports forsyde.io.java.validation;
    exports forsyde.io.java.core.annotations;

}