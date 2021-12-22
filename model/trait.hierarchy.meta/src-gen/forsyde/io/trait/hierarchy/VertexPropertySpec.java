/**
 */
package forsyde.io.trait.hierarchy;

import org.eclipse.emf.ecore.EObject;

/**
 * &lt;!-- begin-user-doc --&gt;
 * A representation of the model object '<em><b>Vertex Property Spec</b>&lt;/em&gt;'.
 * &lt;!-- end-user-doc --&gt;
 *
 * &lt; &gt;
 * The following features are supported:
 * &lt;/p&gt;
 * &lt; &gt;
 *   <li>{@link forsyde.io.trait.hierarchy.VertexPropertySpec#getName <em>Name</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.VertexPropertySpec#getPropertyType <em>Property Type</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.VertexPropertySpec#getPropertyDefault <em>Property Default</em>}&lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @model
 * @generated
 */
public interface VertexPropertySpec extends EObject {
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
	 * Sets the value of the '{@link forsyde.io.trait.hierarchy.VertexPropertySpec#getName <em>Name&lt;/em&gt;}' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @param value the new value of the '<em>Name&lt;/em&gt;' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Property Type</b>&lt;/em&gt;' containment reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Property Type&lt;/em&gt;' containment reference.
	 * @see #setPropertyType(VertexPropertyType)
	 * @model containment="true"
	 * @generated
	 */
	VertexPropertyType getPropertyType();

	/**
	 * Sets the value of the '{@link forsyde.io.trait.hierarchy.VertexPropertySpec#getPropertyType <em>Property Type&lt;/em&gt;}' containment reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @param value the new value of the '<em>Property Type&lt;/em&gt;' containment reference.
	 * @see #getPropertyType()
	 * @generated
	 */
	void setPropertyType(VertexPropertyType value);

	/**
	 * Returns the value of the '<em><b>Property Default</b>&lt;/em&gt;' containment reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Property Default&lt;/em&gt;' containment reference.
	 * @see #setPropertyDefault(VertexPropertyDefault)
	 * @model containment="true"
	 * @generated
	 */
	VertexPropertyDefault getPropertyDefault();

	/**
	 * Sets the value of the '{@link forsyde.io.trait.hierarchy.VertexPropertySpec#getPropertyDefault <em>Property Default&lt;/em&gt;}' containment reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @param value the new value of the '<em>Property Default&lt;/em&gt;' containment reference.
	 * @see #getPropertyDefault()
	 * @generated
	 */
	void setPropertyDefault(VertexPropertyDefault value);

} // VertexPropertySpec
