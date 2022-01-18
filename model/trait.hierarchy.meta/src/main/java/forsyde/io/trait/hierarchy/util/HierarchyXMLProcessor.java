/**
 */
package forsyde.io.trait.hierarchy.util;

import forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class HierarchyXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HierarchyXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		HierarchyPackageImpl.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the HierarchyResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new HierarchyResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new HierarchyResourceFactoryImpl());
		}
		return registrations;
	}

} //HierarchyXMLProcessor
