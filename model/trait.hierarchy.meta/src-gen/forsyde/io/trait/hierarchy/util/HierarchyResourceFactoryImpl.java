/**
 */
package forsyde.io.trait.hierarchy.util;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * &lt;!-- begin-user-doc --&gt;
 * The <b>Resource Factory&lt;/b&gt; associated with the package.
 * &lt;!-- end-user-doc --&gt;
 * @see forsyde.io.trait.hierarchy.util.HierarchyResourceImpl
 * @generated
 */
public class HierarchyResourceFactoryImpl extends ResourceFactoryImpl {
	/**
	 * Creates an instance of the resource factory.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public HierarchyResourceFactoryImpl() {
		super();
	}

	/**
	 * Creates an instance of the resource.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	public Resource createResource(URI uri) {
		Resource result = new HierarchyResourceImpl(uri);
		return result;
	}

} //HierarchyResourceFactoryImpl
