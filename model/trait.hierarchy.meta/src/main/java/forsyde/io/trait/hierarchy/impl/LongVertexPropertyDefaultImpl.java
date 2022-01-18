/**
 */
package forsyde.io.trait.hierarchy.impl;

import forsyde.io.trait.hierarchy.LongVertexPropertyDefault;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Long Vertex Property Default</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link LongVertexPropertyDefaultImpl#getLongValue <em>Long Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LongVertexPropertyDefaultImpl extends MinimalEObjectImpl.Container implements LongVertexPropertyDefault {
	/**
	 * The default value of the '{@link #getLongValue() <em>Long Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongValue()
	 * @generated
	 * @ordered
	 */
	protected static final long LONG_VALUE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getLongValue() <em>Long Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongValue()
	 * @generated
	 * @ordered
	 */
	protected long longValue = LONG_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LongVertexPropertyDefaultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getLongVertexPropertyDefault();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getLongValue() {
		return longValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLongValue(long newLongValue) {
		longValue = newLongValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HierarchyPackageImpl.LONG_VERTEX_PROPERTY_DEFAULT__LONG_VALUE:
				return getLongValue();
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
			case HierarchyPackageImpl.LONG_VERTEX_PROPERTY_DEFAULT__LONG_VALUE:
				setLongValue((Long)newValue);
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
			case HierarchyPackageImpl.LONG_VERTEX_PROPERTY_DEFAULT__LONG_VALUE:
				setLongValue(LONG_VALUE_EDEFAULT);
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
			case HierarchyPackageImpl.LONG_VERTEX_PROPERTY_DEFAULT__LONG_VALUE:
				return longValue != LONG_VALUE_EDEFAULT;
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
		result.append(" (longValue: ");
		result.append(longValue);
		result.append(')');
		return result.toString();
	}

} //LongVertexPropertyDefaultImpl
