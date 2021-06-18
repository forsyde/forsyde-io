package forsyde.io.java.core;

import java.util.Optional;
import java.util.Set;

public interface EdgeInterface {
    public VertexInterface getSource();

    public VertexInterface getTarget();

    public Optional<String> getSourcePort();

    public Optional<String> getTargetPort();

    public void addTrait(Trait t);

    default void addTraits(Iterable<Trait> ts) {
        ts.forEach(t -> addTrait(t));
    }

    public Set<Trait> getTraits();
}
