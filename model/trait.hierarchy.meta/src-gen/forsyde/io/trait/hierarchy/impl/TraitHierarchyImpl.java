/**
 */
package forsyde.io.trait.hierarchy.impl;

import forsyde.io.trait.hierarchy.EdgeTraitSpec;
import forsyde.io.trait.hierarchy.TraitHierarchy;
import forsyde.io.trait.hierarchy.VertexTraitSpec;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * &lt;!-- begin-user-doc --&gt;
 * An implementation of the model object '<em><b>Trait Hierarchy</b>&lt;/em&gt;'.
 * &lt;!-- end-user-doc --&gt;
 * &lt; &gt;
 * The following features are implemented:
 * &lt;/p&gt;
 * &lt; &gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.TraitHierarchyImpl#getNamespace <em>Namespace</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.TraitHierarchyImpl#getScopedHierarchy <em>Scoped Hierarchy</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.TraitHierarchyImpl#getVertexTraits <em>Vertex Traits</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.TraitHierarchyImpl#getEdgeTraits <em>Edge Traits</em>}&lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @generated
 */
public class TraitHierarchyImpl extends MinimalEObjectImpl.Container implements TraitHierarchy {
	/**
	 * The default value of the '{@link #getNamespace() <em>Namespace&lt;/em&gt;}' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getNamespace()
	 * @generated
	 * @ordered
	 */
	protected static final String NAMESPACE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getNamespace() <em>Namespace&lt;/em&gt;}' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getNamespace()
	 * @generated
	 * @ordered
	 */
	protected String namespace = NAMESPACE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getScopedHierarchy() <em>Scoped Hierarchy&lt;/em&gt;}' containment reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getScopedHierarchy()
	 * @generated
	 * @ordered
	 */
	protected EList<TraitHierarchy> scopedHierarchy;

	/**
	 * The cached value of the '{@link #getVertexTraits() <em>Vertex Traits&lt;/em&gt;}' containment reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getVertexTraits()
	 * @generated
	 * @ordered
	 */
	protected EList<VertexTraitSpec> vertexTraits;

	/**
	 * The cached value of the '{@link #getEdgeTraits() <em>Edge Traits&lt;/em&gt;}' containment reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getEdgeTraits()
	 * @generated
	 * @ordered
	 */
	protected EList<EdgeTraitSpec> edgeTraits;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	protected TraitHierarchyImpl() {
		super();
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getTraitHierarchy();
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public void setNamespace(String newNamespace) {
		namespace = newNamespace;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public EList<TraitHierarchy> getScopedHierarchy() {
		if (scopedHierarchy == null) {
			scopedHierarchy = new BasicInternalEList<TraitHierarchy>(TraitHierarchy.class);
		}
		return scopedHierarchy;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public EList<VertexTraitSpec> getVertexTraits() {
		if (vertexTraits == null) {
			vertexTraits = new BasicInternalEList<VertexTraitSpec>(VertexTraitSpec.class);
		}
		return vertexTraits;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public EList<EdgeTraitSpec> getEdgeTraits() {
		if (edgeTraits == null) {
			edgeTraits = new BasicInternalEList<EdgeTraitSpec>(EdgeTraitSpec.class);
		}
		return edgeTraits;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HierarchyPackageImpl.TRAIT_HIERARCHY__SCOPED_HIERARCHY:
				return ((InternalEList<?>)getScopedHierarchy()).basicRemove(otherEnd, msgs);
			case HierarchyPackageImpl.TRAIT_HIERARCHY__VERTEX_TRAITS:
				return ((InternalEList<?>)getVertexTraits()).basicRemove(otherEnd, msgs);
			case HierarchyPackageImpl.TRAIT_HIERARCHY__EDGE_TRAITS:
				return ((InternalEList<?>)getEdgeTraits()).basicRemove(otherEnd, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HierarchyPackageImpl.TRAIT_HIERARCHY__NAMESPACE:
				return getNamespace();
			case HierarchyPackageImpl.TRAIT_HIERARCHY__SCOPED_HIERARCHY:
				return getScopedHierarchy();
			case HierarchyPackageImpl.TRAIT_HIERARCHY__VERTEX_TRAITS:
				return getVertexTraits();
			case HierarchyPackageImpl.TRAIT_HIERARCHY__EDGE_TRAITS:
				return getEdgeTraits();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case HierarchyPackageImpl.TRAIT_HIERARCHY__NAMESPACE:
				setNamespace((String)newValue);
				return;
			case HierarchyPackageImpl.TRAIT_HIERARCHY__SCOPED_HIERARCHY:
				getScopedHierarchy().clear();
				getScopedHierarchy().addAll((Collection<? extends TraitHierarchy>)newValue);
				return;
			case HierarchyPackageImpl.TRAIT_HIERARCHY__VERTEX_TRAITS:
				getVertexTraits().clear();
				getVertexTraits().addAll((Collection<? extends VertexTraitSpec>)newValue);
				return;
			case HierarchyPackageImpl.TRAIT_HIERARCHY__EDGE_TRAITS:
				getEdgeTraits().clear();
				getEdgeTraits().addAll((Collection<? extends EdgeTraitSpec>)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case HierarchyPackageImpl.TRAIT_HIERARCHY__NAMESPACE:
				setNamespace(NAMESPACE_EDEFAULT);
				return;
			case HierarchyPackageImpl.TRAIT_HIERARCHY__SCOPED_HIERARCHY:
				getScopedHierarchy().clear();
				return;
			case HierarchyPackageImpl.TRAIT_HIERARCHY__VERTEX_TRAITS:
				getVertexTraits().clear();
				return;
			case HierarchyPackageImpl.TRAIT_HIERARCHY__EDGE_TRAITS:
				getEdgeTraits().clear();
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case HierarchyPackageImpl.TRAIT_HIERARCHY__NAMESPACE:
				return NAMESPACE_EDEFAULT == null ? namespace != null : !NAMESPACE_EDEFAULT.equals(namespace);
			case HierarchyPackageImpl.TRAIT_HIERARCHY__SCOPED_HIERARCHY:
				return scopedHierarchy != null && !scopedHierarchy.isEmpty();
			case HierarchyPackageImpl.TRAIT_HIERARCHY__VERTEX_TRAITS:
				return vertexTraits != null && !vertexTraits.isEmpty();
			case HierarchyPackageImpl.TRAIT_HIERARCHY__EDGE_TRAITS:
				return edgeTraits != null && !edgeTraits.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (namespace: ");
		result.append(namespace);
		result.append(')');
		return result.toString();
	}

} //TraitHierarchyImpl
