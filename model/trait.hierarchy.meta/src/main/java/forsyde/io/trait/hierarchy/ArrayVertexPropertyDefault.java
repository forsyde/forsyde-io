/**
 */
package forsyde.io.trait.hierarchy;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Vertex Property Default</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ArrayVertexPropertyDefault#getValues <em>Values</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface ArrayVertexPropertyDefault extends VertexPropertyDefault {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * The list contents are of type {@link VertexPropertyDefault}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	EList<VertexPropertyDefault> getValues();

} // ArrayVertexPropertyDefault
