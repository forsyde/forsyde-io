package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.execution.DownsampleReactiveStimulus;
import forsyde.io.java.typed.viewers.execution.PeriodicStimulusViewer;
import forsyde.io.java.typed.viewers.execution.ReactiveStimulus;
import org.eclipse.app4mc.amalthea.model.*;

import java.math.BigInteger;

import static org.eclipse.app4mc.amalthea.model.TimeUnit.*;

public interface ForSyDe2AmaltheaStimulusMixin extends EquivalenceModel2ModelMixin<Vertex, INamed> {

    default void fromStimulusToForSyDe(ForSyDeSystemGraph forSyDeSystemGraph, Amalthea amalthea) {
        final StimuliModel stimuliModel = AmaltheaFactory.eINSTANCE.createStimuliModel();
        amalthea.setStimuliModel(stimuliModel);
        forSyDeSystemGraph.vertexSet().stream().filter(forsyde.io.java.typed.viewers.execution.Stimulus::conforms).forEach(vertex -> {
            // check periodic stimulus
            forsyde.io.java.typed.viewers.execution.PeriodicStimulus.safeCast(vertex).ifPresent(vPeriodicStimulus -> {
                final PeriodicStimulus periodicStimulus = AmaltheaFactory.eINSTANCE.createPeriodicStimulus();
                periodicStimulus.setName(vPeriodicStimulus.getIdentifier());

                final Time time = AmaltheaFactory.eINSTANCE.createTime();
                time.setUnit(fromLongToTimeUnit(vPeriodicStimulus.getPeriodDenominator()));
                time.setValue(BigInteger.valueOf(vPeriodicStimulus.getPeriodNumerator()));
                periodicStimulus.setRecurrence(time);

                final Time offset = AmaltheaFactory.eINSTANCE.createTime();
                offset.setUnit(fromLongToTimeUnit(vPeriodicStimulus.getOffsetDenominator()));
                offset.setValue(BigInteger.valueOf(vPeriodicStimulus.getOffsetNumerator()));
                periodicStimulus.setOffset(offset);

                stimuliModel.getStimuli().add(periodicStimulus);
                addEquivalence(vertex, periodicStimulus);
            });
            // check for precendeces (inter task stimulus)
            ReactiveStimulus.safeCast(vertex).ifPresent(reactiveStimulus -> {
                final InterProcessStimulus interProcessStimulus = AmaltheaFactory.eINSTANCE.createInterProcessStimulus();
                interProcessStimulus.setName(reactiveStimulus.getIdentifier());

                stimuliModel.getStimuli().add(interProcessStimulus);
                addEquivalence(vertex, interProcessStimulus);
            });
        });
//        amalthea.getStimuliModel().getStimuli().forEach((stimulus) -> {
//            if (stimulus instanceof PeriodicStimulus) {
//                final PeriodicStimulus periodicStimulus = (PeriodicStimulus) stimulus;
//                final Vertex timerVertex = new Vertex(stimulus.getName(), VertexTrait.EXECUTION_PERIODICSTIMULUS);
//                timerVertex.ports.add("stimulated");
//                forSyDeSystemGraph.addVertex(timerVertex);
//                final forsyde.io.java.typed.viewers.execution.PeriodicStimulus periodicStimulusVertex = new PeriodicStimulusViewer(timerVertex);
//                if (periodicStimulus.getRecurrence() != null) {
//                    periodicStimulusVertex.setPeriodNumerator(periodicStimulus.getRecurrence().getValue().longValue());
//                    periodicStimulusVertex.setPeriodDenominator(fromTimeUnitToLong(periodicStimulus.getRecurrence().getUnit()));
//                } else {
//                    periodicStimulusVertex.setPeriodNumerator(1L);
//                    periodicStimulusVertex.setPeriodDenominator(1L);
//                }
//                if (periodicStimulus.getOffset() != null) {
//                    periodicStimulusVertex.setOffsetNumerator(periodicStimulus.getOffset().getValue().longValue());
//                    periodicStimulusVertex.setOffsetDenominator(fromTimeUnitToLong(periodicStimulus.getOffset().getUnit()));
//                } else {
//                    periodicStimulusVertex.setOffsetNumerator(0L);
//                    periodicStimulusVertex.setOffsetDenominator(1L);
//                }
//                addEquivalence(stimulus, timerVertex);
//            } else if (stimulus instanceof InterProcessStimulus) {
//                final InterProcessStimulus interProcessStimulus = (InterProcessStimulus) stimulus;
//                final Vertex precedenceVertex = new Vertex(stimulus.getName());
//                final ReactiveStimulus reactiveStimulus = ReactiveStimulus.enforce(precedenceVertex);
//                forSyDeSystemGraph.addVertex(precedenceVertex);
//                addEquivalence(interProcessStimulus, precedenceVertex);
//                if (interProcessStimulus.getCounter() != null) {
//                    final DownsampleReactiveStimulus repetitionReactiveStimulus = DownsampleReactiveStimulus.enforce(precedenceVertex);
//                    repetitionReactiveStimulus.setInitialPredecessorSkips(interProcessStimulus.getCounter().getOffset());
//                    repetitionReactiveStimulus.setRepetitivePredecessorSkips(interProcessStimulus.getCounter().getPrescaler());
//                }
//            }
//        });
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

    private TimeUnit fromLongToTimeUnit(long denominator) {
        if (denominator <= 1L) return S;
        else if (denominator <= 1000L) return MS;
        else if (denominator <= 1000000L) return US;
        else if (denominator <= 1000000000L) return NS;
        else if (denominator <= 1000000000000L) return PS;
        else return S;
    }

}
