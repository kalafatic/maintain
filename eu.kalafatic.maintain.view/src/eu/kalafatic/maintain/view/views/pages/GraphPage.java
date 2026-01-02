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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.Graph;

import eu.kalafatic.maintain.core.providers.ZestLabelProvider;
import eu.kalafatic.maintain.core.providers.ZestNodeContentProvider;
import eu.kalafatic.maintain.view.editors.MaintainEditor;
import eu.kalafatic.utils.hack.ZoomManager;

/**
 * The Class class GraphPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class GraphPage extends FormPage {

	/** The Constant ID. */
	public static final String ID = "eu.kalafatic.maintain.core.editors.pages.GraphPage";

	/**
	 * Instantiates a new graph page.
	 * @param editor the editor
	 * @param index the index
	 */
	public GraphPage(MaintainEditor editor, int index) {
		super(editor, ID, "Overview");

	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();

		form.setText("GraphPage");
		Composite composite = form.getBody();

		composite.setLayout(new FillLayout(SWT.VERTICAL));

		GraphViewer viewer = new GraphViewer(composite, SWT.NONE);
		viewer.setContentProvider(new ZestNodeContentProvider(viewer));
		viewer.setLabelProvider(new ZestLabelProvider(viewer));

		Graph graph = viewer.getGraphControl();

		ZoomManager zoomManager = new ZoomManager(graph.getRootLayer(), graph.getViewport());

		HashMap<String, Map<?, ?>> inputMap = new HashMap<String, Map<?, ?>>();
		inputMap.put("sdg", new HashMap<String, Map<?, ?>>());

		viewer.setInput(inputMap);
	}
}
