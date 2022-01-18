/**
 */
package forsyde.io.trait.hierarchy;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Map Vertex Property Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link StringMapVertexPropertyType#getValueType <em>Value Type</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface StringMapVertexPropertyType extends IntVertexPropertyType {
	/**
	 * Returns the value of the '<em><b>Value Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Type</em>' containment reference.
	 * @see #setValueType(IntVertexPropertyType)
	 * @model containment="true"
	 * @generated
	 */
	IntVertexPropertyType getValueType();

	/**
	 * Sets the value of the '{@link StringMapVertexPropertyType#getValueType <em>Value Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Type</em>' containment reference.
	 * @see #getValueType()
	 * @generated
	 */
	void setValueType(IntVertexPropertyType value);

} // StringMapVertexPropertyType
