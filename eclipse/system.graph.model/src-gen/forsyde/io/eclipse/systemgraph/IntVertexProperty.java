/**
 */
package forsyde.io.eclipse.systemgraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Int Vertex Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link forsyde.io.eclipse.systemgraph.IntVertexProperty#getIntValue <em>Int Value</em>}</li>
 * </ul>
 *
 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getIntVertexProperty()
 * @model
 * @generated
 */
public interface IntVertexProperty extends VertexProperty {
	/**
	 * Returns the value of the '<em><b>Int Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Int Value</em>' attribute.
	 * @see #setIntValue(int)
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getIntVertexProperty_IntValue()
	 * @model unique="false"
	 * @generated
	 */
	int getIntValue();

	/**
	 * Sets the value of the '{@link forsyde.io.eclipse.systemgraph.IntVertexProperty#getIntValue <em>Int Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Int Value</em>' attribute.
	 * @see #getIntValue()
	 * @generated
	 */
	void setIntValue(int value);

} // IntVertexProperty
