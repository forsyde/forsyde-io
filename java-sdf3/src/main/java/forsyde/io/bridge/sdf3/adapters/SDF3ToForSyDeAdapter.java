package forsyde.io.bridge.sdf3.adapters;

import forsyde.io.bridge.sdf3.adapters.mixins.SDFThree2ForSyDeMixin;
import forsyde.io.core.Vertex;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class SDF3ToForSyDeAdapter implements SDFThree2ForSyDeMixin {

    protected Set<Pair<Object, Vertex>> equivalences = new HashSet<>();
    @Override
    public Stream<Vertex> equivalents(Object source) {
        return equivalences.stream().filter(pair -> pair.getLeft().equals(source)).map(Pair::getRight);
    }

    @Override
    public void addEquivalence(Object source, Vertex elem) {
        equivalences.add(Pair.of(source, elem));
    }

    @Override
    public boolean contains(Vertex elem) {
        return equivalences.stream().anyMatch(pair -> pair.getRight().equals(elem));
    }

    @Override
    public boolean hasEquivalent(Object elem) {
        return equivalences.stream().anyMatch(pair -> pair.getLeft().equals(elem));
    }
}
