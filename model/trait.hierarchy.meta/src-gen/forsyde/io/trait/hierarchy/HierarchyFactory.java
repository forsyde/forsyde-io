/**
 */
package forsyde.io.trait.hierarchy;


/**
 * &lt;!-- begin-user-doc --&gt;
 * The <b>Factory&lt;/b&gt; for the model.
 * It provides a create method for each non-abstract class of the model.
 * &lt;!-- end-user-doc --&gt;
 * @generated
 */
public interface HierarchyFactory {
	/**
	 * The singleton instance of the factory.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	HierarchyFactory INSTANCE = forsyde.io.trait.hierarchy.impl.HierarchyFactoryImpl.eINSTANCE;

	/**
	 * Returns a new object of class '<em>Port Spec&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Port Spec&lt;/em&gt;'.
	 * @generated
	 */
	PortSpec createPortSpec();

	/**
	 * Returns a new object of class '<em>String Vertex Property Type&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>String Vertex Property Type&lt;/em&gt;'.
	 * @generated
	 */
	StringVertexPropertyType createStringVertexPropertyType();

	/**
	 * Returns a new object of class '<em>Int Vertex Property Type&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Int Vertex Property Type&lt;/em&gt;'.
	 * @generated
	 */
	IntVertexPropertyType createIntVertexPropertyType();

	/**
	 * Returns a new object of class '<em>Boolean Vertex Property Type&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Boolean Vertex Property Type&lt;/em&gt;'.
	 * @generated
	 */
	BooleanVertexPropertyType createBooleanVertexPropertyType();

	/**
	 * Returns a new object of class '<em>Float Vertex Property Type&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Float Vertex Property Type&lt;/em&gt;'.
	 * @generated
	 */
	FloatVertexPropertyType createFloatVertexPropertyType();

	/**
	 * Returns a new object of class '<em>Double Vertex Property Type&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Double Vertex Property Type&lt;/em&gt;'.
	 * @generated
	 */
	DoubleVertexPropertyType createDoubleVertexPropertyType();

	/**
	 * Returns a new object of class '<em>Long Vertex Property Type&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Long Vertex Property Type&lt;/em&gt;'.
	 * @generated
	 */
	LongVertexPropertyType createLongVertexPropertyType();

	/**
	 * Returns a new object of class '<em>Array Vertex Property Type&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Array Vertex Property Type&lt;/em&gt;'.
	 * @generated
	 */
	ArrayVertexPropertyType createArrayVertexPropertyType();

	/**
	 * Returns a new object of class '<em>Int Map Vertex Property Type&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Int Map Vertex Property Type&lt;/em&gt;'.
	 * @generated
	 */
	IntMapVertexPropertyType createIntMapVertexPropertyType();

	/**
	 * Returns a new object of class '<em>String Map Vertex Property Type&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>String Map Vertex Property Type&lt;/em&gt;'.
	 * @generated
	 */
	StringMapVertexPropertyType createStringMapVertexPropertyType();

	/**
	 * Returns a new object of class '<em>String Vertex Property Default&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>String Vertex Property Default&lt;/em&gt;'.
	 * @generated
	 */
	StringVertexPropertyDefault createStringVertexPropertyDefault();

	/**
	 * Returns a new object of class '<em>Int Vertex Property Default&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Int Vertex Property Default&lt;/em&gt;'.
	 * @generated
	 */
	IntVertexPropertyDefault createIntVertexPropertyDefault();

	/**
	 * Returns a new object of class '<em>Boolean Vertex Property Default&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Boolean Vertex Property Default&lt;/em&gt;'.
	 * @generated
	 */
	BooleanVertexPropertyDefault createBooleanVertexPropertyDefault();

	/**
	 * Returns a new object of class '<em>Float Vertex Property Default&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Float Vertex Property Default&lt;/em&gt;'.
	 * @generated
	 */
	FloatVertexPropertyDefault createFloatVertexPropertyDefault();

	/**
	 * Returns a new object of class '<em>Double Vertex Property Default&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Double Vertex Property Default&lt;/em&gt;'.
	 * @generated
	 */
	DoubleVertexPropertyDefault createDoubleVertexPropertyDefault();

	/**
	 * Returns a new object of class '<em>Long Vertex Property Default&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Long Vertex Property Default&lt;/em&gt;'.
	 * @generated
	 */
	LongVertexPropertyDefault createLongVertexPropertyDefault();

	/**
	 * Returns a new object of class '<em>Array Vertex Property Default&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Array Vertex Property Default&lt;/em&gt;'.
	 * @generated
	 */
	ArrayVertexPropertyDefault createArrayVertexPropertyDefault();

	/**
	 * Returns a new object of class '<em>Int Map Vertex Property Default&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Int Map Vertex Property Default&lt;/em&gt;'.
	 * @generated
	 */
	IntMapVertexPropertyDefault createIntMapVertexPropertyDefault();

	/**
	 * Returns a new object of class '<em>String Map Vertex Property Default&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>String Map Vertex Property Default&lt;/em&gt;'.
	 * @generated
	 */
	StringMapVertexPropertyDefault createStringMapVertexPropertyDefault();

	/**
	 * Returns a new object of class '<em>Vertex Property Spec&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Vertex Property Spec&lt;/em&gt;'.
	 * @generated
	 */
	VertexPropertySpec createVertexPropertySpec();

	/**
	 * Returns a new object of class '<em>Vertex Trait Spec&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Vertex Trait Spec&lt;/em&gt;'.
	 * @generated
	 */
	VertexTraitSpec createVertexTraitSpec();

	/**
	 * Returns a new object of class '<em>Edge Trait Spec&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Edge Trait Spec&lt;/em&gt;'.
	 * @generated
	 */
	EdgeTraitSpec createEdgeTraitSpec();

	/**
	 * Returns a new object of class '<em>Trait Hierarchy&lt;/em&gt;'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return a new object of class '<em>Trait Hierarchy&lt;/em&gt;'.
	 * @generated
	 */
	TraitHierarchy createTraitHierarchy();

} //HierarchyFactory
