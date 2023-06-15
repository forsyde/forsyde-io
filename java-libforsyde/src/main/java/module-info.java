module forsyde.io.java.libforsyde {

    requires transitive forsyde.io.java.core;

    requires static forsyde.io.java.generator;

    exports forsyde.io.lib;
    exports forsyde.io.lib.behavior.sdf;
    exports forsyde.io.lib.visualization;
}