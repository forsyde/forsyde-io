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
 * &lt;!-- begin-user-doc --&gt;
 * An implementation of the model object '<em><b>Vertex Property Spec</b>&lt;/em&gt;'.
 * &lt;!-- end-user-doc --&gt;
 * &lt; &gt;
 * The following features are implemented:
 * &lt;/p&gt;
 * &lt; &gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.VertexPropertySpecImpl#getName <em>Name</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.VertexPropertySpecImpl#getPropertyType <em>Property Type</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.VertexPropertySpecImpl#getPropertyDefault <em>Property Default</em>}&lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @generated
 */
public class VertexPropertySpecImpl extends MinimalEObjectImpl.Container implements VertexPropertySpec {
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
	 * The cached value of the '{@link #getPropertyType() <em>Property Type&lt;/em&gt;}' containment reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getPropertyType()
	 * @generated
	 * @ordered
	 */
	protected VertexPropertyType propertyType;

	/**
	 * The cached value of the '{@link #getPropertyDefault() <em>Property Default&lt;/em&gt;}' containment reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getPropertyDefault()
	 * @generated
	 * @ordered
	 */
	protected VertexPropertyDefault propertyDefault;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	protected VertexPropertySpecImpl() {
		super();
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getVertexPropertySpec();
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
	public VertexPropertyType getPropertyType() {
		return propertyType;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public NotificationChain basicSetPropertyType(VertexPropertyType newPropertyType, NotificationChain msgs) {
		VertexPropertyType oldPropertyType = propertyType;
		propertyType = newPropertyType;
		return msgs;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public VertexPropertyDefault getPropertyDefault() {
		return propertyDefault;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public NotificationChain basicSetPropertyDefault(VertexPropertyDefault newPropertyDefault, NotificationChain msgs) {
		VertexPropertyDefault oldPropertyDefault = propertyDefault;
		propertyDefault = newPropertyDefault;
		return msgs;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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

} //VertexPropertySpecImpl
