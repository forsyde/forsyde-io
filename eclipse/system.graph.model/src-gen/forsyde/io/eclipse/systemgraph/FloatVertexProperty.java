/**
 */
package forsyde.io.eclipse.systemgraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Float Vertex Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link forsyde.io.eclipse.systemgraph.FloatVertexProperty#getFloatValue <em>Float Value</em>}</li>
 * </ul>
 *
 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getFloatVertexProperty()
 * @model
 * @generated
 */
public interface FloatVertexProperty extends VertexProperty {
	/**
	 * Returns the value of the '<em><b>Float Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Float Value</em>' attribute.
	 * @see #setFloatValue(float)
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getFloatVertexProperty_FloatValue()
	 * @model unique="false"
	 * @generated
	 */
	float getFloatValue();

	/**
	 * Sets the value of the '{@link forsyde.io.eclipse.systemgraph.FloatVertexProperty#getFloatValue <em>Float Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Float Value</em>' attribute.
	 * @see #getFloatValue()
	 * @generated
	 */
	void setFloatValue(float value);

} // FloatVertexProperty
