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
package eu.kalafatic.maintain.view.editors;

import org.eclipse.jface.viewers.Viewer;

/**
 * The Class class XSDEditor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class XSDEditor extends org.eclipse.xsd.presentation.XSDEditor {

	/** The Constant ID. */
	public static final String ID = "eu.kalafatic.maintain.view.editors.XSDEditor";

	/**
	 * Instantiates a new XSD editor.
	 */
	public XSDEditor() {
		super();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.xsd.presentation.XSDEditor#createPages()
	 */
	@Override
	public void createPages() {
		createSourcePage();
		createSemanticsPage();
		createSyntaxPage();

		setActivePage(0);
		setCurrentViewer((Viewer) sourceViewer);
	}

	// ---------------------------------------------------------------

	// @Override
	// public void contributeToMenu(IMenuManager menuManager) {
	// super.contributeToMenu(menuManager);
	//
	// // duplicate the menu contribution in the plugin.xml
	// IMenuManager submenuManager = new MenuManager(
	// XSDEditorPlugin.INSTANCE.getString("_UI_XSDEditor_menu"),
	// "org.eclipse.xsdMenuID");
	// menuManager.insertAfter("additions", submenuManager);
	// submenuManager.add(new Separator("settings"));
	// submenuManager.add(new Separator("actions"));
	// submenuManager.add(new Separator("additions"));
	// submenuManager.add(new Separator("additions-end"));
	//
	// // prepare for child and sibling creation item addition/removal
	// createChildMenuManager = new MenuManager(
	// XSDEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
	// createSiblingMenuManager = new MenuManager(
	// XSDEditorPlugin.INSTANCE
	// .getString("_UI_CreateSibling_menu_item"));
	// submenuManager.insertBefore("additions", new Separator("actions"));
	// submenuManager.insertBefore("additions", createChildMenuManager);
	// submenuManager.insertBefore("additions", createSiblingMenuManager);
	//
	// // Force an update because Eclipse hides empty menus now.
	// //
	// submenuManager.addMenuListener(new IMenuListener() {
	// public void menuAboutToShow(IMenuManager menuManager) {
	// menuManager.updateAll(true);
	// }
	// });
	//
	// validateAutomaticallyAction.setChecked(true);
	// submenuManager
	// .insertAfter("additions-end", validateAutomaticallyAction);
	// addGlobalActions(submenuManager);
	// }
}
