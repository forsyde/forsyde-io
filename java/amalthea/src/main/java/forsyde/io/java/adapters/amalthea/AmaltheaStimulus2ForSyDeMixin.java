package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.execution.PeriodicStimulusViewer;
import org.eclipse.app4mc.amalthea.model.Amalthea;
import org.eclipse.app4mc.amalthea.model.INamed;
import org.eclipse.app4mc.amalthea.model.PeriodicStimulus;
import org.eclipse.app4mc.amalthea.model.TimeUnit;

public interface AmaltheaStimulus2ForSyDeMixin extends EquivalenceModel2ModelMixin<INamed, Vertex> {

    default void stimulusToVertexes(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        amalthea.getStimuliModel().getStimuli().forEach((stimulus) -> {
            if (stimulus instanceof PeriodicStimulus) {
                final PeriodicStimulus periodicStimulus = (PeriodicStimulus) stimulus;
                final Vertex timerVertex = new Vertex(stimulus.getName(), VertexTrait.EXECUTION_PERIODICSTIMULUS);
                forSyDeSystemGraph.addVertex(timerVertex);
                final forsyde.io.java.typed.viewers.execution.PeriodicStimulus periodicStimulusVertex = new PeriodicStimulusViewer(timerVertex);
                if (periodicStimulus.getRecurrence() != null) {
                    periodicStimulusVertex.setPeriodNumerator(periodicStimulus.getRecurrence().getValue().longValue());
                    periodicStimulusVertex.setPeriodDenominator(fromTimeUnitToLong(periodicStimulus.getRecurrence().getUnit()));
                } else {
                    periodicStimulusVertex.setPeriodNumerator(1L);
                    periodicStimulusVertex.setPeriodDenominator(1L);
                }
                if (periodicStimulus.getOffset() != null) {
                    periodicStimulusVertex.setOffsetNumerator(periodicStimulus.getOffset().getValue().longValue());
                    periodicStimulusVertex.setOffsetDenominator(fromTimeUnitToLong(periodicStimulus.getOffset().getUnit()));
                } else {
                    periodicStimulusVertex.setOffsetNumerator(0L);
                    periodicStimulusVertex.setOffsetDenominator(1L);
                }
                addEquivalence(stimulus, timerVertex);
            }
        });
    }

    private long fromTimeUnitToLong(TimeUnit timeUnit) {
        switch (timeUnit) {
            case S: return 1L;
            case MS: return 1000L;
            case US: return 1000000L;
            case NS: return 1000000000L;
            //case 1000000000000: return TimeUnit.PS;
            default: return 1000000000000L;
        }
    }

}
