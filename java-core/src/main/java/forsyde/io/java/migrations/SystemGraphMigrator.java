package forsyde.io.java.migrations;

import forsyde.io.java.core.ForSyDeSystemGraph;

public interface SystemGraphMigrator {

    boolean effect(ForSyDeSystemGraph forSyDeSystemGraph);

    String getName();
}
