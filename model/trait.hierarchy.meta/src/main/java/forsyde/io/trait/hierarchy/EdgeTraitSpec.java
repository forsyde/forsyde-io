/**
 */
package forsyde.io.trait.hierarchy;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge Trait Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link EdgeTraitSpec#getName <em>Name</em>}</li>
 *   <li>{@link EdgeTraitSpec#getRefinedTraits <em>Refined Traits</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface EdgeTraitSpec extends EObject {
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
	 * Sets the value of the '{@link EdgeTraitSpec#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Refined Traits</b></em>' reference list.
	 * The list contents are of type {@link EdgeTraitSpec}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refined Traits</em>' reference list.
	 * @model
	 * @generated
	 */
	EList<EdgeTraitSpec> getRefinedTraits();

} // EdgeTraitSpec
