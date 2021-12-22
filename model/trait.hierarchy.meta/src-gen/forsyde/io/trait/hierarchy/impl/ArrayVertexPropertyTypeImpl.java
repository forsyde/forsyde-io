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
 * &lt;!-- begin-user-doc --&gt;
 * An implementation of the model object '<em><b>Array Vertex Property Type</b>&lt;/em&gt;'.
 * &lt;!-- end-user-doc --&gt;
 * &lt; &gt;
 * The following features are implemented:
 * &lt;/p&gt;
 * &lt; &gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.ArrayVertexPropertyTypeImpl#getValueTypes <em>Value Types</em>}&lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @generated
 */
public class ArrayVertexPropertyTypeImpl extends MinimalEObjectImpl.Container implements ArrayVertexPropertyType {
	/**
	 * The cached value of the '{@link #getValueTypes() <em>Value Types&lt;/em&gt;}' containment reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getValueTypes()
	 * @generated
	 * @ordered
	 */
	protected VertexPropertyType valueTypes;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	protected ArrayVertexPropertyTypeImpl() {
		super();
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getArrayVertexPropertyType();
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public VertexPropertyType getValueTypes() {
		return valueTypes;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public NotificationChain basicSetValueTypes(VertexPropertyType newValueTypes, NotificationChain msgs) {
		VertexPropertyType oldValueTypes = valueTypes;
		valueTypes = newValueTypes;
		return msgs;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
