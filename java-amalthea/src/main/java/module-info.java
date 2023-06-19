module forsyde.io.java.amalthea {
    requires java.base;
    requires java.xml;

    requires org.eclipse.app4mc.amalthea.model;
    requires org.eclipse.emf.ecore;
    requires org.eclipse.emf.common;

    requires transitive forsyde.io.core;
    requires transitive forsyde.io.libforsyde;
    requires org.apache.commons.lang3;

    exports forsyde.io.java.amalthea.adapters;
    exports forsyde.io.java.amalthea.adapters.mixins;
    exports forsyde.io.java.amalthea.drivers;

}