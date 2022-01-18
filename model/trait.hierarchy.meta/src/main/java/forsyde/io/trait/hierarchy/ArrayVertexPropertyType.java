/**
 */
package forsyde.io.trait.hierarchy;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Vertex Property Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ArrayVertexPropertyType#getValueTypes <em>Value Types</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface ArrayVertexPropertyType extends VertexPropertyType {
	/**
	 * Returns the value of the '<em><b>Value Types</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Types</em>' containment reference.
	 * @see #setValueTypes(VertexPropertyType)
	 * @model containment="true"
	 * @generated
	 */
	VertexPropertyType getValueTypes();

	/**
	 * Sets the value of the '{@link ArrayVertexPropertyType#getValueTypes <em>Value Types</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Types</em>' containment reference.
	 * @see #getValueTypes()
	 * @generated
	 */
	void setValueTypes(VertexPropertyType value);

} // ArrayVertexPropertyType
