package forsyde.io.core.inference;

import forsyde.io.core.SystemGraph;

/**
 * This interface is very much in line with a validation, except the semantics are different.
 * An inference is simply a way to add deducible information in the model in case it does not exits.
 * It does not imply any validation or equivalent.
 */
public interface SystemGraphInference {

    void infer(SystemGraph systemGraph);

    String getName();
}
