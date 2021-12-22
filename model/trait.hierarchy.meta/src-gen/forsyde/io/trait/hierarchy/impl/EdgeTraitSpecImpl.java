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
 * &lt;!-- begin-user-doc --&gt;
 * An implementation of the model object '<em><b>Edge Trait Spec</b>&lt;/em&gt;'.
 * &lt;!-- end-user-doc --&gt;
 * &lt; &gt;
 * The following features are implemented:
 * &lt;/p&gt;
 * &lt; &gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.EdgeTraitSpecImpl#getName <em>Name</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.EdgeTraitSpecImpl#getRefinedTraits <em>Refined Traits</em>}&lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @generated
 */
public class EdgeTraitSpecImpl extends MinimalEObjectImpl.Container implements EdgeTraitSpec {
	/**
	 * The default value of the '{@link #getName() <em>Name&lt;/em&gt;}' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name&lt;/em&gt;}' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRefinedTraits() <em>Refined Traits&lt;/em&gt;}' reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getRefinedTraits()
	 * @generated
	 * @ordered
	 */
	protected EList<EdgeTraitSpec> refinedTraits;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	protected EdgeTraitSpecImpl() {
		super();
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getEdgeTraitSpec();
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public EList<EdgeTraitSpec> getRefinedTraits() {
		if (refinedTraits == null) {
			refinedTraits = new BasicInternalEList<EdgeTraitSpec>(EdgeTraitSpec.class);
		}
		return refinedTraits;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
