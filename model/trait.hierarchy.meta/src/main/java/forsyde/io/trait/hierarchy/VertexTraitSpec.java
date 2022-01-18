/**
 */
package forsyde.io.trait.hierarchy;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vertex Trait Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link VertexTraitSpec#getName <em>Name</em>}</li>
 *   <li>{@link VertexTraitSpec#getRefinedTraits <em>Refined Traits</em>}</li>
 *   <li>{@link VertexTraitSpec#getRequiredPorts <em>Required Ports</em>}</li>
 *   <li>{@link VertexTraitSpec#getRequiredProperties <em>Required Properties</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface VertexTraitSpec extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @model unique="false" id="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link VertexTraitSpec#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Refined Traits</b></em>' reference list.
	 * The list contents are of type {@link VertexTraitSpec}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refined Traits</em>' reference list.
	 * @model
	 * @generated
	 */
	EList<VertexTraitSpec> getRefinedTraits();

	/**
	 * Returns the value of the '<em><b>Required Ports</b></em>' containment reference list.
	 * The list contents are of type {@link PortSpec}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required Ports</em>' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	EList<PortSpec> getRequiredPorts();

	/**
	 * Returns the value of the '<em><b>Required Properties</b></em>' containment reference list.
	 * The list contents are of type {@link VertexPropertySpec}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required Properties</em>' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	EList<VertexPropertySpec> getRequiredProperties();

} // VertexTraitSpec
