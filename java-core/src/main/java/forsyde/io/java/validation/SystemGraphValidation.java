package forsyde.io.java.validation;

import forsyde.io.java.core.ForSyDeSystemGraph;

import java.util.Optional;

/**
 * This interface validates system graphs in memory for possible
 * problems and return an exception when a problem
 * is encountered.
 */
public interface SystemGraphValidation {

    Optional<String> validate(ForSyDeSystemGraph forSyDeSystemGraph);
}
