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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import eu.kalafatic.maintain.core.utils.Utils;

/**
 * The Class class OpenXSDEditorHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class OpenXSDEditorHandler extends AbstractHandler {

	/** The filter path. */
	public static String FILTER_PATH = "c:/GE/maintain/xsd";
	
	/** The filter ext. */
	public static String[] FILTER_EXT = new String[] { "*.xsd" };

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			String path = Utils.openFile(true, FILTER_PATH, FILTER_EXT);
			if (path != null) {
				File file = new File(path);
				FILTER_PATH = file.getParentFile().getAbsolutePath();

				IFileStore fileStore = EFS.getLocalFileSystem().getStore(
						file.toURI());
				// FileStoreEditorInput fileStoreEditorInput = new
				// FileStoreEditorInput(
				// fileStore);
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();

				IDE.openEditorOnFileStore(page, fileStore);
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		return null;
	}

}
