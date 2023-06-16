module forsyde.io.core {
    requires java.base;
    requires java.xml;

    requires static java.compiler;

    requires transitive org.jgrapht.core;

    requires org.antlr.antlr4.runtime;
    requires jakarta.xml.bind;
    requires org.glassfish.jaxb.runtime;

//    requires static lombok;

    exports forsyde.io.core;
    exports forsyde.io.java.adapters;
    exports forsyde.io.core.drivers;
    exports forsyde.io.core.migrations;
    exports forsyde.io.core.validation;
    exports forsyde.io.core.annotations;

}