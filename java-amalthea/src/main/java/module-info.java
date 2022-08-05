module forsyde.io.java.amalthea {
    requires java.base;
    requires java.xml;

    requires org.eclipse.app4mc.amalthea.model;
    requires org.eclipse.emf.ecore;
    requires org.eclipse.emf.common;

    requires forsyde.io.java.core;
    requires org.apache.commons.lang3;

    exports forsyde.io.java.amalthea.adapters;
    exports forsyde.io.java.amalthea.adapters.mixins;
    exports forsyde.io.java.amalthea.drivers;

}