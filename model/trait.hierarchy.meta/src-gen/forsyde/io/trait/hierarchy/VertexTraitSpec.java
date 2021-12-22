/**
 */
package forsyde.io.trait.hierarchy;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * &lt;!-- begin-user-doc --&gt;
 * A representation of the model object '<em><b>Vertex Trait Spec</b>&lt;/em&gt;'.
 * &lt;!-- end-user-doc --&gt;
 *
 * &lt; &gt;
 * The following features are supported:
 * &lt;/p&gt;
 * &lt; &gt;
 *   <li>{@link forsyde.io.trait.hierarchy.VertexTraitSpec#getName <em>Name</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.VertexTraitSpec#getRefinedTraits <em>Refined Traits</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.VertexTraitSpec#getRequiredPorts <em>Required Ports</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.VertexTraitSpec#getRequiredProperties <em>Required Properties</em>}&lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @model
 * @generated
 */
public interface VertexTraitSpec extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b>&lt;/em&gt;' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Name&lt;/em&gt;' attribute.
	 * @see #setName(String)
	 * @model unique="false" id="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link forsyde.io.trait.hierarchy.VertexTraitSpec#getName <em>Name&lt;/em&gt;}' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @param value the new value of the '<em>Name&lt;/em&gt;' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Refined Traits</b>&lt;/em&gt;' reference list.
	 * The list contents are of type {@link forsyde.io.trait.hierarchy.VertexTraitSpec}.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Refined Traits&lt;/em&gt;' reference list.
	 * @model
	 * @generated
	 */
	EList<VertexTraitSpec> getRefinedTraits();

	/**
	 * Returns the value of the '<em><b>Required Ports</b>&lt;/em&gt;' containment reference list.
	 * The list contents are of type {@link forsyde.io.trait.hierarchy.PortSpec}.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Required Ports&lt;/em&gt;' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	EList<PortSpec> getRequiredPorts();

	/**
	 * Returns the value of the '<em><b>Required Properties</b>&lt;/em&gt;' containment reference list.
	 * The list contents are of type {@link forsyde.io.trait.hierarchy.VertexPropertySpec}.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Required Properties&lt;/em&gt;' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	EList<VertexPropertySpec> getRequiredProperties();

} // VertexTraitSpec
