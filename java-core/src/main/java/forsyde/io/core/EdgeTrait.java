package forsyde.io.core;

import java.util.List;
import java.util.Set;

public interface EdgeTrait extends Trait {

    default List<VertexTrait> allowedSourceTraits() {
        return List.of();
    }

    default List<VertexTrait> allowedTargetTraits() {
        return List.of();
    }
}
