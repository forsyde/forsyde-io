/**
 *
 * $Id$
 */
package forsyde.io.eclipse.systemgraph.validation;

import forsyde.io.eclipse.systemgraph.VertexProperty;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link forsyde.io.eclipse.systemgraph.Vertex}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface VertexValidator {
	boolean validate();

	boolean validateIdentifier(String value);
	boolean validateTraits(EList<String> value);
	boolean validatePorts(EList<String> value);
	boolean validatePropertiesValues(EList<VertexProperty> value);
	boolean validatePropertiesNames(EList<String> value);
}
