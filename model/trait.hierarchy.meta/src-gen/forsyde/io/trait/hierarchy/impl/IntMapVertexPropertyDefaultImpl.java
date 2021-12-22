/**
 */
package forsyde.io.trait.hierarchy.impl;

import forsyde.io.trait.hierarchy.IntMapVertexPropertyDefault;
import forsyde.io.trait.hierarchy.VertexPropertyDefault;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * &lt;!-- begin-user-doc --&gt;
 * An implementation of the model object '<em><b>Int Map Vertex Property Default</b>&lt;/em&gt;'.
 * &lt;!-- end-user-doc --&gt;
 * &lt; &gt;
 * The following features are implemented:
 * &lt;/p&gt;
 * &lt; &gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.IntMapVertexPropertyDefaultImpl#getValues <em>Values</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.IntMapVertexPropertyDefaultImpl#getIndexes <em>Indexes</em>}&lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @generated
 */
public class IntMapVertexPropertyDefaultImpl extends MinimalEObjectImpl.Container implements IntMapVertexPropertyDefault {
	/**
	 * The cached value of the '{@link #getValues() <em>Values&lt;/em&gt;}' containment reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected EList<VertexPropertyDefault> values;

	/**
	 * The cached value of the '{@link #getIndexes() <em>Indexes&lt;/em&gt;}' attribute list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getIndexes()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> indexes;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	protected IntMapVertexPropertyDefaultImpl() {
		super();
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getIntMapVertexPropertyDefault();
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public EList<VertexPropertyDefault> getValues() {
		if (values == null) {
			values = new BasicInternalEList<VertexPropertyDefault>(VertexPropertyDefault.class);
		}
		return values;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public EList<Integer> getIndexes() {
		if (indexes == null) {
			indexes = new BasicInternalEList<Integer>(int.class);
		}
		return indexes;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HierarchyPackageImpl.INT_MAP_VERTEX_PROPERTY_DEFAULT__VALUES:
				return ((InternalEList<?>)getValues()).basicRemove(otherEnd, msgs);
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
			case HierarchyPackageImpl.INT_MAP_VERTEX_PROPERTY_DEFAULT__VALUES:
				return getValues();
			case HierarchyPackageImpl.INT_MAP_VERTEX_PROPERTY_DEFAULT__INDEXES:
				return getIndexes();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case HierarchyPackageImpl.INT_MAP_VERTEX_PROPERTY_DEFAULT__VALUES:
				getValues().clear();
				getValues().addAll((Collection<? extends VertexPropertyDefault>)newValue);
				return;
			case HierarchyPackageImpl.INT_MAP_VERTEX_PROPERTY_DEFAULT__INDEXES:
				getIndexes().clear();
				getIndexes().addAll((Collection<? extends Integer>)newValue);
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
			case HierarchyPackageImpl.INT_MAP_VERTEX_PROPERTY_DEFAULT__VALUES:
				getValues().clear();
				return;
			case HierarchyPackageImpl.INT_MAP_VERTEX_PROPERTY_DEFAULT__INDEXES:
				getIndexes().clear();
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
			case HierarchyPackageImpl.INT_MAP_VERTEX_PROPERTY_DEFAULT__VALUES:
				return values != null && !values.isEmpty();
			case HierarchyPackageImpl.INT_MAP_VERTEX_PROPERTY_DEFAULT__INDEXES:
				return indexes != null && !indexes.isEmpty();
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
		result.append(" (indexes: ");
		result.append(indexes);
		result.append(')');
		return result.toString();
	}

} //IntMapVertexPropertyDefaultImpl
