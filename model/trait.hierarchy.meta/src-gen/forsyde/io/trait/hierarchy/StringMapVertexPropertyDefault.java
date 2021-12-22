/**
 */
package forsyde.io.trait.hierarchy;

import org.eclipse.emf.common.util.EList;

/**
 * &lt;!-- begin-user-doc --&gt;
 * A representation of the model object '<em><b>String Map Vertex Property Default</b>&lt;/em&gt;'.
 * &lt;!-- end-user-doc --&gt;
 *
 * &lt; &gt;
 * The following features are supported:
 * &lt;/p&gt;
 * &lt; &gt;
 *   <li>{@link forsyde.io.trait.hierarchy.StringMapVertexPropertyDefault#getValues <em>Values</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.StringMapVertexPropertyDefault#getIndexes <em>Indexes</em>}&lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @model
 * @generated
 */
public interface StringMapVertexPropertyDefault extends IntVertexPropertyDefault {
	/**
	 * Returns the value of the '<em><b>Values</b>&lt;/em&gt;' containment reference list.
	 * The list contents are of type {@link forsyde.io.trait.hierarchy.IntVertexPropertyDefault}.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Values&lt;/em&gt;' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	EList<IntVertexPropertyDefault> getValues();

	/**
	 * Returns the value of the '<em><b>Indexes</b>&lt;/em&gt;' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the value of the '<em>Indexes&lt;/em&gt;' attribute list.
	 * @model unique="false"
	 * @generated
	 */
	EList<String> getIndexes();

} // StringMapVertexPropertyDefault
