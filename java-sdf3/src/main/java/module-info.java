module forsyde.io.java.sdfThree {
    requires java.base;
    requires java.xml;

    requires jakarta.xml.bind;
    requires org.glassfish.jaxb.runtime;
    requires org.apache.commons.lang3;

    requires forsyde.io.core;

    exports forsyde.io.java.sdf3.adapters;
    exports forsyde.io.java.sdf3.adapters.mixins;
    exports forsyde.io.java.sdf3.drivers;
}