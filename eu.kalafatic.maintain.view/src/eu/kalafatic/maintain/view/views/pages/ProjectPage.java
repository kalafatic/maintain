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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import eu.kalafatic.maintain.core.utils.Utils;
import eu.kalafatic.maintain.view.editors.MaintainEditor;
import eu.kalafatic.utils.constants.EProject;
import eu.kalafatic.utils.factories.GUIFactory;
import eu.kalafatic.utils.factories.SectionFactory;

/**
 * The Class class ProjectPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProjectPage extends FormPage implements ISelectionListener {

	/** The Constant ID. */
	public static final String ID = "eu.kalafatic.maintain.core.editors.pages.ProjectPage";

	/** The editor. */
	private MaintainEditor editor;

	/** The input. */
	private Map input;

	/** The Constant TAB_INDEX. */
	public static final String TAB_INDEX = "TAB_INDEX";

	/** The Constant PARENT. */
	public static final String PARENT = "PARENT";

	/** The tab index. */
	private int tabIndex = 0;

	/** The text style. */
	int textStyle = SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY;

	/** The section style. */
	int sectionStyle = Section.TITLE_BAR | Section.TWISTIE | Section.COMPACT;

	/** The tab folder. */
	public CTabFolder tabFolder;

	/** The form. */
	private ScrolledForm form;

	/** The toolkit. */
	private FormToolkit toolkit;

	/**
	 * Instantiates a new project page.
	 * @param editor the editor
	 * @param index the index
	 * @param input the input
	 */
	public ProjectPage(MaintainEditor editor, int index, Map input) {
		super(editor, editor.ID, "");

		Map attributes = (Map) input.get(EProject.ATTRIBUTES.name());
		if (attributes.containsKey(EProject.NAME.name())) {
			setPartName((String) attributes.get(EProject.NAME.name()));
		} else {
			setPartName((String) input.get(EProject.KEY.name()));
		}
		this.editor = editor;
		this.input = input;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		form = managedForm.getForm();
		form.getBody().setLayout(new GridLayout(1, false));

		toolkit = GUIFactory.INSTANCE.getToolkit();

		createTabFolder();
		createTabs();

		tabFolder.setSelection(0);
		updateSelection();
		initListeners();
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the listeners.
	 */
	private void initListeners() {
		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateSelection();
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the tab folder.
	 */
	private void createTabFolder() {
		tabFolder = new CTabFolder(form.getBody(), SWT.FLAT | SWT.TOP);
		toolkit.adapt(tabFolder, true, true);

		tabFolder.setLayout(new GridLayout(1, false));
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		Color selectedColor = toolkit.getColors().getColor(IFormColors.SEPARATOR);
		tabFolder.setSelectionBackground(new Color[] { selectedColor, toolkit.getColors().getBackground() }, new int[] { 50 });
		// tabFolder.setCursor(FormsResources.getHandCursor());

		toolkit.paintBordersFor(tabFolder);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the tabs.
	 */
	private void createTabs() {

		createTab(EProject.PROJECT, createTab(EProject.PROJECT.literal, input, false));

		createTab(EProject.ATTRIBUTES, createTab(EProject.ATTRIBUTES.literal, (Map) input.get(EProject.ATTRIBUTES.name()), true));

		// one tabs level only
		if (!(this instanceof OverviewPage)) {
			List children = (List) input.get(EProject.CHILDREN.name());

			for (Object object : children) {
				Map supProject = (Map) object;
				createTab(EProject.CHILDREN, createTab(Utils.getName(supProject, null), supProject, true));
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the tab.
	 * @param title the title
	 * @param map the map
	 * @param closeable the closeable
	 * @return the c tab item
	 */
	private CTabItem createTab(String title, Map map, boolean closeable) {
		CTabItem item = new CTabItem(tabFolder, SWT.NULL);
		item.setText(title);
		item.setData(map);
		item.setShowClose(closeable);
		return item;
	}

	// ---------------------------------------------------------------

	// one level projects only
	/**
	 * Creates the tab.
	 * @param eProject the e project
	 * @param cTabItem the c tab item
	 * @return the c tab item
	 */
	private CTabItem createTab(EProject eProject, CTabItem cTabItem) {
//		SashForm parent = new SashForm(tabFolder, SWT.VERTICAL);
		 Composite parent = new Composite(tabFolder, SWT.NONE);
		parent.setLayout(new GridLayout());
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));

		cTabItem.setControl(parent);
		createSection(eProject, (Map) cTabItem.getData(), parent, true);

		cTabItem.setData(TAB_INDEX, tabIndex++);
		cTabItem.setData(PARENT, this);
		editor.pages.put((Map) cTabItem.getData(), cTabItem);

		return cTabItem;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the section.
	 * @param eProject the e project
	 * @param map the map
	 * @param parent the parent
	 * @param expanded the expanded
	 */
	private void createSection(EProject eProject, Map map, Composite parent, boolean expanded) {
		// ScrolledForm form = SectionFactory.INSTANCE.initGrid(parent, 1);

		// form.getBody().setLayout(new ColumnLayout());

		Composite client = null;
		boolean enabled = !(this instanceof OverviewPage);

		Iterator iterator = map.entrySet().iterator();

		switch (eProject) {
		case PROJECT:
			Section section = SectionFactory.INSTANCE.createSection(parent, EProject.PROJECT.literal, "", sectionStyle);
			client = SectionFactory.INSTANCE.createClient(GUIFactory.INSTANCE.getToolkit(), section, 1, 0);

			// client = SectionFactory.INSTANCE.createSectionClient(form, EProject.PROJECT.literal);

			while (iterator.hasNext()) {
				Entry entry = (Entry) iterator.next();
				if (filter(entry, EProject.PROJECT, EProject.ATTRIBUTES, EProject.CHILDREN)) {
					enabled &= !entry.getKey().equals(EProject.KEY.name());
					// addClient(client, map, entry, enabled ? EUI.SELECT.bits : 0);
				}
			}
			break;
		case ATTRIBUTES:
			section = SectionFactory.INSTANCE.createSection(parent, EProject.ATTRIBUTES.literal, "", sectionStyle);
			// client = SectionFactory.INSTANCE.createSectionClient(section, 1);
			client = SectionFactory.INSTANCE.createClient(GUIFactory.INSTANCE.getToolkit(), section, 1, 0);
			// client = SectionFactory.INSTANCE.createSectionClient(form, EProject.ATTRIBUTES.literal);

			// client = guiFactory.createUISection(parent,
			// EProject.ATTRIBUTES.literal, expanded);
			Map attributes = (Map) map.get(EProject.ATTRIBUTES.name());

			while (iterator.hasNext()) {
				Entry entry = (Entry) iterator.next();
				// addClient(client, attributes, entry, enabled ? EUI.SELECT.bits : 0);
			}
			break;

		case CHILDREN:
			section = SectionFactory.INSTANCE.createSection(parent, Utils.getName(map, EProject.TYPE), "", sectionStyle);
			// client = SectionFactory.INSTANCE.createSectionClient(section, 1);
			client = SectionFactory.INSTANCE.createClient(GUIFactory.INSTANCE.getToolkit(), section, 1, 0);
			// client = SectionFactory.INSTANCE.createSectionClient(form, EProject.TYPE.literal);

			// client = guiFactory
			// .createUISection(parent, Utils.getName(map, EProject.TYPE),
			// expanded);

			while (iterator.hasNext()) {
				Entry entry = (Entry) iterator.next();
				addClient(client, map, entry, 0);
			}
			break;

		default:
			break;
		}
		// client.layout(true, true);
		// form.reflow(true);
	}

	// ---------------------------------------------------------------

	/**
	 * Filter.
	 * @param entry the entry
	 * @param eProjects the e projects
	 * @return true, if successful
	 */
	private boolean filter(Entry entry, EProject... eProjects) {
		for (EProject eProject : eProjects) {
			if (entry.getKey().equals(eProject.name())) {
				return false;
			}
		}
		return true;
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the client.
	 * @param client the client
	 * @param map the map
	 * @param entry the entry
	 * @param ui the ui
	 */
	public void addClient(Composite client, Map map, Entry entry, int ui) {
		// if (entry.getValue() instanceof String) {
		// guiFactory.createUIElement(client, map, entry, ui);
		//
		// } else if (entry.getValue() instanceof List) {
		// guiFactory.createUIElement(client, (List) entry.getValue());
		// }
	}

	// ---------------------------------------------------------------

	/**
	 * Update selection.
	 */
	protected void updateSelection() {
		// CTabItem item = tabFolder.getSelection();
		// TextSection section = (TextSection) item.getData();
		// text.setText(section.text);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		System.err.println();
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the input.
	 * @return the input
	 */
	public Map getInput() {
		return input;
	}

	// ---------------------------------------------------------------

	/**
	 * Select tab.
	 * @param index the index
	 */
	public void selectTab(int index) {
		tabFolder.setSelection(index);
	}
}
