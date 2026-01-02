/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.maintain.model.maintain.impl;

import eu.kalafatic.maintain.model.maintain.*;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MaintainFactoryImpl extends EFactoryImpl implements MaintainFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MaintainFactory init() {
		try {
			MaintainFactory theMaintainFactory = (MaintainFactory)EPackage.Registry.INSTANCE.getEFactory("http:///maintain.ecore"); 
			if (theMaintainFactory != null) {
				return theMaintainFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MaintainFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MaintainFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case MaintainPackage.MAINTAIN: return createMaintain();
			case MaintainPackage.NODE: return createNode();
			case MaintainPackage.STRING_TO_NODE_MAP_ENTRY: return (EObject)createStringToNodeMapEntry();
			case MaintainPackage.PROJECT: return createProject();
			case MaintainPackage.SUBPROJECT: return createSubproject();
			case MaintainPackage.SERVICE: return createService();
			case MaintainPackage.MACHINE: return createMachine();
			case MaintainPackage.METADATA: return createMetadata();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Maintain createMaintain() {
		MaintainImpl maintain = new MaintainImpl();
		return maintain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node createNode() {
		NodeImpl node = new NodeImpl();
		return node;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Node> createStringToNodeMapEntry() {
		StringToNodeMapEntryImpl stringToNodeMapEntry = new StringToNodeMapEntryImpl();
		return stringToNodeMapEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Project createProject() {
		ProjectImpl project = new ProjectImpl();
		return project;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Subproject createSubproject() {
		SubprojectImpl subproject = new SubprojectImpl();
		return subproject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Service createService() {
		ServiceImpl service = new ServiceImpl();
		return service;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Machine createMachine() {
		MachineImpl machine = new MachineImpl();
		return machine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Metadata createMetadata() {
		MetadataImpl metadata = new MetadataImpl();
		return metadata;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MaintainPackage getMaintainPackage() {
		return (MaintainPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MaintainPackage getPackage() {
		return MaintainPackage.eINSTANCE;
	}

} //MaintainFactoryImpl
