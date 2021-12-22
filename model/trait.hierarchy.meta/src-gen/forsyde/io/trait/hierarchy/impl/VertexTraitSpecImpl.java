/**
 */
package forsyde.io.trait.hierarchy.impl;

import forsyde.io.trait.hierarchy.PortSpec;
import forsyde.io.trait.hierarchy.VertexPropertySpec;
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
 * An implementation of the model object '<em><b>Vertex Trait Spec</b>&lt;/em&gt;'.
 * &lt;!-- end-user-doc --&gt;
 * &lt; &gt;
 * The following features are implemented:
 * &lt;/p&gt;
 * &lt; &gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.VertexTraitSpecImpl#getName <em>Name</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.VertexTraitSpecImpl#getRefinedTraits <em>Refined Traits</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.VertexTraitSpecImpl#getRequiredPorts <em>Required Ports</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.VertexTraitSpecImpl#getRequiredProperties <em>Required Properties</em>}&lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @generated
 */
public class VertexTraitSpecImpl extends MinimalEObjectImpl.Container implements VertexTraitSpec {
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
	protected EList<VertexTraitSpec> refinedTraits;

	/**
	 * The cached value of the '{@link #getRequiredPorts() <em>Required Ports&lt;/em&gt;}' containment reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getRequiredPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<PortSpec> requiredPorts;

	/**
	 * The cached value of the '{@link #getRequiredProperties() <em>Required Properties&lt;/em&gt;}' containment reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getRequiredProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<VertexPropertySpec> requiredProperties;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	protected VertexTraitSpecImpl() {
		super();
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getVertexTraitSpec();
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
	public EList<VertexTraitSpec> getRefinedTraits() {
		if (refinedTraits == null) {
			refinedTraits = new BasicInternalEList<VertexTraitSpec>(VertexTraitSpec.class);
		}
		return refinedTraits;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public EList<PortSpec> getRequiredPorts() {
		if (requiredPorts == null) {
			requiredPorts = new BasicInternalEList<PortSpec>(PortSpec.class);
		}
		return requiredPorts;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public EList<VertexPropertySpec> getRequiredProperties() {
		if (requiredProperties == null) {
			requiredProperties = new BasicInternalEList<VertexPropertySpec>(VertexPropertySpec.class);
		}
		return requiredProperties;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__REQUIRED_PORTS:
				return ((InternalEList<?>)getRequiredPorts()).basicRemove(otherEnd, msgs);
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__REQUIRED_PROPERTIES:
				return ((InternalEList<?>)getRequiredProperties()).basicRemove(otherEnd, msgs);
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
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__NAME:
				return getName();
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__REFINED_TRAITS:
				return getRefinedTraits();
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__REQUIRED_PORTS:
				return getRequiredPorts();
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__REQUIRED_PROPERTIES:
				return getRequiredProperties();
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
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__NAME:
				setName((String)newValue);
				return;
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__REFINED_TRAITS:
				getRefinedTraits().clear();
				getRefinedTraits().addAll((Collection<? extends VertexTraitSpec>)newValue);
				return;
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__REQUIRED_PORTS:
				getRequiredPorts().clear();
				getRequiredPorts().addAll((Collection<? extends PortSpec>)newValue);
				return;
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__REQUIRED_PROPERTIES:
				getRequiredProperties().clear();
				getRequiredProperties().addAll((Collection<? extends VertexPropertySpec>)newValue);
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
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__NAME:
				setName(NAME_EDEFAULT);
				return;
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__REFINED_TRAITS:
				getRefinedTraits().clear();
				return;
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__REQUIRED_PORTS:
				getRequiredPorts().clear();
				return;
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__REQUIRED_PROPERTIES:
				getRequiredProperties().clear();
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
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__REFINED_TRAITS:
				return refinedTraits != null && !refinedTraits.isEmpty();
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__REQUIRED_PORTS:
				return requiredPorts != null && !requiredPorts.isEmpty();
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC__REQUIRED_PROPERTIES:
				return requiredProperties != null && !requiredProperties.isEmpty();
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

} //VertexTraitSpecImpl
