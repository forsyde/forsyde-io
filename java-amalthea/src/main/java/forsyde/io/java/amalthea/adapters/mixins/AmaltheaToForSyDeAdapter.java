package forsyde.io.java.amalthea.adapters.mixins;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.Vertex;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.app4mc.amalthea.model.Amalthea;
import org.eclipse.app4mc.amalthea.model.INamed;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class AmaltheaToForSyDeAdapter implements AmaltheaOS2ForSyDeMixin, AmaltheaHW2ForSyDeMixin,
        AmaltheaSW2ForSyDeMixin, AmaltheaStimulus2ForSyDeMixin, AmaltheaConstraints2ForSyDeMixin, AmaltheaMapping2ForSyDeMixin {

    protected Set<Pair<INamed, Vertex>> equivalences = new HashSet<>();

    public void convert(Amalthea input, SystemGraph output) {
        fromStimulusToForSyDe(input, output);
        fromHWtoForSyDe(input, output);
        fromSWToForSyDe(input, output);
        fromOSToForSyDe(input, output);
        fromConstraintsToForSyDe(input, output);
        fromMappingToForSyDe(input, output);
    }

    @Override
    public Stream<Vertex> equivalents(INamed source) {
        return equivalences.stream().filter(p -> p.getLeft().equals(source)).map(Pair::getRight);
    }

    @Override
    public void addEquivalence(INamed source, Vertex elem) {
        equivalences.add(Pair.of(source, elem));
    }


    @Override
    public boolean contains(Vertex elem) {
        return equivalences.stream().anyMatch(p -> p.getRight().equals(elem));
    }

    @Override
    public boolean hasEquivalent(INamed elem) {
        return equivalences.stream().anyMatch(p -> p.getLeft().equals(elem));
    }

}
