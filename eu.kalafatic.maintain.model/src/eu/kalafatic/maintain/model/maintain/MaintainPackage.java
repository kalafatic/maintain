/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.maintain.model.maintain;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see eu.kalafatic.maintain.model.maintain.MaintainFactory
 * @model kind="package"
 * @generated
 */
public interface MaintainPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "maintain";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///maintain.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "maintain";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MaintainPackage eINSTANCE = eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.kalafatic.maintain.model.maintain.impl.MaintainImpl <em>Maintain</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainImpl
	 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getMaintain()
	 * @generated
	 */
	int MAINTAIN = 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAINTAIN__CHILDREN = 0;

	/**
	 * The number of structural features of the '<em>Maintain</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAINTAIN_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link eu.kalafatic.maintain.model.maintain.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.maintain.model.maintain.impl.NodeImpl
	 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 1;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.kalafatic.maintain.model.maintain.impl.StringToNodeMapEntryImpl <em>String To Node Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.maintain.model.maintain.impl.StringToNodeMapEntryImpl
	 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getStringToNodeMapEntry()
	 * @generated
	 */
	int STRING_TO_NODE_MAP_ENTRY = 2;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_NODE_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_NODE_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To Node Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_NODE_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link eu.kalafatic.maintain.model.maintain.impl.ProjectImpl <em>Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.maintain.model.maintain.impl.ProjectImpl
	 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getProject()
	 * @generated
	 */
	int PROJECT = 3;

	/**
	 * The number of structural features of the '<em>Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_FEATURE_COUNT = NODE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.kalafatic.maintain.model.maintain.impl.SubprojectImpl <em>Subproject</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.maintain.model.maintain.impl.SubprojectImpl
	 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getSubproject()
	 * @generated
	 */
	int SUBPROJECT = 4;

	/**
	 * The number of structural features of the '<em>Subproject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBPROJECT_FEATURE_COUNT = PROJECT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.kalafatic.maintain.model.maintain.impl.ServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.maintain.model.maintain.impl.ServiceImpl
	 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getService()
	 * @generated
	 */
	int SERVICE = 5;

	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_FEATURE_COUNT = NODE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.kalafatic.maintain.model.maintain.impl.MachineImpl <em>Machine</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.maintain.model.maintain.impl.MachineImpl
	 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getMachine()
	 * @generated
	 */
	int MACHINE = 6;

	/**
	 * The number of structural features of the '<em>Machine</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE_FEATURE_COUNT = NODE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.kalafatic.maintain.model.maintain.impl.MetadataImpl <em>Metadata</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.maintain.model.maintain.impl.MetadataImpl
	 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getMetadata()
	 * @generated
	 */
	int METADATA = 7;

	/**
	 * The number of structural features of the '<em>Metadata</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_FEATURE_COUNT = NODE_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link eu.kalafatic.maintain.model.maintain.Maintain <em>Maintain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Maintain</em>'.
	 * @see eu.kalafatic.maintain.model.maintain.Maintain
	 * @generated
	 */
	EClass getMaintain();

	/**
	 * Returns the meta object for the map '{@link eu.kalafatic.maintain.model.maintain.Maintain#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Children</em>'.
	 * @see eu.kalafatic.maintain.model.maintain.Maintain#getChildren()
	 * @see #getMaintain()
	 * @generated
	 */
	EReference getMaintain_Children();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.maintain.model.maintain.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see eu.kalafatic.maintain.model.maintain.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To Node Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String To Node Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="eu.kalafatic.maintain.model.maintain.Node" valueContainment="true"
	 * @generated
	 */
	EClass getStringToNodeMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToNodeMapEntry()
	 * @generated
	 */
	EAttribute getStringToNodeMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToNodeMapEntry()
	 * @generated
	 */
	EReference getStringToNodeMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.maintain.model.maintain.Project <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Project</em>'.
	 * @see eu.kalafatic.maintain.model.maintain.Project
	 * @generated
	 */
	EClass getProject();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.maintain.model.maintain.Subproject <em>Subproject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Subproject</em>'.
	 * @see eu.kalafatic.maintain.model.maintain.Subproject
	 * @generated
	 */
	EClass getSubproject();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.maintain.model.maintain.Service <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service</em>'.
	 * @see eu.kalafatic.maintain.model.maintain.Service
	 * @generated
	 */
	EClass getService();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.maintain.model.maintain.Machine <em>Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Machine</em>'.
	 * @see eu.kalafatic.maintain.model.maintain.Machine
	 * @generated
	 */
	EClass getMachine();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.maintain.model.maintain.Metadata <em>Metadata</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Metadata</em>'.
	 * @see eu.kalafatic.maintain.model.maintain.Metadata
	 * @generated
	 */
	EClass getMetadata();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MaintainFactory getMaintainFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link eu.kalafatic.maintain.model.maintain.impl.MaintainImpl <em>Maintain</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainImpl
		 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getMaintain()
		 * @generated
		 */
		EClass MAINTAIN = eINSTANCE.getMaintain();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAINTAIN__CHILDREN = eINSTANCE.getMaintain_Children();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.maintain.model.maintain.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.maintain.model.maintain.impl.NodeImpl
		 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.maintain.model.maintain.impl.StringToNodeMapEntryImpl <em>String To Node Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.maintain.model.maintain.impl.StringToNodeMapEntryImpl
		 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getStringToNodeMapEntry()
		 * @generated
		 */
		EClass STRING_TO_NODE_MAP_ENTRY = eINSTANCE.getStringToNodeMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_NODE_MAP_ENTRY__KEY = eINSTANCE.getStringToNodeMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_TO_NODE_MAP_ENTRY__VALUE = eINSTANCE.getStringToNodeMapEntry_Value();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.maintain.model.maintain.impl.ProjectImpl <em>Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.maintain.model.maintain.impl.ProjectImpl
		 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getProject()
		 * @generated
		 */
		EClass PROJECT = eINSTANCE.getProject();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.maintain.model.maintain.impl.SubprojectImpl <em>Subproject</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.maintain.model.maintain.impl.SubprojectImpl
		 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getSubproject()
		 * @generated
		 */
		EClass SUBPROJECT = eINSTANCE.getSubproject();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.maintain.model.maintain.impl.ServiceImpl <em>Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.maintain.model.maintain.impl.ServiceImpl
		 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getService()
		 * @generated
		 */
		EClass SERVICE = eINSTANCE.getService();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.maintain.model.maintain.impl.MachineImpl <em>Machine</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.maintain.model.maintain.impl.MachineImpl
		 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getMachine()
		 * @generated
		 */
		EClass MACHINE = eINSTANCE.getMachine();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.maintain.model.maintain.impl.MetadataImpl <em>Metadata</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.maintain.model.maintain.impl.MetadataImpl
		 * @see eu.kalafatic.maintain.model.maintain.impl.MaintainPackageImpl#getMetadata()
		 * @generated
		 */
		EClass METADATA = eINSTANCE.getMetadata();

	}

} //MaintainPackage
