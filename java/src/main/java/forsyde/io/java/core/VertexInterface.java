package forsyde.io.java.core;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface VertexInterface {

    public Map<String, VertexPropertyElement> getProperties();

    public Set<String> getPorts();

    public String getIdentifier();

    public Set<Trait> getTraits();

    public void addTrait(Trait t);

    default void addTraits(Iterable<Trait> ts) {
        ts.forEach(t -> addTrait(t));
    };

    public boolean mergeInPlace(VertexInterface other);

    public Optional<Vertex> merge(VertexInterface other);
}
