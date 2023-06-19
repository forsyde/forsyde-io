module forsyde.io.libforsyde {

    requires transitive forsyde.io.core;

    requires static forsyde.io.java.generator;

    exports forsyde.io.lib;
    exports forsyde.io.lib.behavior;
    exports forsyde.io.lib.behavior.basic;
    exports forsyde.io.lib.behavior.sdf;
    exports forsyde.io.lib.visualization;
}