/**
 */
package forsyde.io.eclipse.systemgraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Long Vertex Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link forsyde.io.eclipse.systemgraph.LongVertexProperty#getLongValue <em>Long Value</em>}</li>
 * </ul>
 *
 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getLongVertexProperty()
 * @model
 * @generated
 */
public interface LongVertexProperty extends VertexProperty {
	/**
	 * Returns the value of the '<em><b>Long Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Long Value</em>' attribute.
	 * @see #setLongValue(long)
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getLongVertexProperty_LongValue()
	 * @model unique="false"
	 * @generated
	 */
	long getLongValue();

	/**
	 * Sets the value of the '{@link forsyde.io.eclipse.systemgraph.LongVertexProperty#getLongValue <em>Long Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Long Value</em>' attribute.
	 * @see #getLongValue()
	 * @generated
	 */
	void setLongValue(long value);

} // LongVertexProperty
