package forsyde.io.core.migrations;

import forsyde.io.core.SystemGraph;

public interface SystemGraphMigrator {

    boolean effect(SystemGraph systemGraph);

    default String getName() {
        return getClass().getCanonicalName();
    };

    class SystemGraphMigrationException extends Exception {

        public SystemGraphMigrationException(String s) {
            super(s);
        }
    }
}
