/**
 */
package forsyde.io.eclipse.systemgraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Double Vertex Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link forsyde.io.eclipse.systemgraph.DoubleVertexProperty#getDoubleValue <em>Double Value</em>}</li>
 * </ul>
 *
 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getDoubleVertexProperty()
 * @model
 * @generated
 */
public interface DoubleVertexProperty extends VertexProperty {
	/**
	 * Returns the value of the '<em><b>Double Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Double Value</em>' attribute.
	 * @see #setDoubleValue(double)
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getDoubleVertexProperty_DoubleValue()
	 * @model unique="false"
	 * @generated
	 */
	double getDoubleValue();

	/**
	 * Sets the value of the '{@link forsyde.io.eclipse.systemgraph.DoubleVertexProperty#getDoubleValue <em>Double Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Double Value</em>' attribute.
	 * @see #getDoubleValue()
	 * @generated
	 */
	void setDoubleValue(double value);

} // DoubleVertexProperty
