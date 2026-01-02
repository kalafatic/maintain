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

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.xsd.presentation.XSDEditor;

/**
 * The Class class XSDEditorContributor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class XSDEditorContributor extends EditingDomainActionBarContributor
		implements ISelectionChangedListener {

	/** The active editor part. */
	protected IEditorPart activeEditorPart;

	/** The selection provider. */
	protected ISelectionProvider selectionProvider;

	/**
	 * Instantiates a new XSD editor contributor.
	 */
	public XSDEditorContributor() {
		super();
	}

	/** The refresh viewer action. */
	protected IAction refreshViewerAction = new Action(
			"_UI_RefreshViewer_menu_item") {
		@Override
		public boolean isEnabled() {
			return activeEditorPart instanceof IViewerProvider;
		}

		@Override
		public void run() {
			if (activeEditorPart instanceof IViewerProvider) {
				Viewer viewer = ((IViewerProvider) activeEditorPart)
						.getViewer();
				if (viewer != null) {
					viewer.refresh();
				}
			}
		}
	};

	/** The show properties view action. */
	protected IAction showPropertiesViewAction = new Action(
			"_UI_ShowPropertiesView_menu_item") {
		@Override
		public void run() {
			try {
				getPage().showView("org.eclipse.ui.views.PropertySheet");
			} catch (PartInitException exception) {

			}
		}
	};

	/** The validate automatically action. */
	protected IAction validateAutomaticallyAction = new Action(
			"_UI_ValidateAutomatically_menu_item") {
		@Override
		public void run() {
			((XSDEditor) activeEditorPart)
					.setValidateAutomatically(isChecked());
		}
	};

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// remove any menu items for old selection
		// if (createChildMenuManager != null)
		// {
		// depopulateManager(createChildMenuManager, createChildActions);
		// }
		// if (createSiblingMenuManager != null)
		// {
		// depopulateManager(createSiblingMenuManager, createSiblingActions);
		// }
		//
		// // query new selection for appropriate new child/sibling
		// descriptors...
		// Collection<?> newChildDescriptors = Collections.emptyList();
		// Collection<?> newSiblingDescriptors = Collections.emptyList();
		// ISelection sel = event.getSelection();
		//
		// if (sel instanceof IStructuredSelection
		// && ((IStructuredSelection) sel).size() == 1)
		// {
		// Object object = ((IStructuredSelection) sel).getFirstElement();
		// EditingDomain domain =
		// ((IEditingDomainProvider) activeEditorPart).getEditingDomain();
		//
		// newChildDescriptors = domain.getNewChildDescriptors(object, null);
		// newSiblingDescriptors =
		// domain.getNewChildDescriptors(domain.getParent(object), object);
		// }
		//
		// // generate actions for selection, populate and redraw menu
		// createChildActions = generateCreateChildActions(newChildDescriptors,
		// sel);
		// createSiblingActions =
		// generateCreateSiblingActions(newSiblingDescriptors, sel);
		//
		// if (createChildMenuManager != null)
		// {
		// populateManager(createChildMenuManager, createChildActions, null);
		// createChildMenuManager.update(true);
		// }
		// if (createSiblingMenuManager != null)
		// {
		// populateManager(createSiblingMenuManager, createSiblingActions,
		// null);
		// createSiblingMenuManager.update(true);
		// }

	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor#contributeToMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void contributeToMenu(IMenuManager manager) {
		IMenuManager menu = new MenuManager("Editor &Menu");
//		manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, menu);
		// menu.add(sampleAction);
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor#contributeToToolBar(org.eclipse.jface.action.IToolBarManager)
	 */
	@Override
	public void contributeToToolBar(IToolBarManager manager) {
		// manager.add(new Separator());
		// manager.add(sampleAction);
	}

}
