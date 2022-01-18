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
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Trait Hierarchy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link TraitHierarchyImpl#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link TraitHierarchyImpl#getScopedHierarchy <em>Scoped Hierarchy</em>}</li>
 *   <li>{@link TraitHierarchyImpl#getVertexTraits <em>Vertex Traits</em>}</li>
 *   <li>{@link TraitHierarchyImpl#getEdgeTraits <em>Edge Traits</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TraitHierarchyImpl extends MinimalEObjectImpl.Container implements TraitHierarchy {
	/**
	 * The default value of the '{@link #getNamespace() <em>Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNamespace()
	 * @generated
	 * @ordered
	 */
	protected static final String NAMESPACE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getNamespace() <em>Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNamespace()
	 * @generated
	 * @ordered
	 */
	protected String namespace = NAMESPACE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getScopedHierarchy() <em>Scoped Hierarchy</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopedHierarchy()
	 * @generated
	 * @ordered
	 */
	protected EList<TraitHierarchy> scopedHierarchy;

	/**
	 * The cached value of the '{@link #getVertexTraits() <em>Vertex Traits</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVertexTraits()
	 * @generated
	 * @ordered
	 */
	protected EList<VertexTraitSpec> vertexTraits;

	/**
	 * The cached value of the '{@link #getEdgeTraits() <em>Edge Traits</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdgeTraits()
	 * @generated
	 * @ordered
	 */
	protected EList<EdgeTraitSpec> edgeTraits;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TraitHierarchyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getTraitHierarchy();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNamespace(String newNamespace) {
		namespace = newNamespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TraitHierarchy> getScopedHierarchy() {
		if (scopedHierarchy == null) {
			scopedHierarchy = new BasicInternalEList<TraitHierarchy>(TraitHierarchy.class);
		}
		return scopedHierarchy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VertexTraitSpec> getVertexTraits() {
		if (vertexTraits == null) {
			vertexTraits = new BasicInternalEList<VertexTraitSpec>(VertexTraitSpec.class);
		}
		return vertexTraits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EdgeTraitSpec> getEdgeTraits() {
		if (edgeTraits == null) {
			edgeTraits = new BasicInternalEList<EdgeTraitSpec>(EdgeTraitSpec.class);
		}
		return edgeTraits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
