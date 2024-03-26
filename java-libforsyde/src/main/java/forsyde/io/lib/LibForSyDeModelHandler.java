package forsyde.io.lib;

import forsyde.io.core.ModelHandler;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;

/**
 * This is an utility class which injects into a {@link forsyde.io.core.ModelHandler} the necessary
 * migrators, infereneces and validators associated with the LibForSyDe trait hierarchy and utilities.
 * use {@link #registerLibForSyDe(ModelHandler)} to effect such standard injection.
 */
public class LibForSyDeModelHandler {

    private LibForSyDeModelHandler() {}

    public static ModelHandler registerLibForSyDe(ModelHandler modelHandler) {
        modelHandler.registerTraitHierarchy(new ForSyDeHierarchy());
        modelHandler.registerSystemGraphMigrator(new TraitNamesFrom0_6To0_7());
        modelHandler.registerSystemGraphMigrator(new SDFActorsWithFunctionMigrator());
        modelHandler.registerSystemGraphMigrator(new InstrumentationRenamingMigration());
        modelHandler.registerSystemGraphMigrator(new AnalyzedBehaviourThroughputNameMigration());
        modelHandler.registerValidation(new SDFValidator());
        modelHandler.registerValidation(new InstrumentedSoftwareBehaviourValidator());
        modelHandler.registerInference(new ComputationalRequirementsPropagator());
        modelHandler.registerInference(new MemoryRequirementsPropagator());
        modelHandler.registerInference(new FunctionLikePortsInference());
        modelHandler.registerInference(new EnforceBasicDataTypeConstraintsInference());
        return modelHandler;
    }
}
