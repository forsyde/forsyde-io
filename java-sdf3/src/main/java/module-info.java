module forsyde.io.java.sdfThree {
    requires java.base;
    requires java.xml;

    requires jakarta.xml.bind;
    requires org.glassfish.jaxb.runtime;
    requires org.apache.commons.lang3;

    requires transitive forsyde.io.core;
    requires transitive forsyde.io.libforsyde;

    exports forsyde.io.bridge.sdf3.adapters;
    exports forsyde.io.bridge.sdf3.adapters.mixins;
    exports forsyde.io.bridge.sdf3.drivers;
}