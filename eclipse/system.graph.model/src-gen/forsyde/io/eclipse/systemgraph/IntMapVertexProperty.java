/**
 */
package forsyde.io.eclipse.systemgraph;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Int Map Vertex Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link forsyde.io.eclipse.systemgraph.IntMapVertexProperty#getValues <em>Values</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.IntMapVertexProperty#getIndexes <em>Indexes</em>}</li>
 * </ul>
 *
 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getIntMapVertexProperty()
 * @model
 * @generated
 */
public interface IntMapVertexProperty extends VertexProperty {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * The list contents are of type {@link forsyde.io.eclipse.systemgraph.VertexProperty}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getIntMapVertexProperty_Values()
	 * @model containment="true"
	 * @generated
	 */
	EList<VertexProperty> getValues();

	/**
	 * Returns the value of the '<em><b>Indexes</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexes</em>' attribute list.
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getIntMapVertexProperty_Indexes()
	 * @model unique="false"
	 * @generated
	 */
	EList<Integer> getIndexes();

} // IntMapVertexProperty
