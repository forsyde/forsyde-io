package forsyde.io.java.sdf3.adapters;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.sdf3.adapters.mixins.ForSyDe2SDFThreeMixin;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ForSyDeToSDF3Adapter implements ForSyDe2SDFThreeMixin {

    protected Set<Pair<Vertex, Object>> equivalences = new HashSet<>();

    @Override
    public Stream<Object> equivalents(Vertex source) {
        return equivalences.stream().filter(pair -> pair.getLeft().equals(source)).map(Pair::getRight);
    }

    @Override
    public void addEquivalence(Vertex source, Object elem) {
        equivalences.add(Pair.of(source, elem));
    }

    @Override
    public boolean contains(Object elem) {
        return equivalences.stream().anyMatch(pair -> pair.getRight().equals(elem));
    }

    @Override
    public boolean hasEquivalent(Vertex elem) {
        return equivalences.stream().anyMatch(pair -> pair.getLeft().equals(elem));
    }
}
