/**
 */
package forsyde.io.eclipse.systemgraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Vertex Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link forsyde.io.eclipse.systemgraph.BooleanVertexProperty#isBooleanValue <em>Boolean Value</em>}</li>
 * </ul>
 *
 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getBooleanVertexProperty()
 * @model
 * @generated
 */
public interface BooleanVertexProperty extends VertexProperty {
	/**
	 * Returns the value of the '<em><b>Boolean Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Boolean Value</em>' attribute.
	 * @see #setBooleanValue(boolean)
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getBooleanVertexProperty_BooleanValue()
	 * @model unique="false"
	 * @generated
	 */
	boolean isBooleanValue();

	/**
	 * Sets the value of the '{@link forsyde.io.eclipse.systemgraph.BooleanVertexProperty#isBooleanValue <em>Boolean Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Boolean Value</em>' attribute.
	 * @see #isBooleanValue()
	 * @generated
	 */
	void setBooleanValue(boolean value);

} // BooleanVertexProperty
