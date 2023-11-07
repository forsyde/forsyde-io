module forsyde.io.libforsyde.hierarchy {

    requires transitive forsyde.io.core;

    requires static forsyde.io.java.generator;

    exports forsyde.io.lib.hierarchy.behavior.basic;
    exports forsyde.io.lib.hierarchy.behavior.data;
    exports forsyde.io.lib.hierarchy.behavior.execution;
    exports forsyde.io.lib.hierarchy.behavior.moc.sdf;
    exports forsyde.io.lib.hierarchy.behavior.moc.sy;
    exports forsyde.io.lib.hierarchy.behavior.moc;
    exports forsyde.io.lib.hierarchy.behavior.parallel;
    exports forsyde.io.lib.hierarchy.behavior;
    exports forsyde.io.lib.hierarchy.constraints;
    exports forsyde.io.lib.hierarchy.decision.sdf;
    exports forsyde.io.lib.hierarchy.decision;
    exports forsyde.io.lib.hierarchy.implementation.code;
    exports forsyde.io.lib.hierarchy.implementation.functional;
    exports forsyde.io.lib.hierarchy.implementation.synthetizable;
    exports forsyde.io.lib.hierarchy.platform.hardware;
    exports forsyde.io.lib.hierarchy.platform.runtime;
    exports forsyde.io.lib.hierarchy.visualization;
    exports forsyde.io.lib.hierarchy;
}