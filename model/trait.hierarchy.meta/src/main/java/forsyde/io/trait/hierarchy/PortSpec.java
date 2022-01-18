/**
 */
package forsyde.io.trait.hierarchy;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link PortSpec#getName <em>Name</em>}</li>
 *   <li>{@link PortSpec#isMultiple <em>Multiple</em>}</li>
 *   <li>{@link PortSpec#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link PortSpec#getDirection <em>Direction</em>}</li>
 *   <li>{@link PortSpec#getConnectedVertexTrait <em>Connected Vertex Trait</em>}</li>
 *   <li>{@link PortSpec#getConnectedEdgeTrait <em>Connected Edge Trait</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface PortSpec extends EObject {
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
	 * Sets the value of the '{@link PortSpec#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Multiple</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiple</em>' attribute.
	 * @see #setMultiple(boolean)
	 * @model default="true" unique="false"
	 * @generated
	 */
	boolean isMultiple();

	/**
	 * Sets the value of the '{@link PortSpec#isMultiple <em>Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiple</em>' attribute.
	 * @see #isMultiple()
	 * @generated
	 */
	void setMultiple(boolean value);

	/**
	 * Returns the value of the '<em><b>Ordered</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ordered</em>' attribute.
	 * @see #setOrdered(boolean)
	 * @model default="false" unique="false"
	 * @generated
	 */
	boolean isOrdered();

	/**
	 * Sets the value of the '{@link PortSpec#isOrdered <em>Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ordered</em>' attribute.
	 * @see #isOrdered()
	 * @generated
	 */
	void setOrdered(boolean value);

	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
	 * The default value is <code>"BIDIRECTIONAL"</code>.
	 * The literals are from the enumeration {@link PortDirectionEnum}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direction</em>' attribute.
	 * @see PortDirectionEnum
	 * @see #setDirection(PortDirectionEnum)
	 * @model default="BIDIRECTIONAL" unique="false"
	 * @generated
	 */
	PortDirectionEnum getDirection();

	/**
	 * Sets the value of the '{@link PortSpec#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see PortDirectionEnum
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(PortDirectionEnum value);

	/**
	 * Returns the value of the '<em><b>Connected Vertex Trait</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connected Vertex Trait</em>' reference.
	 * @see #setConnectedVertexTrait(VertexTraitSpec)
	 * @model
	 * @generated
	 */
	VertexTraitSpec getConnectedVertexTrait();

	/**
	 * Sets the value of the '{@link PortSpec#getConnectedVertexTrait <em>Connected Vertex Trait</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connected Vertex Trait</em>' reference.
	 * @see #getConnectedVertexTrait()
	 * @generated
	 */
	void setConnectedVertexTrait(VertexTraitSpec value);

	/**
	 * Returns the value of the '<em><b>Connected Edge Trait</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connected Edge Trait</em>' reference.
	 * @see #setConnectedEdgeTrait(EdgeTraitSpec)
	 * @model
	 * @generated
	 */
	EdgeTraitSpec getConnectedEdgeTrait();

	/**
	 * Sets the value of the '{@link PortSpec#getConnectedEdgeTrait <em>Connected Edge Trait</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connected Edge Trait</em>' reference.
	 * @see #getConnectedEdgeTrait()
	 * @generated
	 */
	void setConnectedEdgeTrait(EdgeTraitSpec value);

} // PortSpec
