/**
 */
package forsyde.io.trait.hierarchy;

import org.eclipse.emf.ecore.EObject;

/**
 * &lt;!-- begin-user-doc --&gt;
 * A representation of the model object '<em><b>Port Spec</b>&lt;/em&gt;'.
 * &lt;!-- end-user-doc --&gt;
 *
 * &lt; &gt;
 * The following features are supported:
 * &lt;/p&gt;
 * &lt; &gt;
 *   <li>{@link forsyde.io.trait.hierarchy.PortSpec#getName <em>Name</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.PortSpec#getDirection <em>Direction</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.PortSpec#getConnectedVertexTrait <em>Connected Vertex Trait</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.PortSpec#getConnectedEdgeTrait <em>Connected Edge Trait</em>}&lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @model
 * @generated
 */
public interface PortSpec extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b>&lt;/em&gt;' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Name&lt;/em&gt;' attribute.
	 * @see #setName(String)
	 * @model unique="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link forsyde.io.trait.hierarchy.PortSpec#getName <em>Name&lt;/em&gt;}' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @param value the new value of the '<em>Name&lt;/em&gt;' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Direction</b>&lt;/em&gt;' attribute.
	 * The literals are from the enumeration {@link forsyde.io.trait.hierarchy.PortDirectionEnum}.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Direction&lt;/em&gt;' attribute.
	 * @see forsyde.io.trait.hierarchy.PortDirectionEnum
	 * @see #setDirection(PortDirectionEnum)
	 * @model unique="false"
	 * @generated
	 */
	PortDirectionEnum getDirection();

	/**
	 * Sets the value of the '{@link forsyde.io.trait.hierarchy.PortSpec#getDirection <em>Direction&lt;/em&gt;}' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @param value the new value of the '<em>Direction&lt;/em&gt;' attribute.
	 * @see forsyde.io.trait.hierarchy.PortDirectionEnum
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(PortDirectionEnum value);

	/**
	 * Returns the value of the '<em><b>Connected Vertex Trait</b>&lt;/em&gt;' reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Connected Vertex Trait&lt;/em&gt;' reference.
	 * @see #setConnectedVertexTrait(VertexTraitSpec)
	 * @model
	 * @generated
	 */
	VertexTraitSpec getConnectedVertexTrait();

	/**
	 * Sets the value of the '{@link forsyde.io.trait.hierarchy.PortSpec#getConnectedVertexTrait <em>Connected Vertex Trait&lt;/em&gt;}' reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @param value the new value of the '<em>Connected Vertex Trait&lt;/em&gt;' reference.
	 * @see #getConnectedVertexTrait()
	 * @generated
	 */
	void setConnectedVertexTrait(VertexTraitSpec value);

	/**
	 * Returns the value of the '<em><b>Connected Edge Trait</b>&lt;/em&gt;' reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Connected Edge Trait&lt;/em&gt;' reference.
	 * @see #setConnectedEdgeTrait(EdgeTraitSpec)
	 * @model
	 * @generated
	 */
	EdgeTraitSpec getConnectedEdgeTrait();

	/**
	 * Sets the value of the '{@link forsyde.io.trait.hierarchy.PortSpec#getConnectedEdgeTrait <em>Connected Edge Trait&lt;/em&gt;}' reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @param value the new value of the '<em>Connected Edge Trait&lt;/em&gt;' reference.
	 * @see #getConnectedEdgeTrait()
	 * @generated
	 */
	void setConnectedEdgeTrait(EdgeTraitSpec value);

} // PortSpec
