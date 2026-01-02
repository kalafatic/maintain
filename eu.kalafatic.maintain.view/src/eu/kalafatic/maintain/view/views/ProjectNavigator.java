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
package eu.kalafatic.maintain.view.views;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;

import eu.kalafatic.maintain.controller.model.MaintainModelManager;
import eu.kalafatic.maintain.core.lib.FMaintainConstants;
import eu.kalafatic.maintain.core.providers.ProjectContentProvider;
import eu.kalafatic.maintain.core.providers.ProjectLabelProvider;
import eu.kalafatic.maintain.view.listeners.NavigatorDoubleClickListener;
import eu.kalafatic.parsers.main.Parser;
import eu.kalafatic.utils.interfaces.ATreeViewer;

/**
 * The Class class ProjectNavigator.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProjectNavigator extends ATreeViewer {

	/** The id. */
	public static String ID = "eu.kalafatic.maintain.view.views.ProjectNavigator";

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.utils.interfaces.ATreeViewer#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		try {
			super.createPartControl(parent);

			((TreeViewer) viewer).setContentProvider(new ProjectContentProvider());
			((TreeViewer) viewer).setLabelProvider(new ProjectLabelProvider());

			// final DecoratingLabelProvider provider = new DecoratingLabelProvider(new JavaElementLabelProvider(labelFlags), new
			// ProblemsLabelDecorator(null));
			// final DecoratingLabelProvider provider = new DecoratingLabelProvider(new ProjectLabelProvider(),
			// PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator());

			// ((TreeViewer) viewer).setLabelProvider(provider);

			File file = new File(FMaintainConstants.MAINTAIN_XML_PATH);
			if (!file.exists()) {
				new FileOutputStream(file).write(("<!-- Maintain ® -->").getBytes());
			}
			Map<?, ?> project = Parser.getInstance(file).parse();
			//
			// IProject iProject = AppUtils.INSTANCE.createProject("Maintain");
			// iProject.getFile(file.getName()).setContents(new FileInputStream(file), true, true, null);

			List list = new ArrayList();
			list.add(project);
			viewer.setInput(list);

			// AppUtils.INSTANCE.openEditor(MaintainEditor.ID, file, project);
			((TreeViewer) viewer).expandToLevel(2);

			MaintainModelManager.getInstance();

			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.utils.interfaces.AViewer#initListeners()
	 */
	@Override
	public void initListeners() {
		((TreeViewer) viewer).addDoubleClickListener(new NavigatorDoubleClickListener());
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.utils.interfaces.ATreeViewer#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void fillContextMenu(IMenuManager manager) {
		// super.fillContextMenu(manager);
		if (viewer.getSelection() instanceof TreeSelection) {
			TreeSelection selection = (TreeSelection) viewer.getSelection();
			Object object = selection.getFirstElement();

		}
		manager.add(expandAll);
		manager.add(collapseAll);
		manager.add(new Separator("actions"));

		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));

		// manager.a

	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.utils.interfaces.AViewer#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		System.err.println();
	}
}
