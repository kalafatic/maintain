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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

import eu.kalafatic.maintain.view.views.pages.XSDXMLValidatorPage;
import eu.kalafatic.utils.ui.FreeFormPage;
import eu.kalafatic.utils.ui.ThirdPage;

/**
 * The Class class SimpleFormEditor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SimpleFormEditor extends FormEditor {
	
	/**
	 * Instantiates a new simple form editor.
	 */
	public SimpleFormEditor() {}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#createToolkit(org.eclipse.swt. widgets.Display)
	 */
	@Override
	protected FormToolkit createToolkit(Display display) {
		// Create a toolkit that shares colors between editors.
		return new FormToolkit(display);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	@Override
	protected void addPages() {
		try {
			addPage(new FreeFormPage(this));
			addPage(new XSDXMLValidatorPage(this));
			int index = addPage(new Composite(getContainer(), SWT.NULL));
			setPageText(index, "Composite"); //$NON-NLS-1$
			addPage(new ThirdPage(this));
			addPage(new eu.kalafatic.maintain.view.views.pages.MasterDetailsPage(this));
			// addPage(new OverviewPage(this));
		} catch (PartInitException e) {
			//
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor )
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.ISaveablePart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
}