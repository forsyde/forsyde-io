/**
 */
package forsyde.io.eclipse.systemgraph;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Map Vertex Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link forsyde.io.eclipse.systemgraph.StringMapVertexProperty#getValues <em>Values</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.StringMapVertexProperty#getIndexes <em>Indexes</em>}</li>
 * </ul>
 *
 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getStringMapVertexProperty()
 * @model
 * @generated
 */
public interface StringMapVertexProperty extends IntVertexProperty {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * The list contents are of type {@link forsyde.io.eclipse.systemgraph.IntVertexProperty}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getStringMapVertexProperty_Values()
	 * @model containment="true"
	 * @generated
	 */
	EList<IntVertexProperty> getValues();

	/**
	 * Returns the value of the '<em><b>Indexes</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexes</em>' attribute list.
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getStringMapVertexProperty_Indexes()
	 * @model unique="false"
	 * @generated
	 */
	EList<String> getIndexes();

} // StringMapVertexProperty
