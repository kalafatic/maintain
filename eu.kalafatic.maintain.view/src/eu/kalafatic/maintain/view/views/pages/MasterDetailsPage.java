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
package eu.kalafatic.maintain.view.views.pages;

import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import eu.kalafatic.utils.ui.Messages;
import eu.kalafatic.utils.ui.ScrolledPropertiesBlock;

/**
 * The Class class MasterDetailsPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class MasterDetailsPage extends FormPage {
	
	/** The block. */
	private ScrolledPropertiesBlock block;

	/**
	 * Instantiates a new master details page.
	 * @param editor the editor
	 */
	public MasterDetailsPage(FormEditor editor) {
		super(editor, "fourth", Messages.getString("MasterDetailsPage.label")); //$NON-NLS-1$ //$NON-NLS-2$
		block = new ScrolledPropertiesBlock(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createFormContent(final IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		// FormToolkit toolkit = managedForm.getToolkit();
		form.setText(Messages.getString("MasterDetailsPage.title")); //$NON-NLS-1$
		// form.setBackgroundImage(FormArticlePlugin.getDefault().getImage(
		// FormArticlePlugin.IMG_FORM_BG));
		block.createContent(managedForm);
	}
}