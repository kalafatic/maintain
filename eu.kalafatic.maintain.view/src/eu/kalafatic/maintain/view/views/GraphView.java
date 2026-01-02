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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.AbstractZoomableViewer;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IZoomableWorkbenchPart;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;

import eu.kalafatic.maintain.core.providers.ZestLabelProvider;
import eu.kalafatic.maintain.core.providers.ZestNodeContentProvider;
import eu.kalafatic.utils.hack.ZoomManager;
import eu.kalafatic.utils.interfaces.IViewer;

/**
 * The Class class GraphView.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class GraphView extends ViewPart implements IPartListener2, IViewer, IZoomableWorkbenchPart {

	/** The id. */
	public static String ID = "eu.kalafatic.maintain.core.views.GraphView";

	/** The viewer. */
	private GraphViewer viewer;

	/** The graph. */
	private Graph graph;

	/** The zoom manager. */
	private ZoomManager zoomManager;

	/** The Constant ROOT_NODE. */
	public static final String ROOT_NODE = "Swarms";

	/** The input map. */
	private Map<String, Map<?, ?>> inputMap;

	/** The select all action. */
	private Action addItemAction, deleteItemAction, selectAllAction;

	/**
	 * Instantiates a new graph view.
	 */
	public GraphView() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.VERTICAL));

		viewer = new GraphViewer(parent, SWT.NONE);
		viewer.setContentProvider(new ZestNodeContentProvider(viewer));
		viewer.setLabelProvider(new ZestLabelProvider(viewer)); // GraphItemStyler

		graph = viewer.getGraphControl();

		zoomManager = new ZoomManager(graph.getRootLayer(), graph.getViewport());

		inputMap = new HashMap<String, Map<?, ?>>();
		inputMap.put(ROOT_NODE, new HashMap<String, Map<?, ?>>());

		viewer.setInput(inputMap);

		createActions();
		createMenu();
		hookContextMenu();
		createToolbar();

		initContributionItems();

		getSite().setSelectionProvider(viewer);
		getSite().getWorkbenchWindow().getPartService().addPartListener(this);

	}

	// ---------------------------------------------------------------

	/**
	 * Inits the contribution items.
	 */
	private void initContributionItems() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				// filterItem.getCombo().select(0);
				// layoutItem.getCombo().select(0);
				// zoomItem.getCombo().select(0);
				viewer.setLayoutAlgorithm(new RadialLayoutAlgorithm(LayoutStyles.NONE));

				initListeners();
				initAdapters(null);
			}
		});
	}

	// ---------------------------------------------------------------

	// ---------------------------------------------------------------

	/**
	 * Creates the toolbar.
	 */
	private void createToolbar() {
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();

		IActionBars bars = getViewSite().getActionBars();

		// subtreeFilter = new SubtreeContributionItem(viewer, inputMap);
		// filterItem = new FilterContributionItem(viewer, inputMap,
		// subtreeFilter);
		//
		// layoutItem = new LayoutContributionItem(viewer.getGraphControl());
		// zoomItem = new ZoomContributionViewItem(this, zoomManager);
		//
		// bars.getMenuManager().add(filterItem);
		// bars.getMenuManager().add(subtreeFilter);
		// bars.getMenuManager().add(layoutItem);
		// bars.getMenuManager().add(zoomItem);
		//
		// toolBarManager.add(filterItem);
		// toolBarManager.add(subtreeFilter);
		// toolBarManager.add(layoutItem);
		// toolBarManager.add(zoomItem);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#hookContextMenu()
	 */
	@Override
	public void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the menu.
	 */
	@SuppressWarnings("unused")
	private void createMenu() {
		IMenuManager mgr = getViewSite().getActionBars().getMenuManager();
		// mgr.add(selectAllAction);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the actions.
	 */
	public void createActions() {
		addItemAction = new Action("Add...") {
			@Override
			public void run() {
				System.err.println(" addItem();");

			}
		};
		// addItemAction.setImageDescriptor(getImageDescriptor("add.gif"));

		deleteItemAction = new Action("Delete") {
			@Override
			public void run() {
				System.err.println(" deleteItem();");

			}
		};
		// deleteItemAction.setImageDescriptor(getImageDescriptor("delete.gif"));

		selectAllAction = new Action("Select All") {
			@Override
			public void run() {
				System.err.println("  selectAll();");

			}
		};

		// Add selection listener.
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// updateActionEnablement();
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.zest.core.viewers.IZoomableWorkbenchPart#getZoomableViewer()
	 */
	@Override
	public AbstractZoomableViewer getZoomableViewer() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see eu.kalafatic.utils.interfaces.IViewer#initColumns()
	 */
	@Override
	public void initColumns() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see eu.kalafatic.utils.interfaces.IViewer#initListeners()
	 */
	@Override
	public void initListeners() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see eu.kalafatic.utils.interfaces.IViewer#initAdapters(java.util.Collection)
	 */
	@Override
	public void initAdapters(Collection<?> collection) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see eu.kalafatic.utils.interfaces.IViewer#getViewer()
	 */
	@Override
	public Object getViewer() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partActivated(org.eclipse.ui.IWorkbenchPartReference)
	 */
	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partBroughtToTop(org.eclipse.ui.IWorkbenchPartReference)
	 */
	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partClosed(org.eclipse.ui.IWorkbenchPartReference)
	 */
	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partDeactivated(org.eclipse.ui.IWorkbenchPartReference)
	 */
	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partOpened(org.eclipse.ui.IWorkbenchPartReference)
	 */
	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partHidden(org.eclipse.ui.IWorkbenchPartReference)
	 */
	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partVisible(org.eclipse.ui.IWorkbenchPartReference)
	 */
	@Override
	public void partVisible(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partInputChanged(org.eclipse.ui.IWorkbenchPartReference)
	 */
	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveListener#perspectiveActivated(org.eclipse.ui.IWorkbenchPage, org.eclipse.ui.IPerspectiveDescriptor)
	 */
	@Override
	public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveListener#perspectiveChanged(org.eclipse.ui.IWorkbenchPage, org.eclipse.ui.IPerspectiveDescriptor, java.lang.String)
	 */
	@Override
	public void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor perspective, String changeId) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see eu.kalafatic.utils.interfaces.IViewer#initDragAndDrop()
	 */
	@Override
	public void initDragAndDrop() {
		// TODO Auto-generated method stub

	}

}
