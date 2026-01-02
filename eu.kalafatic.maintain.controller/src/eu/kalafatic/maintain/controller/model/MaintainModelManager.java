/*******************************************************************************
 * Copyright (c) 2010, Petr Kalafatic (gemini@kalafatic.eu).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU GPL Version 3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.txt
 *
 * Contributors:
 *     Petr Kalafatic - initial API and implementation
 ******************************************************************************/
package eu.kalafatic.maintain.controller.model;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import eu.kalafatic.maintain.model.maintain.Maintain;
import eu.kalafatic.maintain.model.maintain.MaintainFactory;
import eu.kalafatic.maintain.model.maintain.MaintainPackage;
import eu.kalafatic.utils.model.AModelManager;
import eu.kalafatic.utils.model.ModelUtils;

/**
 * The Class class MaintainModelManager.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class MaintainModelManager extends AModelManager {

	/** The model name. */
	private final String MODEL_NAME = "Model.maintain";

	/** The instance. */
	private volatile static MaintainModelManager INSTANCE;

	/**
	 * Instantiates a new maintain model manager.
	 */
	public MaintainModelManager() {
		initModel();
	}

	/**
	 * Gets the single instance of MaintainModelManager.
	 * @return single instance of MaintainModelManager
	 */
	public static MaintainModelManager getInstance() {
		if (INSTANCE == null) {
			synchronized (MaintainModelManager.class) {
				INSTANCE = new MaintainModelManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.utils.model.IModelManager#initModel()
	 */
	@Override
	public void initModel() {
		try {
			// String models =
			// PREFERENCES.get(ECorePreferences.MODELS_LOC.getName(),
			// (String) ECorePreferences.MODELS_LOC.getDef());

			String models = "C:\\GE\\workspace\\models";
			super.initModel(models, "Maintain", MODEL_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.utils.model.IModelManager#createModel()
	 */
	@Override
	public void createModel() {
		try {
			ResourceSetImpl resourceSet = new ResourceSetImpl();
			// Register the appropriate resource factory to handle all file
			// extensions.
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

			// Register the package to ensure it is available during loading.
			resourceSet.getPackageRegistry().put(MaintainPackage.eNS_URI, MaintainPackage.eINSTANCE);

			resource = resourceSet.createResource(modelURI);
			model = MaintainFactory.eINSTANCE.createMaintain();
			resource.getContents().add(getModel());
			resource.save(ModelUtils.SAVE_OPTIONS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.utils.model.IModelManager#setUpModel()
	 */
	@Override
	public void setUpModel() {
		// try {
		// Node gateway = NetUtils.getGateway();
		//
		// Node localHost = MaintainFactory.eINSTANCE.createNode();
		// localHost.setIp(InetAddress.getLocalHost().getHostAddress());
		// localHost.setHost(InetAddress.getLocalHost().getCanonicalHostName());
		//
		// localHost.getChildren().put(gateway.getHost(), gateway);
		// maintain.getChildren().put(localHost.getHost(), localHost);
		// } catch (UnknownHostException e) {
		// e.printStackTrace();
		// }
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.utils.model.IModelManager#getModel()
	 */
	@Override
	public Maintain getModel() {
		return (Maintain) model;
	}

}
