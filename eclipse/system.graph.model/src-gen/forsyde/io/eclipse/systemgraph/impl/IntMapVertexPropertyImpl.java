/**
 */
package forsyde.io.eclipse.systemgraph.impl;

import forsyde.io.eclipse.systemgraph.IntMapVertexProperty;
import forsyde.io.eclipse.systemgraph.SystemGraphPackage;
import forsyde.io.eclipse.systemgraph.VertexProperty;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Int Map Vertex Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link forsyde.io.eclipse.systemgraph.impl.IntMapVertexPropertyImpl#getValues <em>Values</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.impl.IntMapVertexPropertyImpl#getIndexes <em>Indexes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IntMapVertexPropertyImpl extends MinimalEObjectImpl.Container implements IntMapVertexProperty {
	/**
	 * The cached value of the '{@link #getValues() <em>Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected EList<VertexProperty> values;

	/**
	 * The cached value of the '{@link #getIndexes() <em>Indexes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexes()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> indexes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IntMapVertexPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SystemGraphPackage.Literals.INT_MAP_VERTEX_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VertexProperty> getValues() {
		if (values == null) {
			values = new EObjectContainmentEList<VertexProperty>(VertexProperty.class, this, SystemGraphPackage.INT_MAP_VERTEX_PROPERTY__VALUES);
		}
		return values;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getIndexes() {
		if (indexes == null) {
			indexes = new EDataTypeEList<Integer>(Integer.class, this, SystemGraphPackage.INT_MAP_VERTEX_PROPERTY__INDEXES);
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
			case SystemGraphPackage.INT_MAP_VERTEX_PROPERTY__VALUES:
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
			case SystemGraphPackage.INT_MAP_VERTEX_PROPERTY__VALUES:
				return getValues();
			case SystemGraphPackage.INT_MAP_VERTEX_PROPERTY__INDEXES:
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
			case SystemGraphPackage.INT_MAP_VERTEX_PROPERTY__VALUES:
				getValues().clear();
				getValues().addAll((Collection<? extends VertexProperty>)newValue);
				return;
			case SystemGraphPackage.INT_MAP_VERTEX_PROPERTY__INDEXES:
				getIndexes().clear();
				getIndexes().addAll((Collection<? extends Integer>)newValue);
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
			case SystemGraphPackage.INT_MAP_VERTEX_PROPERTY__VALUES:
				getValues().clear();
				return;
			case SystemGraphPackage.INT_MAP_VERTEX_PROPERTY__INDEXES:
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
			case SystemGraphPackage.INT_MAP_VERTEX_PROPERTY__VALUES:
				return values != null && !values.isEmpty();
			case SystemGraphPackage.INT_MAP_VERTEX_PROPERTY__INDEXES:
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

} //IntMapVertexPropertyImpl
