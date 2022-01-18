/**
 */
package forsyde.io.trait.hierarchy;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trait Hierarchy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link TraitHierarchy#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link TraitHierarchy#getScopedHierarchy <em>Scoped Hierarchy</em>}</li>
 *   <li>{@link TraitHierarchy#getVertexTraits <em>Vertex Traits</em>}</li>
 *   <li>{@link TraitHierarchy#getEdgeTraits <em>Edge Traits</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface TraitHierarchy extends EObject {
	/**
	 * Returns the value of the '<em><b>Namespace</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Namespace</em>' attribute.
	 * @see #setNamespace(String)
	 * @model default="" unique="false"
	 * @generated
	 */
	String getNamespace();

	/**
	 * Sets the value of the '{@link TraitHierarchy#getNamespace <em>Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Namespace</em>' attribute.
	 * @see #getNamespace()
	 * @generated
	 */
	void setNamespace(String value);

	/**
	 * Returns the value of the '<em><b>Scoped Hierarchy</b></em>' containment reference list.
	 * The list contents are of type {@link TraitHierarchy}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scoped Hierarchy</em>' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	EList<TraitHierarchy> getScopedHierarchy();

	/**
	 * Returns the value of the '<em><b>Vertex Traits</b></em>' containment reference list.
	 * The list contents are of type {@link VertexTraitSpec}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vertex Traits</em>' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	EList<VertexTraitSpec> getVertexTraits();

	/**
	 * Returns the value of the '<em><b>Edge Traits</b></em>' containment reference list.
	 * The list contents are of type {@link EdgeTraitSpec}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edge Traits</em>' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	EList<EdgeTraitSpec> getEdgeTraits();

} // TraitHierarchy
