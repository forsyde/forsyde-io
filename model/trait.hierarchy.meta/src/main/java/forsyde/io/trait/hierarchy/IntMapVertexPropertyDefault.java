/**
 */
package forsyde.io.trait.hierarchy;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Int Map Vertex Property Default</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link IntMapVertexPropertyDefault#getValues <em>Values</em>}</li>
 *   <li>{@link IntMapVertexPropertyDefault#getIndexes <em>Indexes</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface IntMapVertexPropertyDefault extends VertexPropertyDefault {
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

	/**
	 * Returns the value of the '<em><b>Indexes</b></em>' attribute list.
	 * The list contents are of type {@link Integer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexes</em>' attribute list.
	 * @model unique="false"
	 * @generated
	 */
	EList<Integer> getIndexes();

} // IntMapVertexPropertyDefault
