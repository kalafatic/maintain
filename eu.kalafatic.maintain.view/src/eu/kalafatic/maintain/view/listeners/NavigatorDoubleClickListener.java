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
package eu.kalafatic.maintain.view.listeners;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import eu.kalafatic.maintain.view.editors.MaintainEditor;
import eu.kalafatic.maintain.view.views.pages.ProjectPage;
import eu.kalafatic.utils.constants.EProject;

/**
 * The listener interface for receiving navigatorDoubleClick events. The class that is interested in processing a navigatorDoubleClick event
 * implements this interface, and the object created with that class is registered with a component using the component's
 * <code>addNavigatorDoubleClickListener<code> method. When
 * the navigatorDoubleClick event occurs, that object's appropriate
 * method is invoked.
 * @see NavigatorDoubleClickEvent
 */
@SuppressWarnings({ "unused", "rawtypes" })
public class NavigatorDoubleClickListener implements IDoubleClickListener {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
	 */
	@Override
	public void doubleClick(DoubleClickEvent event) {
		try {
			if (event.getSelection() instanceof TreeSelection) {
				IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

				if (activeEditor instanceof MaintainEditor) {
					TreeSelection selection = (TreeSelection) event.getSelection();
					Object object = selection.getFirstElement();

					MaintainEditor maintainEditor = (MaintainEditor) activeEditor;
					selectEditorPage(maintainEditor, (TreeViewer) event.getSource(), object);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Select editor page.
	 * @param maintainEditor the maintain editor
	 * @param viewer the viewer
	 * @param object the object
	 */
	private void selectEditorPage(MaintainEditor maintainEditor, TreeViewer viewer, Object object) {

		if (object instanceof Entry) {
			Entry entry = (Entry) object;
			if (entry.getKey().equals(EProject.ATTRIBUTES.name())) {
				selectEditorPage(maintainEditor, viewer, entry.getValue());
			} else if (entry.getKey().equals(EProject.CHILDREN.name())) {
				// expandItem(viewer);
			}
		} else if (object instanceof Map) {
			Map map = (Map) object;

			if (maintainEditor.pages.containsKey(map)) {
				Object o = maintainEditor.pages.get(map);
				if (o instanceof ProjectPage) {
					ProjectPage projectPage = (ProjectPage) o;
					maintainEditor.setActivePage(projectPage.getIndex());
					projectPage.tabFolder.setSelection(0);

				} else if (o instanceof CTabItem) {
					CTabItem cTabItem = (CTabItem) o;

					if (cTabItem.getData(ProjectPage.PARENT) != null) {
						ProjectPage projectPage = (ProjectPage) cTabItem.getData(ProjectPage.PARENT);
						maintainEditor.setActivePage(projectPage.getIndex());
						// projectPage.selectTab((int) cTabItem
						// .getData(ProjectPage.TAB_INDEX));
					}

				}
			}
		} else if (object instanceof List) {
			List list = (List) object;
		}
		expandItem(viewer);
	}

	// ---------------------------------------------------------------

	/**
	 * Expand item.
	 * @param viewer the viewer
	 */
	private void expandItem(TreeViewer viewer) {
		viewer.getTree().getSelection()[0].setExpanded(true);
		viewer.refresh();
	}
}
