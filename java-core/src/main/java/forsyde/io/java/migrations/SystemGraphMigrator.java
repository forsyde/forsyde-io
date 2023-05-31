package forsyde.io.java.migrations;

import forsyde.io.java.core.SystemGraph;

public interface SystemGraphMigrator {

    boolean effect(SystemGraph systemGraph);

    String getName();
}
