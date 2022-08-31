module forsyde.io.java.core {
    requires java.base;
    requires java.xml;

    requires static java.compiler;

    requires transitive org.jgrapht.core;
    requires transitive org.jgrapht.io;

    requires org.antlr.antlr4.runtime;
    requires jakarta.xml.bind;
    requires org.glassfish.jaxb.runtime;

//    requires static lombok;

    exports forsyde.io.java.core;
    exports forsyde.io.java.adapters;
    exports forsyde.io.java.drivers;
    exports forsyde.io.java.migrations;
    exports forsyde.io.java.validation;
    // generated elements
    exports forsyde.io.java.typed.viewers.execution;
    exports forsyde.io.java.typed.viewers.decision;
    exports forsyde.io.java.typed.viewers.decision.results;
    exports forsyde.io.java.typed.viewers.decision.sdf;
    exports forsyde.io.java.typed.viewers.typing;
    exports forsyde.io.java.typed.viewers.typing.datatypes;
    exports forsyde.io.java.typed.viewers.moc;
    exports forsyde.io.java.typed.viewers.moc.sdf;
    exports forsyde.io.java.typed.viewers.moc.linguafranca;
    exports forsyde.io.java.typed.viewers.impl;
    exports forsyde.io.java.typed.viewers.nonfunctional;
    exports forsyde.io.java.typed.viewers.parallel;
    exports forsyde.io.java.typed.viewers.platform.runtime;
    exports forsyde.io.java.typed.viewers.platform;
    exports forsyde.io.java.typed.viewers.values;
    exports forsyde.io.java.typed.viewers.visualization;
    exports forsyde.io.java.typed.viewers.decision.platform.runtime;

    exports forsyde.io.java.typed.edges.execution;
    exports forsyde.io.java.typed.edges.decision;
    exports forsyde.io.java.typed.edges.typing.datatypes;
    exports forsyde.io.java.typed.edges.moc;
    exports forsyde.io.java.typed.edges.moc.sdf;
    exports forsyde.io.java.typed.edges.moc.linguafranca;
    exports forsyde.io.java.typed.edges.impl;
    exports forsyde.io.java.typed.edges.parallel;
    exports forsyde.io.java.typed.edges.platform;
    exports forsyde.io.java.typed.edges.visualization;


}