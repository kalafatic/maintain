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

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.ide.IDE;

import eu.kalafatic.maintain.core.actions.PulldownAction;
import eu.kalafatic.maintain.core.lib.FCoreImageConstants;
import eu.kalafatic.maintain.view.views.pages.GraphPage;
import eu.kalafatic.maintain.view.views.pages.OverviewPage;
import eu.kalafatic.maintain.view.views.pages.ProjectPage;
import eu.kalafatic.maintain.view.views.pages.XSDXMLValidatorPage;
import eu.kalafatic.utils.constants.EProject;
import eu.kalafatic.utils.model.FormEditorInput;

/**
 * The Class class MaintainEditor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MaintainEditor extends SharedHeaderFormEditor implements ISaveablePart, ISelectionListener, IResourceChangeListener {

	/** The Constant ID. */
	public static final String ID = "eu.kalafatic.maintain.view.editors.MaintainEditor";

	/** The editor. */
	private TextEditor editor;

	/** The editor input. */
	private FormEditorInput editorInput;

	/** The form. */
	private Form form;

	/** The pages. */
	public Map<Map, Object> pages = new LinkedHashMap<Map, Object>();

	/**
	 * Instantiates a new maintain editor.
	 */
	public MaintainEditor() {
		super();

		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.SharedHeaderFormEditor#createHeaderContents(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createHeaderContents(IManagedForm headerForm) {
		FormToolkit toolkit = headerForm.getToolkit();
		ScrolledForm scrolledForm = headerForm.getForm();
		form = scrolledForm.getForm();
		toolkit.decorateFormHeading(form);

		form.setText("Maintain Editor");
		form.setImage(FCoreImageConstants.SETTINGS_IMG);

		form.getToolBarManager().add(new PulldownAction("Structure"));
		form.getToolBarManager().add(new PulldownAction("Actions"));
		form.getToolBarManager().update(true);

		// form.getMenuManager().add(new PulldownAction());

		form.addMessageHyperlinkListener(new HyperlinkAdapter());
		form.setMessage("This is an error message", IMessageProvider.ERROR);

		getSite().getPage().addSelectionListener(this);
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	@Override
	protected void addPages() {
		try {
			editorInput = (FormEditorInput) getEditorInput();
			Map project = editorInput.getProject();

			int index = 0;

			addPage(project, new OverviewPage(this, index++, project));

			List<Map> children = (List<Map>) project.get(EProject.CHILDREN.name());

			for (Map input : children) {
				addPage(input, new ProjectPage(this, index++, input));
			}

			addPage(new GraphPage(this, index++));

			createXMLEditorPage(index++);
			createXSDEditorPage(index++);

			addPage(new XSDXMLValidatorPage(this, index++));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Override
	// public FormEditorInput getEditorInput() {
	// if (this.editorInput == null) {
	// return (FormEditorInput) super.getEditorInput();
	// }
	// return editorInput;
	// }

	// ---------------------------------------------------------------

	/**
	 * Creates the xml editor page.
	 * @param index the index
	 */
	void createXMLEditorPage(int index) {
		try {
			editor = new Editor("xml");
			// File file = new File("c:/GE/maintain/project.xml");
			IFileStore fileStore = EFS.getStore(editorInput.getFile().toURI());
			FileStoreEditorInput fileStoreEditorInput = new FileStoreEditorInput(fileStore);

			addPage(editor, fileStoreEditorInput);
			setPageText(index, editor.getTitle());

		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(), "Error creating nested text editor", null, e.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the xsd editor page.
	 * @param index the index
	 */
	void createXSDEditorPage(int index) {
		try {
			XSDEditor editor = new XSDEditor();
			File file = new File("c:/GE/maintain/xsd/project.xsd");
			// File file = new File("c:/GE/maintain/project.xml");
			// IFileStore fileStore =
			// EFS.getStore(editorInput.getFile().toURI());
			// FileStoreEditorInput fileStoreEditorInput = new
			// FileStoreEditorInput(
			// fileStore);

			IFileStore fileStore = EFS.getLocalFileSystem().getStore(file.toURI());
			FileStoreEditorInput fileStoreEditorInput = new FileStoreEditorInput(fileStore);
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

			IDE.openEditorOnFileStore(page, fileStore);

			addPage(editor, fileStoreEditorInput);
			setPageText(index, editor.getTitle());

		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(), "Error creating nested text editor", null, e.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.SharedHeaderFormEditor#dispose()
	 */
	@Override
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		getEditor(0).doSave(monitor);
	}

	// ---------------------------------------------------------------
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		IEditorPart editor = getEditor(0);
		editor.doSaveAs();
		setPageText(0, editor.getTitle());
		setInput(editor.getEditorInput());
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.SharedHeaderFormEditor#setActivePage(int)
	 */
	@Override
	public void setActivePage(int pageIndex) {
		super.setActivePage(pageIndex);
	}

	// ---------------------------------------------------------------
	/*
	 * (non-Javadoc) Method declared on IEditorPart
	 */
	/**
	 * Goto marker.
	 * @param marker the marker
	 */
	public void gotoMarker(IMarker marker) {
		setActivePage(0);
		IDE.gotoMarker(getEditor(0), marker);
	}

	// ---------------------------------------------------------------
	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
		// if (!(editorInput instanceof IFileEditorInput)) {
		// throw new PartInitException(
		// "Invalid Input: Must be IFileEditorInput");
		// }

		super.init(site, editorInput);

		setPartName(editorInput.getName());
	}

	// ---------------------------------------------------------------
	/*
	 * (non-Javadoc) Method declared on IEditorPart.
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	// ---------------------------------------------------------------
	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#pageChange(int)
	 */
	@Override
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		if (newPageIndex == 2) {

		}
	}

	// ---------------------------------------------------------------
	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	@Override
	public void resourceChanged(final IResourceChangeEvent event) {
		if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
					for (int i = 0; i < pages.length; i++) {
						// if (((FileEditorInput) editor.getEditorInput()).getFile().getProject().equals(event.getResource())) {
						// IEditorPart editorPart = pages[i].findEditor(editor.getEditorInput());
						// pages[i].closeEditor(editorPart, true);
						// }
					}
				}
			});
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the form.
	 * @return the form
	 */
	public Form getForm() {
		return form;
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		System.err.println();

	}

	// ---------------------------------------------------------------

	/**
	 * Adds the page.
	 * @param input the input
	 * @param projectPage the project page
	 * @return the int
	 * @throws PartInitException the part init exception
	 */
	public int addPage(Map input, ProjectPage projectPage) throws PartInitException {
		pages.put(input, projectPage);
		return super.addPage(projectPage);
	}
}
