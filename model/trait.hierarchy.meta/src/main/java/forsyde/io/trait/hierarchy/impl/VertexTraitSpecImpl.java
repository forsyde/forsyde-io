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
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vertex Trait Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link VertexTraitSpecImpl#getName <em>Name</em>}</li>
 *   <li>{@link VertexTraitSpecImpl#getRefinedTraits <em>Refined Traits</em>}</li>
 *   <li>{@link VertexTraitSpecImpl#getRequiredPorts <em>Required Ports</em>}</li>
 *   <li>{@link VertexTraitSpecImpl#getRequiredProperties <em>Required Properties</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VertexTraitSpecImpl extends MinimalEObjectImpl.Container implements VertexTraitSpec {
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
	protected EList<VertexTraitSpec> refinedTraits;

	/**
	 * The cached value of the '{@link #getRequiredPorts() <em>Required Ports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<PortSpec> requiredPorts;

	/**
	 * The cached value of the '{@link #getRequiredProperties() <em>Required Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<VertexPropertySpec> requiredProperties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VertexTraitSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getVertexTraitSpec();
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
	public EList<VertexTraitSpec> getRefinedTraits() {
		if (refinedTraits == null) {
			refinedTraits = new BasicInternalEList<VertexTraitSpec>(VertexTraitSpec.class);
		}
		return refinedTraits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortSpec> getRequiredPorts() {
		if (requiredPorts == null) {
			requiredPorts = new BasicInternalEList<PortSpec>(PortSpec.class);
		}
		return requiredPorts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VertexPropertySpec> getRequiredProperties() {
		if (requiredProperties == null) {
			requiredProperties = new BasicInternalEList<VertexPropertySpec>(VertexPropertySpec.class);
		}
		return requiredProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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

} //VertexTraitSpecImpl
