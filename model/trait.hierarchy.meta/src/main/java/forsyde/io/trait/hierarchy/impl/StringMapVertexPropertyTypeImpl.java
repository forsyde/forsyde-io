/**
 */
package forsyde.io.trait.hierarchy.impl;

import forsyde.io.trait.hierarchy.IntVertexPropertyType;
import forsyde.io.trait.hierarchy.StringMapVertexPropertyType;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String Map Vertex Property Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link StringMapVertexPropertyTypeImpl#getValueType <em>Value Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StringMapVertexPropertyTypeImpl extends IntVertexPropertyTypeImpl implements StringMapVertexPropertyType {
	/**
	 * The cached value of the '{@link #getValueType() <em>Value Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueType()
	 * @generated
	 * @ordered
	 */
	protected IntVertexPropertyType valueType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StringMapVertexPropertyTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getStringMapVertexPropertyType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntVertexPropertyType getValueType() {
		return valueType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValueType(IntVertexPropertyType newValueType, NotificationChain msgs) {
		IntVertexPropertyType oldValueType = valueType;
		valueType = newValueType;
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueType(IntVertexPropertyType newValueType) {
		if (newValueType != valueType) {
			NotificationChain msgs = null;
			if (valueType != null)
				msgs = ((InternalEObject)valueType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_TYPE__VALUE_TYPE, null, msgs);
			if (newValueType != null)
				msgs = ((InternalEObject)newValueType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_TYPE__VALUE_TYPE, null, msgs);
			msgs = basicSetValueType(newValueType, msgs);
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
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_TYPE__VALUE_TYPE:
				return basicSetValueType(null, msgs);
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
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_TYPE__VALUE_TYPE:
				return getValueType();
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
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_TYPE__VALUE_TYPE:
				setValueType((IntVertexPropertyType)newValue);
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
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_TYPE__VALUE_TYPE:
				setValueType((IntVertexPropertyType)null);
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
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_TYPE__VALUE_TYPE:
				return valueType != null;
		}
		return eDynamicIsSet(featureID);
	}

} //StringMapVertexPropertyTypeImpl
