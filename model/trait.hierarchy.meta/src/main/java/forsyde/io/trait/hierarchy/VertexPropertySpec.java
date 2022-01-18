/**
 */
package forsyde.io.trait.hierarchy;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vertex Property Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link VertexPropertySpec#getName <em>Name</em>}</li>
 *   <li>{@link VertexPropertySpec#getPropertyType <em>Property Type</em>}</li>
 *   <li>{@link VertexPropertySpec#getPropertyDefault <em>Property Default</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface VertexPropertySpec extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @model unique="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link VertexPropertySpec#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Property Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Type</em>' containment reference.
	 * @see #setPropertyType(VertexPropertyType)
	 * @model containment="true"
	 * @generated
	 */
	VertexPropertyType getPropertyType();

	/**
	 * Sets the value of the '{@link VertexPropertySpec#getPropertyType <em>Property Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property Type</em>' containment reference.
	 * @see #getPropertyType()
	 * @generated
	 */
	void setPropertyType(VertexPropertyType value);

	/**
	 * Returns the value of the '<em><b>Property Default</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Default</em>' containment reference.
	 * @see #setPropertyDefault(VertexPropertyDefault)
	 * @model containment="true"
	 * @generated
	 */
	VertexPropertyDefault getPropertyDefault();

	/**
	 * Sets the value of the '{@link VertexPropertySpec#getPropertyDefault <em>Property Default</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property Default</em>' containment reference.
	 * @see #getPropertyDefault()
	 * @generated
	 */
	void setPropertyDefault(VertexPropertyDefault value);

} // VertexPropertySpec
