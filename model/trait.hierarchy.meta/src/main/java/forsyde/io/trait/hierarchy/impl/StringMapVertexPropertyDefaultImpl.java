/**
 */
package forsyde.io.trait.hierarchy.impl;

import forsyde.io.trait.hierarchy.IntVertexPropertyDefault;
import forsyde.io.trait.hierarchy.StringMapVertexPropertyDefault;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String Map Vertex Property Default</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link StringMapVertexPropertyDefaultImpl#getValues <em>Values</em>}</li>
 *   <li>{@link StringMapVertexPropertyDefaultImpl#getIndexes <em>Indexes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StringMapVertexPropertyDefaultImpl extends IntVertexPropertyDefaultImpl implements StringMapVertexPropertyDefault {
	/**
	 * The cached value of the '{@link #getValues() <em>Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected EList<IntVertexPropertyDefault> values;

	/**
	 * The cached value of the '{@link #getIndexes() <em>Indexes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> indexes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StringMapVertexPropertyDefaultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getStringMapVertexPropertyDefault();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IntVertexPropertyDefault> getValues() {
		if (values == null) {
			values = new BasicInternalEList<IntVertexPropertyDefault>(IntVertexPropertyDefault.class);
		}
		return values;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getIndexes() {
		if (indexes == null) {
			indexes = new BasicInternalEList<String>(String.class);
		}
		return indexes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_DEFAULT__VALUES:
				return ((InternalEList<?>)getValues()).basicRemove(otherEnd, msgs);
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
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_DEFAULT__INT_VALUE:
				return getIntValue();
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_DEFAULT__VALUES:
				return getValues();
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_DEFAULT__INDEXES:
				return getIndexes();
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
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_DEFAULT__INT_VALUE:
				setIntValue((Integer)newValue);
				return;
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_DEFAULT__VALUES:
				getValues().clear();
				getValues().addAll((Collection<? extends IntVertexPropertyDefault>)newValue);
				return;
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_DEFAULT__INDEXES:
				getIndexes().clear();
				getIndexes().addAll((Collection<? extends String>)newValue);
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
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_DEFAULT__INT_VALUE:
				setIntValue(INT_VALUE_EDEFAULT);
				return;
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_DEFAULT__VALUES:
				getValues().clear();
				return;
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_DEFAULT__INDEXES:
				getIndexes().clear();
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
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_DEFAULT__INT_VALUE:
				return intValue != INT_VALUE_EDEFAULT;
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_DEFAULT__VALUES:
				return values != null && !values.isEmpty();
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_DEFAULT__INDEXES:
				return indexes != null && !indexes.isEmpty();
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
		result.append(" (indexes: ");
		result.append(indexes);
		result.append(')');
		return result.toString();
	}

} //StringMapVertexPropertyDefaultImpl
