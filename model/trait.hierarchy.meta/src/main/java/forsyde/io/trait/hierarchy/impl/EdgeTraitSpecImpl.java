/**
 */
package forsyde.io.trait.hierarchy.impl;

import forsyde.io.trait.hierarchy.EdgeTraitSpec;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicInternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Edge Trait Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link EdgeTraitSpecImpl#getName <em>Name</em>}</li>
 *   <li>{@link EdgeTraitSpecImpl#getRefinedTraits <em>Refined Traits</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EdgeTraitSpecImpl extends MinimalEObjectImpl.Container implements EdgeTraitSpec {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRefinedTraits() <em>Refined Traits</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefinedTraits()
	 * @generated
	 * @ordered
	 */
	protected EList<EdgeTraitSpec> refinedTraits;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EdgeTraitSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getEdgeTraitSpec();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EdgeTraitSpec> getRefinedTraits() {
		if (refinedTraits == null) {
			refinedTraits = new BasicInternalEList<EdgeTraitSpec>(EdgeTraitSpec.class);
		}
		return refinedTraits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HierarchyPackageImpl.EDGE_TRAIT_SPEC__NAME:
				return getName();
			case HierarchyPackageImpl.EDGE_TRAIT_SPEC__REFINED_TRAITS:
				return getRefinedTraits();
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
			case HierarchyPackageImpl.EDGE_TRAIT_SPEC__NAME:
				setName((String)newValue);
				return;
			case HierarchyPackageImpl.EDGE_TRAIT_SPEC__REFINED_TRAITS:
				getRefinedTraits().clear();
				getRefinedTraits().addAll((Collection<? extends EdgeTraitSpec>)newValue);
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
			case HierarchyPackageImpl.EDGE_TRAIT_SPEC__NAME:
				setName(NAME_EDEFAULT);
				return;
			case HierarchyPackageImpl.EDGE_TRAIT_SPEC__REFINED_TRAITS:
				getRefinedTraits().clear();
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
			case HierarchyPackageImpl.EDGE_TRAIT_SPEC__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case HierarchyPackageImpl.EDGE_TRAIT_SPEC__REFINED_TRAITS:
				return refinedTraits != null && !refinedTraits.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //EdgeTraitSpecImpl
