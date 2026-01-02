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
import java.util.Set;

import eu.kalafatic.maintain.core.model.MaintainModel;

/**
 * The Class class XSDGenerator.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class XSDGenerator {

	/** The xml. */
	private static StringBuffer xml = new StringBuffer();
	
	/** The attr. */
	private static StringBuffer attr = new StringBuffer();

	/**
	 * Generate xsd.
	 * @param path the path
	 * @return the map
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Map generateXSD(String path) throws IOException {
		xml = new StringBuffer();
		xml.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>");
		xml.append("<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">");

		Map map = MaintainModel.getInstance(path);
		generateXSD(map);

		xml.append("</xs:schema>");

		FileWriter fw = new FileWriter(path);
		fw.write(xml.toString());
		fw.close();

		return map;
	}

	/**
	 * Generate xsd.
	 * @param object the object
	 */
	private static void generateXSD(Object object) {
		if (object instanceof Map) {
			generateXSD((Map<String, Object>) object);

		} else if (object instanceof List) {
			generateXSD((List<Object>) object);

		} else if (object instanceof Entry) {
			generateXSD((Entry<String, Object>) object);
		}
	}

	/**
	 * Generate xsd.
	 * @param map the map
	 */
	private static void generateXSD(Map<String, Object> map) {
		attr = new StringBuffer();
		xml.append("<xs:element name=\"" + map.get("NAME") + "\">");
		xml.append("<xs:complexType>");
		xml.append("<xs:sequence>");
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			generateXSD(entry);
		}
		xml.append("</xs:sequence>");
		xml.append(attr.toString());
		xml.append("</xs:complexType>");
		xml.append("</xs:element>");

	}

	/**
	 * Generate xsd.
	 * @param list the list
	 */
	private static void generateXSD(List<Object> list) {
		for (Object object : list) {
			generateXSD(object);
		}
	}

	// element
	/**
	 * Generate xsd.
	 * @param entry the entry
	 */
	private static void generateXSD(Entry<String, Object> entry) {
		try {
			if (entry.getKey().equals("NAME")) {
				return;
			}

			// attributes
			if (entry.getValue() instanceof Map) {
				generateXSD(((Map) entry.getValue()).entrySet());

				// children
			} else if (entry.getValue() instanceof List) {
				generateXSD((List) entry.getValue());

			} else {
				// list of simple elements
				generateXSD(entry.getKey(), entry.getValue(), false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// attributes
	/**
	 * Generate xsd.
	 * @param attributes the attributes
	 */
	private static void generateXSD(Set attributes) {
		// sb.append("<xs:sequence>");
		Iterator iterator = attributes.iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = (Entry<String, Object>) iterator
					.next();
			generateXSD(entry.getKey(), entry.getValue(), true);
		}
		// sb.append("</xs:sequence>");
	}

	// simple elements
	/**
	 * Generate xsd.
	 * @param key the key
	 * @param value the value
	 * @param attribute the attribute
	 */
	private static void generateXSD(String key, Object value, boolean attribute) {
		// if (value instanceof String) {
		// sb.append("<xs:element name=\"" + key + "type=\"xs:string\"/>");
		// } else if (value instanceof File) {
		//
		// sb.append("<xs:element name=\"" + key + "\" " + value + "/>");
		// } else {
		// sb.append("<xs:element name=\"" + key + "type=\"xs:string\"/>");
		// }
		if (attribute) {
			attr.append("<xs:attribute name=\"" + key
					+ "\" type=\"xs:string\"/>");
		} else {
			xml.append("<xs:element name=\"" + key + "\" type=\"xs:string\"/>");
		}

	}

}
