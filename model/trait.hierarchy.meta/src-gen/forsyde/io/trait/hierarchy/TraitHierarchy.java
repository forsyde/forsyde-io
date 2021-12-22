/**
 */
package forsyde.io.trait.hierarchy;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * &lt;!-- begin-user-doc --&gt;
 * A representation of the model object '<em><b>Trait Hierarchy</b>&lt;/em&gt;'.
 * &lt;!-- end-user-doc --&gt;
 *
 * &lt; &gt;
 * The following features are supported:
 * &lt;/p&gt;
 * &lt; &gt;
 *   <li>{@link forsyde.io.trait.hierarchy.TraitHierarchy#getNamespace <em>Namespace</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.TraitHierarchy#getScopedHierarchy <em>Scoped Hierarchy</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.TraitHierarchy#getVertexTraits <em>Vertex Traits</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.TraitHierarchy#getEdgeTraits <em>Edge Traits</em>}&lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @model
 * @generated
 */
public interface TraitHierarchy extends EObject {
	/**
	 * Returns the value of the '<em><b>Namespace</b>&lt;/em&gt;' attribute.
	 * The default value is ""&lt;/code&gt;.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Namespace&lt;/em&gt;' attribute.
	 * @see #setNamespace(String)
	 * @model default="" unique="false"
	 * @generated
	 */
	String getNamespace();

	/**
	 * Sets the value of the '{@link forsyde.io.trait.hierarchy.TraitHierarchy#getNamespace <em>Namespace&lt;/em&gt;}' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @param value the new value of the '<em>Namespace&lt;/em&gt;' attribute.
	 * @see #getNamespace()
	 * @generated
	 */
	void setNamespace(String value);

	/**
	 * Returns the value of the '<em><b>Scoped Hierarchy</b>&lt;/em&gt;' containment reference list.
	 * The list contents are of type {@link forsyde.io.trait.hierarchy.TraitHierarchy}.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Scoped Hierarchy&lt;/em&gt;' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	EList<TraitHierarchy> getScopedHierarchy();

	/**
	 * Returns the value of the '<em><b>Vertex Traits</b>&lt;/em&gt;' containment reference list.
	 * The list contents are of type {@link forsyde.io.trait.hierarchy.VertexTraitSpec}.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Vertex Traits&lt;/em&gt;' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	EList<VertexTraitSpec> getVertexTraits();

	/**
	 * Returns the value of the '<em><b>Edge Traits</b>&lt;/em&gt;' containment reference list.
	 * The list contents are of type {@link forsyde.io.trait.hierarchy.EdgeTraitSpec}.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Edge Traits&lt;/em&gt;' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	EList<EdgeTraitSpec> getEdgeTraits();

} // TraitHierarchy
