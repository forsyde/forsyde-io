package forsyde.io.java.core;

import java.util.Set;

public interface EdgeTrait extends Trait {

    default Set<String> allowedSourceTraits() {
        return Set.of();
    }

    default Set<String> allowedTargetTraits() {
        return Set.of();
    }
}
