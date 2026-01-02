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
package eu.kalafatic.maintain.core.xsd;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;

/**
 * The Class class XSDXMLValidator.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class XSDXMLValidator implements Runnable {

	/** The xml file. */
	private File xmlFile;
	
	/** The xsds. */
	private File[] xsds;
	
	/** The document. */
	Document document;

	/** The result. */
	String result = "Valid";

	/**
	 * Instantiates a new XSDXML validator.
	 * @param xmlFile the xml file
	 * @param xsds the xsds
	 */
	public XSDXMLValidator(File xmlFile, File... xsds) {
		this.xmlFile = xmlFile;
		this.xsds = xsds;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			if (document == null) {
				validate(xmlFile, xsds);
			} else {
				validate(document, xsds);
			}
		} catch (Exception e) {
			result = e.getLocalizedMessage();
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Validate.
	 * @param xmlFile the xml file
	 * @param xsds the xsds
	 * @throws Exception the exception
	 */
	public void validate(File xmlFile, File... xsds) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(xmlFile);

		validate(document, xsds);
	}

	// ---------------------------------------------------------------

	/**
	 * Validate.
	 * @param document the document
	 * @param xsds the xsds
	 * @throws Exception the exception
	 */
	public void validate(Document document, File... xsds) throws Exception {
		Source source = new DOMSource(document);

		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		StreamSource[] schemaDocuments = new StreamSource[xsds.length];

		for (int i = 0; i < xsds.length; i++) {
			schemaDocuments[i] = new StreamSource(new FileInputStream(xsds[i]));
		}

		Schema schema = schemaFactory.newSchema(schemaDocuments);

		Validator validator = schema.newValidator();
		validator.validate(source);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the result.
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
}
