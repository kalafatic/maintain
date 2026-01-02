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
package eu.kalafatic.maintain.core.lib;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import eu.kalafatic.maintain.core.Activator;

/**
 * The Class class FMaintainConstants.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public final class FMaintainConstants {

	/** The Constant MAINTAIN_EDITOR_ID. */
	public static final String MAINTAIN_EDITOR_ID = "eu.kalafatic.maintain.view.editors.MaintainEditor";

	/** The Constant XSD_EDITOR_ID. */
	public static final String XSD_EDITOR_ID = "eu.kalafatic.maintain.view.editors.XSDEditor";

	/** The shared images. */
	private static ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	// FILE STRUCTURE
	/** The Constant FOLDER_IMG. */
	public static final Image FOLDER_IMG = sharedImages.getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER).createImage();

	/** The Constant FILE_IMG. */
	public static final Image FILE_IMG = sharedImages.getImageDescriptor(ISharedImages.IMG_OBJ_FILE).createImage();

	/** The Constant DRIVE_IMG_1. */
	public static final Image DRIVE_IMG_1 = Activator.getImageDescriptor("icons/drive1.png").createImage();

	/** The Constant DRIVE_IMG_2. */
	public static final Image DRIVE_IMG_2 = Activator.getImageDescriptor("icons/drive2.png").createImage();

	/** The Constant MAINTAIN_IMG. */
	public static final Image MAINTAIN_IMG = Activator.getImageDescriptor("icons/maintain.png").createImage();

	/** The Constant PROJECT_IMG. */
	public static final Image PROJECT_IMG = Activator.getImageDescriptor("icons/project/project.gif").createImage();

	/** The Constant SETTINGS_IMG. */
	public static final Image SETTINGS_IMG = Activator.getImageDescriptor("icons/settings.png").createImage();

	/** The Constant TREE_IMG. */
	public static final Image TREE_IMG = Activator.getImageDescriptor("icons/tree/tree.png").createImage();

	/** The Constant LIST_IMG. */
	public static final Image LIST_IMG = Activator.getImageDescriptor("icons/tree/list.gif").createImage();

	/** The Constant ELEMENTS_IMG. */
	public static final Image ELEMENTS_IMG = Activator.getImageDescriptor("icons/tree/elements.gif").createImage();

	/** The Constant FRAME_IMG. */
	public static final Image FRAME_IMG = Activator.getImageDescriptor("icons/tree/frame.png").createImage();

	// public static final Image SERVER_IMG = Activator.getImageDescriptor(
	// "icons/server.png").createImage();

	/** The Constant COLLAPSE_ALL_DESC. */
	public static final ImageDescriptor COLLAPSE_ALL_DESC = Activator.getImageDescriptor("icons/tree/collapse_all.gif");

	/** The Constant EXPAND_ALL_DESC. */
	public static final ImageDescriptor EXPAND_ALL_DESC = Activator.getImageDescriptor("icons/tree/expand_all.gif");

	/** The Constant EXPAND_ALL. */
	public static final String EXPAND_ALL = "Expand All";

	/** The Constant COLLAPSE_ALL. */
	public static final String COLLAPSE_ALL = "Collapse All";

	/** The Constant FULL_BORDER_SCROLL_BOTH. */
	public final static int FULL_BORDER_SCROLL_BOTH = SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL;

	/** The Constant TREE_STYLE_0. */
	public final static int TREE_STYLE_0 = SWT.SHADOW_ETCHED_OUT | FULL_BORDER_SCROLL_BOTH;

	/** The Constant TREE_STYLE_1. */
	public final static int TREE_STYLE_1 = SWT.CHECK | TREE_STYLE_0;

	/** The Constant SAND_COLOR. */
	public final static Color SAND_COLOR = new Color(Display.getDefault(), 255, 200, 50);

	/** The Constant GRASS_COLOR. */
	public final static Color GRASS_COLOR = new Color(Display.getDefault(), 230, 255, 220);

	/** The Constant WHITE_COLOR. */
	public final static Color WHITE_COLOR = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);

	/** The Constant ORANGE_COLOR. */
	public final static Color ORANGE_COLOR = new Color(Display.getDefault(), 255, 100, 50);

	/** The Constant MAINTAIN_XML_PATH. */
	public final static String MAINTAIN_XML_PATH = "c:/GE/maintain/project.xml";

	/** The Constant FILTER_PATH. */
	public final static String FILTER_PATH = "FILTER_PATH";

	/** The Constant FILTER_EXT. */
	public final static String FILTER_EXT = "FILTER_EXT";

}
