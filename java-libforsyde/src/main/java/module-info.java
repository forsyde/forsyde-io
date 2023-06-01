module forsyde.io.java.libforsyde {

    requires transitive forsyde.io.java.core;

    requires static forsyde.io.java.generator;

    exports forsyde.io.java.libforsyde.behavior.sdf;
    exports forsyde.io.java.libforsyde.visualization;
}