package meta.moc;

import java.util.List;
import java.util.Set;

import meta.ForwardPort;

public interface LinguaFrancaReactor extends LinguaFrancaElement {

    @ForwardPort
    Set<LinguaFrancaTimer> timers();

    @ForwardPort
    Set<LinguaFrancaReaction> reactions();

    List<String> stateNames = List.of();

}
