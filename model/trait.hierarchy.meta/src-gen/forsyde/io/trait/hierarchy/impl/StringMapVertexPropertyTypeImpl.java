/**
 */
package forsyde.io.trait.hierarchy.impl;

import forsyde.io.trait.hierarchy.IntVertexPropertyType;
import forsyde.io.trait.hierarchy.StringMapVertexPropertyType;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

/**
 * &lt;!-- begin-user-doc --&gt;
 * An implementation of the model object '<em><b>String Map Vertex Property Type</b>&lt;/em&gt;'.
 * &lt;!-- end-user-doc --&gt;
 * &lt; &gt;
 * The following features are implemented:
 * &lt;/p&gt;
 * &lt; &gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.StringMapVertexPropertyTypeImpl#getValueType <em>Value Type</em>}&lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @generated
 */
public class StringMapVertexPropertyTypeImpl extends IntVertexPropertyTypeImpl implements StringMapVertexPropertyType {
	/**
	 * The cached value of the '{@link #getValueType() <em>Value Type&lt;/em&gt;}' containment reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getValueType()
	 * @generated
	 * @ordered
	 */
	protected IntVertexPropertyType valueType;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	protected StringMapVertexPropertyTypeImpl() {
		super();
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getStringMapVertexPropertyType();
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public IntVertexPropertyType getValueType() {
		return valueType;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public NotificationChain basicSetValueType(IntVertexPropertyType newValueType, NotificationChain msgs) {
		IntVertexPropertyType oldValueType = valueType;
		valueType = newValueType;
		return msgs;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
