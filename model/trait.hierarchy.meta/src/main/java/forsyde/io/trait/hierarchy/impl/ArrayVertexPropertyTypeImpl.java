/**
 */
package forsyde.io.trait.hierarchy.impl;

import forsyde.io.trait.hierarchy.ArrayVertexPropertyType;
import forsyde.io.trait.hierarchy.VertexPropertyType;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array Vertex Property Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ArrayVertexPropertyTypeImpl#getValueTypes <em>Value Types</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArrayVertexPropertyTypeImpl extends MinimalEObjectImpl.Container implements ArrayVertexPropertyType {
	/**
	 * The cached value of the '{@link #getValueTypes() <em>Value Types</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueTypes()
	 * @generated
	 * @ordered
	 */
	protected VertexPropertyType valueTypes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrayVertexPropertyTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getArrayVertexPropertyType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexPropertyType getValueTypes() {
		return valueTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValueTypes(VertexPropertyType newValueTypes, NotificationChain msgs) {
		VertexPropertyType oldValueTypes = valueTypes;
		valueTypes = newValueTypes;
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueTypes(VertexPropertyType newValueTypes) {
		if (newValueTypes != valueTypes) {
			NotificationChain msgs = null;
			if (valueTypes != null)
				msgs = ((InternalEObject)valueTypes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HierarchyPackageImpl.ARRAY_VERTEX_PROPERTY_TYPE__VALUE_TYPES, null, msgs);
			if (newValueTypes != null)
				msgs = ((InternalEObject)newValueTypes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HierarchyPackageImpl.ARRAY_VERTEX_PROPERTY_TYPE__VALUE_TYPES, null, msgs);
			msgs = basicSetValueTypes(newValueTypes, msgs);
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
			case HierarchyPackageImpl.ARRAY_VERTEX_PROPERTY_TYPE__VALUE_TYPES:
				return basicSetValueTypes(null, msgs);
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
			case HierarchyPackageImpl.ARRAY_VERTEX_PROPERTY_TYPE__VALUE_TYPES:
				return getValueTypes();
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
			case HierarchyPackageImpl.ARRAY_VERTEX_PROPERTY_TYPE__VALUE_TYPES:
				setValueTypes((VertexPropertyType)newValue);
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
			case HierarchyPackageImpl.ARRAY_VERTEX_PROPERTY_TYPE__VALUE_TYPES:
				setValueTypes((VertexPropertyType)null);
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
			case HierarchyPackageImpl.ARRAY_VERTEX_PROPERTY_TYPE__VALUE_TYPES:
				return valueTypes != null;
		}
		return eDynamicIsSet(featureID);
	}

} //ArrayVertexPropertyTypeImpl
