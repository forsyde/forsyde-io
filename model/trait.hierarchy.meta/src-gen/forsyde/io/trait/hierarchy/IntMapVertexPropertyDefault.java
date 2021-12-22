/**
 */
package forsyde.io.trait.hierarchy;

import org.eclipse.emf.common.util.EList;

/**
 * &lt;!-- begin-user-doc --&gt;
 * A representation of the model object '<em><b>Int Map Vertex Property Default</b>&lt;/em&gt;'.
 * &lt;!-- end-user-doc --&gt;
 *
 * &lt; &gt;
 * The following features are supported:
 * &lt;/p&gt;
 * &lt; &gt;
 *   <li>{@link forsyde.io.trait.hierarchy.IntMapVertexPropertyDefault#getValues <em>Values</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.IntMapVertexPropertyDefault#getIndexes <em>Indexes</em>}&lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @model
 * @generated
 */
public interface IntMapVertexPropertyDefault extends VertexPropertyDefault {
	/**
	 * Returns the value of the '<em><b>Values</b>&lt;/em&gt;' containment reference list.
	 * The list contents are of type {@link forsyde.io.trait.hierarchy.VertexPropertyDefault}.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Values&lt;/em&gt;' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	EList<VertexPropertyDefault> getValues();

	/**
	 * Returns the value of the '<em><b>Indexes</b>&lt;/em&gt;' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Indexes&lt;/em&gt;' attribute list.
	 * @model unique="false"
	 * @generated
	 */
	EList<Integer> getIndexes();

} // IntMapVertexPropertyDefault
