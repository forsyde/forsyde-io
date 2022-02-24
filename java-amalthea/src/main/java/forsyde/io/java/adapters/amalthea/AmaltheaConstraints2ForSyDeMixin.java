package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.execution.ConstrainedTask;
import forsyde.io.java.typed.viewers.execution.Task;
import org.eclipse.app4mc.amalthea.model.*;

import java.lang.System;

public interface AmaltheaConstraints2ForSyDeMixin extends EquivalenceModel2ModelMixin<INamed, Vertex> {

    default void fromConstraintsToForSyDe(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        if (amalthea.getConstraintsModel() != null) {
            amalthea.getConstraintsModel().getRequirements().forEach(req -> {
                if (req instanceof ProcessRequirement) {
                    final ProcessRequirement processRequirement = (ProcessRequirement) req;
                    equivalents(processRequirement.getProcess()).flatMap(v -> Task.safeCast(v).stream()).forEach(processVertex -> {
                        // now look inside all contents of the req to figure out
                        // if they are deadlines or others stuff
                        processRequirement.eAllContents().forEachRemaining(item -> {
                            if (item instanceof TimeRequirementLimit) {
                                final TimeRequirementLimit timeRequirementLimit = (TimeRequirementLimit) item;
                                final ConstrainedTask constrainedTask = ConstrainedTask.enforce(processVertex);
                                constrainedTask.setRelativeDeadlineDenominator(
                                        timeRequirementLimit.getLimitValue() != null ?
                                                fromTimeUnitToLong(timeRequirementLimit.getLimitValue().getUnit())
                                                : 1L);
                                constrainedTask.setRelativeDeadlineNumerator(
                                        timeRequirementLimit.getLimitValue() != null ?
                                                timeRequirementLimit.getLimitValue().getValue().longValue()
                                                : 1L);
                            }
                        });
                    });
                }
            });
        }
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
