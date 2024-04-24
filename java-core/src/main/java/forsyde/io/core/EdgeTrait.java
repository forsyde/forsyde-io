package forsyde.io.core;

import java.util.List;

public interface EdgeTrait extends Trait {

    default List<VertexTrait> allowedSourceTraits() {
        return List.of();
    }

    default List<VertexTrait> allowedTargetTraits() {
        return List.of();
    }
}
