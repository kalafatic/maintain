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
package eu.kalafatic.maintain.core.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import eu.kalafatic.utils.constants.EProject;

/**
 * The Class class Utils.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Utils {

	/** The VALI d_ ip v4_ pattern. */
	private static Pattern VALID_IPV4_PATTERN = null;
	
	/** The VALI d_ ip v6_ pattern. */
	private static Pattern VALID_IPV6_PATTERN = null;
	
	/** The Constant ipv4Pattern. */
	private static final String ipv4Pattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
	
	/** The Constant ipv6Pattern. */
	private static final String ipv6Pattern = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";

	static {
		try {
			VALID_IPV4_PATTERN = Pattern.compile(ipv4Pattern, Pattern.CASE_INSENSITIVE);
			VALID_IPV6_PATTERN = Pattern.compile(ipv6Pattern, Pattern.CASE_INSENSITIVE);
		} catch (PatternSyntaxException e) {
			// logger.severe("Unable to compile pattern", e);
		}
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Open file.
	 * @param isFile the is file
	 * @param args the args
	 * @return the string
	 */
	public static String openFile(boolean isFile, Object... args) {
		Shell shell = new Shell(Display.getCurrent());
		String filterPath = (args.length > 0) ? (String) args[0] : null;
		String[] filterExt = (args.length > 1) ? (String[]) args[1] : null;
		String result = null;

		if (isFile) {
			FileDialog dialog = new FileDialog(shell);
			dialog.setFilterPath(filterPath);
			dialog.setFilterExtensions(filterExt);
			result = dialog.open();
		} else {
			DirectoryDialog dialog = new DirectoryDialog(shell);
			dialog.setFilterPath(filterPath);
			result = dialog.open();
		}
		if (result == null) {
			MessageDialog.open(MessageDialog.WARNING, shell, "", "Nothing selected", SWT.SHEET);
		}
		return result;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the filename filter.
	 * @param ext the ext
	 * @return the filename filter
	 */
	public FilenameFilter getFilenameFilter(String ext) {
		return new OnlyExt(ext);
	}

	// ---------------------------------------------------------------

	/**
	 * The Class class OnlyExt.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	class OnlyExt implements FilenameFilter {
		
		/** The ext. */
		String ext;

		/**
		 * Instantiates a new only ext.
		 * @param ext the ext
		 */
		public OnlyExt(String ext) {
			this.ext = ext;
		}

		/* (non-Javadoc)
		 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
		 */
		@Override
		public boolean accept(File dir, String name) {
			return name.endsWith(ext);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the name.
	 * @param map the map
	 * @param eProject the e project
	 * @return the name
	 */
	public static String getName(Map map, EProject eProject) {
		String result = "";

		Map<String, Object> attributes = (Map<String, Object>) map.get(EProject.ATTRIBUTES.name());

		if (attributes != null) {
			result += (String) attributes.get(EProject.NAME.name());

		}
		if (eProject != null) {
			String type = (String) map.get(eProject.name());
			if (type == null) {
				type = (String) attributes.get(eProject.name());
				result += " (" + type + ")";
			}
		}
		return result;

	}

	// ---------------------------------------------------------------

	/**
	 * Gets the extension.
	 * @param fileName the file name
	 * @return the extension
	 */
	public static String getExtension(String fileName) {
		String ext = "";
		try {
			ext = fileName.substring(fileName.lastIndexOf('.') + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ext;
	}

	// ---------------------------------------------------------------

	/**
	 * Checks if is ip address.
	 * @param ipAddress the ip address
	 * @return true, if is ip address
	 */
	public static boolean isIpAddress(String ipAddress) {
		Matcher m1 = VALID_IPV4_PATTERN.matcher(ipAddress);
		if (m1.matches()) {
			return true;
		}
		Matcher m2 = VALID_IPV6_PATTERN.matcher(ipAddress);
		return m2.matches();
	}

}
