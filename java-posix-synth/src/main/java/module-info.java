module forsyde.io.java.posix.synth {

    requires transitive forsyde.io.core;
    requires transitive forsyde.io.libforsyde;
    requires transitive info.picocli;

    requires org.ainslec.picocog;

    exports forsyde.io.posix.synth;
}