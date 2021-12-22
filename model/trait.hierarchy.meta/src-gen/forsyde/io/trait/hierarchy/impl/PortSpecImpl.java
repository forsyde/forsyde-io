/**
 */
package forsyde.io.trait.hierarchy.impl;

import forsyde.io.trait.hierarchy.EdgeTraitSpec;
import forsyde.io.trait.hierarchy.PortDirectionEnum;
import forsyde.io.trait.hierarchy.PortSpec;
import forsyde.io.trait.hierarchy.VertexTraitSpec;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * &lt;!-- begin-user-doc --&gt;
 * An implementation of the model object '<em><b>Port Spec</b>&lt;/em&gt;'.
 * &lt;!-- end-user-doc --&gt;
 * &lt; &gt;
 * The following features are implemented:
 * &lt;/p&gt;
 * &lt; &gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.PortSpecImpl#getName <em>Name</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.PortSpecImpl#getDirection <em>Direction</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.PortSpecImpl#getConnectedVertexTrait <em>Connected Vertex Trait</em>}&lt;/li&gt;
 *   <li>{@link forsyde.io.trait.hierarchy.impl.PortSpecImpl#getConnectedEdgeTrait <em>Connected Edge Trait</em>}&lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @generated
 */
public class PortSpecImpl extends MinimalEObjectImpl.Container implements PortSpec {
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
	 * The default value of the '{@link #getDirection() <em>Direction&lt;/em&gt;}' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected static final PortDirectionEnum DIRECTION_EDEFAULT = PortDirectionEnum.FORWARD;

	/**
	 * The cached value of the '{@link #getDirection() <em>Direction&lt;/em&gt;}' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected PortDirectionEnum direction = DIRECTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConnectedVertexTrait() <em>Connected Vertex Trait&lt;/em&gt;}' reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getConnectedVertexTrait()
	 * @generated
	 * @ordered
	 */
	protected VertexTraitSpec connectedVertexTrait;

	/**
	 * The cached value of the '{@link #getConnectedEdgeTrait() <em>Connected Edge Trait&lt;/em&gt;}' reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #getConnectedEdgeTrait()
	 * @generated
	 * @ordered
	 */
	protected EdgeTraitSpec connectedEdgeTrait;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	protected PortSpecImpl() {
		super();
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getPortSpec();
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
	public PortDirectionEnum getDirection() {
		return direction;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public void setDirection(PortDirectionEnum newDirection) {
		direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public VertexTraitSpec getConnectedVertexTrait() {
		if (connectedVertexTrait != null && connectedVertexTrait.eIsProxy()) {
			InternalEObject oldConnectedVertexTrait = (InternalEObject)connectedVertexTrait;
			connectedVertexTrait = (VertexTraitSpec)eResolveProxy(oldConnectedVertexTrait);
			if (connectedVertexTrait != oldConnectedVertexTrait) {
			}
		}
		return connectedVertexTrait;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public VertexTraitSpec basicGetConnectedVertexTrait() {
		return connectedVertexTrait;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public void setConnectedVertexTrait(VertexTraitSpec newConnectedVertexTrait) {
		connectedVertexTrait = newConnectedVertexTrait;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public EdgeTraitSpec getConnectedEdgeTrait() {
		if (connectedEdgeTrait != null && connectedEdgeTrait.eIsProxy()) {
			InternalEObject oldConnectedEdgeTrait = (InternalEObject)connectedEdgeTrait;
			connectedEdgeTrait = (EdgeTraitSpec)eResolveProxy(oldConnectedEdgeTrait);
			if (connectedEdgeTrait != oldConnectedEdgeTrait) {
			}
		}
		return connectedEdgeTrait;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public EdgeTraitSpec basicGetConnectedEdgeTrait() {
		return connectedEdgeTrait;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public void setConnectedEdgeTrait(EdgeTraitSpec newConnectedEdgeTrait) {
		connectedEdgeTrait = newConnectedEdgeTrait;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HierarchyPackageImpl.PORT_SPEC__NAME:
				return getName();
			case HierarchyPackageImpl.PORT_SPEC__DIRECTION:
				return getDirection();
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_VERTEX_TRAIT:
				if (resolve) return getConnectedVertexTrait();
				return basicGetConnectedVertexTrait();
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_EDGE_TRAIT:
				if (resolve) return getConnectedEdgeTrait();
				return basicGetConnectedEdgeTrait();
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
			case HierarchyPackageImpl.PORT_SPEC__NAME:
				setName((String)newValue);
				return;
			case HierarchyPackageImpl.PORT_SPEC__DIRECTION:
				setDirection((PortDirectionEnum)newValue);
				return;
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_VERTEX_TRAIT:
				setConnectedVertexTrait((VertexTraitSpec)newValue);
				return;
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_EDGE_TRAIT:
				setConnectedEdgeTrait((EdgeTraitSpec)newValue);
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
			case HierarchyPackageImpl.PORT_SPEC__NAME:
				setName(NAME_EDEFAULT);
				return;
			case HierarchyPackageImpl.PORT_SPEC__DIRECTION:
				setDirection(DIRECTION_EDEFAULT);
				return;
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_VERTEX_TRAIT:
				setConnectedVertexTrait((VertexTraitSpec)null);
				return;
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_EDGE_TRAIT:
				setConnectedEdgeTrait((EdgeTraitSpec)null);
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
			case HierarchyPackageImpl.PORT_SPEC__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case HierarchyPackageImpl.PORT_SPEC__DIRECTION:
				return direction != DIRECTION_EDEFAULT;
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_VERTEX_TRAIT:
				return connectedVertexTrait != null;
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_EDGE_TRAIT:
				return connectedEdgeTrait != null;
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
		result.append(", direction: ");
		result.append(direction);
		result.append(')');
		return result.toString();
	}

} //PortSpecImpl
