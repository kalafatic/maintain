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
package eu.kalafatic.maintain.core.perspectives;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import eu.kalafatic.maintain.core.lib.FMaintainConstants;
import eu.kalafatic.parsers.main.Parser;
import eu.kalafatic.utils.application.AppUtils;

/**
 * The Class class MaintainCorePerspective.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class MaintainCorePerspective implements IPerspectiveFactory {

	/** The Constant ID. */
	public static final String ID = "eu.kalafatic.maintain.core.perspectives.MaintainCorePerspective";

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);

		IFolderLayout topLeft = layout.createFolder("TOP_LEFT", IPageLayout.LEFT, 0.25f, editorArea);

		topLeft.addView("eu.kalafatic.maintain.view.views.ProjectNavigator");

		IFolderLayout bottomLeft = layout.createFolder("BOTTOM_LEFT", IPageLayout.BOTTOM, 0.65f, "TOP_LEFT");

		bottomLeft.addView(IPageLayout.ID_PROP_SHEET);
		bottomLeft.addView(IPageLayout.ID_OUTLINE);

		IFolderLayout bottomRight = layout.createFolder("BOTTOM_RIGHT", IPageLayout.BOTTOM, 0.65f, editorArea);

		// bottomRight.addView(EView.CONSOLE.ID);
		bottomRight.addView(IConsoleConstants.ID_CONSOLE_VIEW);

		addActionSets(layout);
		addViewShortcuts(layout);
		addNewWizardShortcuts(layout);

		try {
			File file = new File(FMaintainConstants.MAINTAIN_XML_PATH);
			if (!file.exists()) {
				new FileOutputStream(file).write(("<!-- Maintain � -->").getBytes());
			}
			Map<?, ?> project = Parser.getInstance(file).parse();

			IProject iProject = AppUtils.INSTANCE.createProject("Maintain");
			IFile iFile = iProject.getFile(file.getName());
			if (!iFile.exists()) {
				iFile.create(new FileInputStream(file), true, null);
			}
			AppUtils.INSTANCE.openEditor("eu.kalafatic.maintain.view.editors.MaintainEditor", file, project);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ---------------------------------------------------------------

	/**
	 * Adds the action sets.
	 * @param layout the layout
	 */
	private void addActionSets(IPageLayout layout) {
		layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the view shortcuts.
	 * @param layout the layout
	 */
	private void addViewShortcuts(IPageLayout layout) {
		layout.addShowViewShortcut(IConsoleConstants.ID_CONSOLE_VIEW);
		layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
		layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(IPageLayout.ID_PROGRESS_VIEW);
		layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the new wizard shortcuts.
	 * @param layout the layout
	 */
	private void addNewWizardShortcuts(IPageLayout layout) {
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");
	}
}
