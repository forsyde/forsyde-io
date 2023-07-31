/**
 */
package forsyde.io.eclipse.systemgraph;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Vertex Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link forsyde.io.eclipse.systemgraph.ArrayVertexProperty#getValues <em>Values</em>}</li>
 * </ul>
 *
 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getArrayVertexProperty()
 * @model
 * @generated
 */
public interface ArrayVertexProperty extends VertexProperty {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * The list contents are of type {@link forsyde.io.eclipse.systemgraph.VertexProperty}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getArrayVertexProperty_Values()
	 * @model containment="true"
	 * @generated
	 */
	EList<VertexProperty> getValues();

} // ArrayVertexProperty
