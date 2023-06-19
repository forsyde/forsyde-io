module forsyde.io.java.generator {
    requires transitive forsyde.io.core;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires org.antlr.antlr4.runtime;
    requires com.squareup.javapoet;
    requires java.compiler;

    exports forsyde.io.java.generator;

}