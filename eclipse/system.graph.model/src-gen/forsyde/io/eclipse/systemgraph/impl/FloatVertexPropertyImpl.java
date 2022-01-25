/**
 */
package forsyde.io.eclipse.systemgraph.impl;

import forsyde.io.eclipse.systemgraph.FloatVertexProperty;
import forsyde.io.eclipse.systemgraph.SystemGraphPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Float Vertex Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link forsyde.io.eclipse.systemgraph.impl.FloatVertexPropertyImpl#getFloatValue <em>Float Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FloatVertexPropertyImpl extends MinimalEObjectImpl.Container implements FloatVertexProperty {
	/**
	 * The default value of the '{@link #getFloatValue() <em>Float Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFloatValue()
	 * @generated
	 * @ordered
	 */
	protected static final float FLOAT_VALUE_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getFloatValue() <em>Float Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFloatValue()
	 * @generated
	 * @ordered
	 */
	protected float floatValue = FLOAT_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FloatVertexPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SystemGraphPackage.Literals.FLOAT_VERTEX_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getFloatValue() {
		return floatValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFloatValue(float newFloatValue) {
		float oldFloatValue = floatValue;
		floatValue = newFloatValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SystemGraphPackage.FLOAT_VERTEX_PROPERTY__FLOAT_VALUE, oldFloatValue, floatValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SystemGraphPackage.FLOAT_VERTEX_PROPERTY__FLOAT_VALUE:
				return getFloatValue();
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
			case SystemGraphPackage.FLOAT_VERTEX_PROPERTY__FLOAT_VALUE:
				setFloatValue((Float)newValue);
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
			case SystemGraphPackage.FLOAT_VERTEX_PROPERTY__FLOAT_VALUE:
				setFloatValue(FLOAT_VALUE_EDEFAULT);
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
			case SystemGraphPackage.FLOAT_VERTEX_PROPERTY__FLOAT_VALUE:
				return floatValue != FLOAT_VALUE_EDEFAULT;
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
		result.append(" (floatValue: ");
		result.append(floatValue);
		result.append(')');
		return result.toString();
	}

} //FloatVertexPropertyImpl
