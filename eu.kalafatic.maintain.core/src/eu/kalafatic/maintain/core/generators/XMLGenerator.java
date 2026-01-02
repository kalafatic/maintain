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
package eu.kalafatic.maintain.core.generators;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import eu.kalafatic.maintain.core.model.MaintainModel;

/**
 * The Class class XMLGenerator.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class XMLGenerator {

	/** The xml. */
	private static StringBuffer xml = new StringBuffer();
	
	/** The regex. */
	private static String regex = "(NAME|CHILDREN|ATTRIBUTES)";

	/**
	 * Generate xml.
	 * @param path the path
	 * @return the map
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Map generateXML(String path) throws IOException {
		xml = new StringBuffer();
		xml.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>");

		Map map = MaintainModel.getInstance(path);
		generateXML(map);

		FileWriter fw = new FileWriter(path);
		fw.write(xml.toString());
		fw.close();

		return map;
	}

	/**
	 * Generate xml.
	 * @param map the map
	 */
	private static void generateXML(Map<String, Object> map) {
		xml.append("<" + map.get("NAME") + " ");
		addAttributes(map);
		xml.append(">");

		addElements(map);
		addChildren(map);

		xml.append("</" + map.get("NAME") + ">");
	}

	/**
	 * Adds the children.
	 * @param map the map
	 */
	private static void addChildren(Map<String, Object> map) {
		List children = (List) map.get("CHILDREN");
		if (children != null) {
			for (Object object : children) {
				generateXML((Map<String, Object>) object);
			}
		}
	}

	/**
	 * Adds the elements.
	 * @param map the map
	 */
	private static void addElements(Map<String, Object> map) {
		if (!map.values().isEmpty()) {
			Iterator<Entry<String, Object>> iterator = map.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();

				if (!Pattern.matches(regex, entry.getKey())) {
					xml.append("<" + entry.getKey() + ">");
					xml.append(entry.getValue());
					xml.append("</" + entry.getKey() + ">");
				}
			}
		}
	}

	/**
	 * Adds the attributes.
	 * @param map the map
	 */
	private static void addAttributes(Map<String, Object> map) {
		Map<String, Object> attributes = (Map<String, Object>) map
				.get("ATTRIBUTES");
		if (attributes != null) {
			Iterator<Entry<String, Object>> iterator = attributes.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				xml.append(entry.getKey() + "=\"" + entry.getValue() + "\"");
			}
		}
	}
}
