/**
 */
package forsyde.io.eclipse.systemgraph.impl;

import forsyde.io.eclipse.systemgraph.Edge;
import forsyde.io.eclipse.systemgraph.SystemGraphPackage;
import forsyde.io.eclipse.systemgraph.Vertex;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link forsyde.io.eclipse.systemgraph.impl.EdgeImpl#getSourceport <em>Sourceport</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.impl.EdgeImpl#getTargetport <em>Targetport</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.impl.EdgeImpl#getTraits <em>Traits</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.impl.EdgeImpl#getSource <em>Source</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.impl.EdgeImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EdgeImpl extends MinimalEObjectImpl.Container implements Edge {
	/**
	 * The default value of the '{@link #getSourceport() <em>Sourceport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceport()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCEPORT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceport() <em>Sourceport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceport()
	 * @generated
	 * @ordered
	 */
	protected String sourceport = SOURCEPORT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetport() <em>Targetport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetport()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGETPORT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetport() <em>Targetport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetport()
	 * @generated
	 * @ordered
	 */
	protected String targetport = TARGETPORT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTraits() <em>Traits</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTraits()
	 * @generated
	 * @ordered
	 */
	protected EList<String> traits;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Vertex source;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Vertex target;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EdgeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SystemGraphPackage.Literals.EDGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceport() {
		return sourceport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceport(String newSourceport) {
		String oldSourceport = sourceport;
		sourceport = newSourceport;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SystemGraphPackage.EDGE__SOURCEPORT, oldSourceport, sourceport));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetport() {
		return targetport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetport(String newTargetport) {
		String oldTargetport = targetport;
		targetport = newTargetport;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SystemGraphPackage.EDGE__TARGETPORT, oldTargetport, targetport));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getTraits() {
		if (traits == null) {
			traits = new EDataTypeEList<String>(String.class, this, SystemGraphPackage.EDGE__TRAITS);
		}
		return traits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vertex getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (Vertex)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SystemGraphPackage.EDGE__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vertex basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Vertex newSource) {
		Vertex oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SystemGraphPackage.EDGE__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vertex getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (Vertex)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SystemGraphPackage.EDGE__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vertex basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Vertex newTarget) {
		Vertex oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SystemGraphPackage.EDGE__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SystemGraphPackage.EDGE__SOURCEPORT:
				return getSourceport();
			case SystemGraphPackage.EDGE__TARGETPORT:
				return getTargetport();
			case SystemGraphPackage.EDGE__TRAITS:
				return getTraits();
			case SystemGraphPackage.EDGE__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case SystemGraphPackage.EDGE__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
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
			case SystemGraphPackage.EDGE__SOURCEPORT:
				setSourceport((String)newValue);
				return;
			case SystemGraphPackage.EDGE__TARGETPORT:
				setTargetport((String)newValue);
				return;
			case SystemGraphPackage.EDGE__TRAITS:
				getTraits().clear();
				getTraits().addAll((Collection<? extends String>)newValue);
				return;
			case SystemGraphPackage.EDGE__SOURCE:
				setSource((Vertex)newValue);
				return;
			case SystemGraphPackage.EDGE__TARGET:
				setTarget((Vertex)newValue);
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
			case SystemGraphPackage.EDGE__SOURCEPORT:
				setSourceport(SOURCEPORT_EDEFAULT);
				return;
			case SystemGraphPackage.EDGE__TARGETPORT:
				setTargetport(TARGETPORT_EDEFAULT);
				return;
			case SystemGraphPackage.EDGE__TRAITS:
				getTraits().clear();
				return;
			case SystemGraphPackage.EDGE__SOURCE:
				setSource((Vertex)null);
				return;
			case SystemGraphPackage.EDGE__TARGET:
				setTarget((Vertex)null);
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
			case SystemGraphPackage.EDGE__SOURCEPORT:
				return SOURCEPORT_EDEFAULT == null ? sourceport != null : !SOURCEPORT_EDEFAULT.equals(sourceport);
			case SystemGraphPackage.EDGE__TARGETPORT:
				return TARGETPORT_EDEFAULT == null ? targetport != null : !TARGETPORT_EDEFAULT.equals(targetport);
			case SystemGraphPackage.EDGE__TRAITS:
				return traits != null && !traits.isEmpty();
			case SystemGraphPackage.EDGE__SOURCE:
				return source != null;
			case SystemGraphPackage.EDGE__TARGET:
				return target != null;
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
		result.append(" (sourceport: ");
		result.append(sourceport);
		result.append(", targetport: ");
		result.append(targetport);
		result.append(", traits: ");
		result.append(traits);
		result.append(')');
		return result.toString();
	}

} //EdgeImpl
