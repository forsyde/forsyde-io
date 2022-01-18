/**
 */
package forsyde.io.trait.hierarchy;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Map Vertex Property Default</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link StringMapVertexPropertyDefault#getValues <em>Values</em>}</li>
 *   <li>{@link StringMapVertexPropertyDefault#getIndexes <em>Indexes</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface StringMapVertexPropertyDefault extends IntVertexPropertyDefault {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * The list contents are of type {@link IntVertexPropertyDefault}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	EList<IntVertexPropertyDefault> getValues();

	/**
	 * Returns the value of the '<em><b>Indexes</b></em>' attribute list.
	 * The list contents are of type {@link String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexes</em>' attribute list.
	 * @model unique="false"
	 * @generated
	 */
	EList<String> getIndexes();

} // StringMapVertexPropertyDefault
