package forsyde.io.core;

import java.util.Set;

public interface VertexTrait extends Trait{

    default Set<String> requiredPorts() {
        return Set.of();
    };

    default Set<String> requiredProperties() {
        return Set.of();
    };
}
