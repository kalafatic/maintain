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
package eu.kalafatic.maintain.controller.handlers;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import eu.kalafatic.maintain.core.lib.FMaintainConstants;

/**
 * The Class class EditHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class EditHandler extends AbstractHandler {

	/** The action. */
	private final String action = "action";
	
	/** The type. */
	private final String type = "type";

	/** The input. */
	private File input;
	
	/** The last open path. */
	private String lastOpenPath;

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String actionParameter = (String) event.getParameters().get(action);
		String typeParameter = (String) event.getParameters().get(type);

		// if (actionParameter.equals("new")) {
		// newConfiguration(typeParameter);
		// } else if (actionParameter.equals("open")) {
		// openConfiguration(typeParameter);
		// } else if (actionParameter.equals("edit")) {
		// editConfiguration(typeParameter);
		// }
		input = new File("c:/a.jnlp");

		if (input != null) {
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

			try {
				IDE.openEditor(window.getActivePage(), input.toURI(), FMaintainConstants.MAINTAIN_EDITOR_ID, true);
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// AppUtils.openEditor(MaintainEditor.ID, input);
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * New configuration.
	 * @param typeParameter the type parameter
	 */
	private void newConfiguration(String typeParameter) {

		try {
			FileDialog fileDialog = setFileDialog();

			if (typeParameter.equals("jnlp")) {
				fileDialog.setFilterExtensions(new String[] { "*.jnlp", "*.*" });
				String open = fileDialog.open();
				if (!open.endsWith(".jnlp")) {
					open += ".jnlp";
				}
				input = new File(open);

			} else {

			}

			input.createNewFile();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ---------------------------------------------------------------

	/**
	 * Open configuration.
	 * @param typeParameter the type parameter
	 */
	private void openConfiguration(String typeParameter) {
		// TODO Auto-generated method stub

	}

	// ---------------------------------------------------------------

	/**
	 * Edits the configuration.
	 * @param typeParameter the type parameter
	 */
	private void editConfiguration(String typeParameter) {
		// TODO Auto-generated method stub

	}

	// ---------------------------------------------------------------

	/**
	 * Sets the file dialog.
	 * @return the file dialog
	 */
	private FileDialog setFileDialog() {
		// String maintain = PREFERENCES.get(
		// ECorePreferences.MAINTAIN_LOC.getName(),
		// (String) ECorePreferences.MAINTAIN_LOC.getDef());
		//
		FileDialog fileDialog = new FileDialog(new Shell(Display.getDefault()));
		// fileDialog.setText("Create File");
		//
		// if (lastOpenPath == null) {
		// lastOpenPath = maintain;
		// }
		// fileDialog.setFilterPath(lastOpenPath);
		return fileDialog;
	}

}
