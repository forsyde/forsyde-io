package forsyde.io.java.sdf3.adapters;

import forsyde.io.java.adapters.ModelAdapter;
import forsyde.io.java.sdf3.adapters.mixins.SDFThree2ForSyDeMixin;
import forsyde.io.java.sdf3.adapters.mixins.elems.Sdf3;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class SDF3Adapter implements ModelAdapter<Sdf3>, SDFThree2ForSyDeMixin {

    protected Set<Pair<Object, Vertex>> equivalences = new HashSet<>();

    @Override
    public ForSyDeSystemGraph convert(Sdf3 inputModel) {
        final ForSyDeSystemGraph forSyDeSystemGraph = new ForSyDeSystemGraph();
        fromActorsToVertexes(inputModel, forSyDeSystemGraph);
        fromChannelstoSignalsAndPrefix(inputModel, forSyDeSystemGraph);
        fromChannelsToEdges(inputModel, forSyDeSystemGraph);
        fromChannelPropertiesToSDFChannels(inputModel, forSyDeSystemGraph);
        fromActorPropertiesToSDFActor(inputModel, forSyDeSystemGraph);
        return forSyDeSystemGraph;
    }

    @Override
    public Sdf3 convert(ForSyDeSystemGraph inputModel) {
        return null;
    }

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
