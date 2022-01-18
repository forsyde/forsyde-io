/**
 */
package forsyde.io.trait.hierarchy.impl;

import forsyde.io.trait.hierarchy.VertexPropertyDefault;
import forsyde.io.trait.hierarchy.VertexPropertySpec;
import forsyde.io.trait.hierarchy.VertexPropertyType;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vertex Property Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link VertexPropertySpecImpl#getName <em>Name</em>}</li>
 *   <li>{@link VertexPropertySpecImpl#getPropertyType <em>Property Type</em>}</li>
 *   <li>{@link VertexPropertySpecImpl#getPropertyDefault <em>Property Default</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VertexPropertySpecImpl extends MinimalEObjectImpl.Container implements VertexPropertySpec {
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
	 * The cached value of the '{@link #getPropertyType() <em>Property Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyType()
	 * @generated
	 * @ordered
	 */
	protected VertexPropertyType propertyType;

	/**
	 * The cached value of the '{@link #getPropertyDefault() <em>Property Default</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyDefault()
	 * @generated
	 * @ordered
	 */
	protected VertexPropertyDefault propertyDefault;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VertexPropertySpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getVertexPropertySpec();
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
	public VertexPropertyType getPropertyType() {
		return propertyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPropertyType(VertexPropertyType newPropertyType, NotificationChain msgs) {
		VertexPropertyType oldPropertyType = propertyType;
		propertyType = newPropertyType;
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPropertyType(VertexPropertyType newPropertyType) {
		if (newPropertyType != propertyType) {
			NotificationChain msgs = null;
			if (propertyType != null)
				msgs = ((InternalEObject)propertyType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__PROPERTY_TYPE, null, msgs);
			if (newPropertyType != null)
				msgs = ((InternalEObject)newPropertyType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__PROPERTY_TYPE, null, msgs);
			msgs = basicSetPropertyType(newPropertyType, msgs);
			if (msgs != null) msgs.dispatch();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexPropertyDefault getPropertyDefault() {
		return propertyDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPropertyDefault(VertexPropertyDefault newPropertyDefault, NotificationChain msgs) {
		VertexPropertyDefault oldPropertyDefault = propertyDefault;
		propertyDefault = newPropertyDefault;
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPropertyDefault(VertexPropertyDefault newPropertyDefault) {
		if (newPropertyDefault != propertyDefault) {
			NotificationChain msgs = null;
			if (propertyDefault != null)
				msgs = ((InternalEObject)propertyDefault).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__PROPERTY_DEFAULT, null, msgs);
			if (newPropertyDefault != null)
				msgs = ((InternalEObject)newPropertyDefault).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__PROPERTY_DEFAULT, null, msgs);
			msgs = basicSetPropertyDefault(newPropertyDefault, msgs);
			if (msgs != null) msgs.dispatch();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__PROPERTY_TYPE:
				return basicSetPropertyType(null, msgs);
			case HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__PROPERTY_DEFAULT:
				return basicSetPropertyDefault(null, msgs);
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
			case HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__NAME:
				return getName();
			case HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__PROPERTY_TYPE:
				return getPropertyType();
			case HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__PROPERTY_DEFAULT:
				return getPropertyDefault();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__NAME:
				setName((String)newValue);
				return;
			case HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__PROPERTY_TYPE:
				setPropertyType((VertexPropertyType)newValue);
				return;
			case HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__PROPERTY_DEFAULT:
				setPropertyDefault((VertexPropertyDefault)newValue);
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
			case HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__NAME:
				setName(NAME_EDEFAULT);
				return;
			case HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__PROPERTY_TYPE:
				setPropertyType((VertexPropertyType)null);
				return;
			case HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__PROPERTY_DEFAULT:
				setPropertyDefault((VertexPropertyDefault)null);
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
			case HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__PROPERTY_TYPE:
				return propertyType != null;
			case HierarchyPackageImpl.VERTEX_PROPERTY_SPEC__PROPERTY_DEFAULT:
				return propertyDefault != null;
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

} //VertexPropertySpecImpl
