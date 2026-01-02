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
package eu.kalafatic.maintain.core.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import eu.kalafatic.maintain.core.generators.XMLGenerator;
import eu.kalafatic.maintain.core.generators.XSDGenerator;
import eu.kalafatic.utils.constants.EProject;

/**
 * The Class class MaintainModel.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@Deprecated
public class MaintainModel {

	// class Model extends LinkedHashMap {
	// List<Model> children = new ArrayList<Model>();
	// Map<String, Object> attributes = new LinkedHashMap<String, Object>();
	// {
	// put("NAME", "");
	// put("CHILDREN", children);
	// put("ATTRIBUTES", attributes);
	// }
	// }

	/**
	 * Gets the single instance of MaintainModel.
	 * @param path the path
	 * @param create the create
	 * @return single instance of MaintainModel
	 */
	public static Map getInstance(String path, boolean create) {
		if (create) {
			return getInstance(path);
		}
		// TODO - load by parser
		return null;
	}

	/**
	 * Gets the single instance of MaintainModel.
	 * @param path the path
	 * @return single instance of MaintainModel
	 */
	public static Map getInstance(String path) {
		// element root
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Map<String, Object> attributes = new LinkedHashMap<String, Object>();
		result.put(EProject.CHILDREN.name(), EProject.MANIFEST);

		File file = new File(path);
		result.put(EProject.OUTPUT.name(), file);

		attributes.put(EProject.INPUT.name(), file);

		List<Map> resultChildren = new ArrayList<Map>();

		// element root/machine
		Map<String, Object> machine = new HashMap<String, Object>();
		machine.put(EProject.CHILDREN.name(), EProject.MACHINE);
		List<Map> machineChildren = new ArrayList<Map>();

		// element root/machine/metadata
		Map<String, Object> hosts = new HashMap<String, Object>();
		hosts.put(EProject.CHILDREN.name(), EProject.METADATA);
		// element root/machine/service
		Map<String, Object> servise = new HashMap<String, Object>();
		servise.put(EProject.CHILDREN.name(), EProject.SERVICE);

		machineChildren.add(hosts);
		machineChildren.add(servise);
		machine.put(EProject.CHILDREN.name(), machineChildren);

		resultChildren.add(machine);

		result.put(EProject.CHILDREN.name(), resultChildren);
		result.put("ATTRIBUTES", attributes);

		return result;
	}

	/**
	 * The main method.
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		XSDGenerator.generateXSD("test/1.xsd");
		XMLGenerator.generateXML("test/2.xml");
	}
}
