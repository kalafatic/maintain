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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import eu.kalafatic.maintain.core.utils.Utils;
import eu.kalafatic.maintain.core.xsd.XSDXMLValidator;
import eu.kalafatic.maintain.view.editors.MaintainEditor;

/**
 * The Class class XSDXMLValidatorPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class XSDXMLValidatorPage extends FormPage {

	/** The Constant ID. */
	public static final String ID = "eu.kalafatic.maintain.core.editors.pages.XSDXMLValidatorPage";

	/** The grid layout. */
	private GridLayout gridLayout;
	
	/** The grid data. */
	private GridData gridData;
	
	/** The toolkit. */
	private FormToolkit toolkit;

	/** The filter path. */
	private String filterPath = "c:/GE/maintain/";

	/** The xml. */
	private String xml;
	
	/** The xsds. */
	private List<String> xsds = new ArrayList<String>();
	
	/** The result text. */
	private Text resultText;

	/**
	 * Instantiates a new XSDXML validator page.
	 * @param editor the editor
	 */
	public XSDXMLValidatorPage(FormEditor editor) {
		super(editor, "second", "Validator");
	}

	// ---------------------------------------------------------------

	/**
	 * Instantiates a new XSDXML validator page.
	 * @param editor the editor
	 * @param i the i
	 */
	public XSDXMLValidatorPage(MaintainEditor editor, int i) {
		super(editor, "second", "Validator");
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = managedForm.getForm();
		toolkit = managedForm.getToolkit();
		form.setText("");
		// form.setBackgroundImage(FormArticlePlugin.getDefault().getImage(FormArticlePlugin.IMG_FORM_BG));
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		form.getBody().setLayout(layout);

		createTableSection(form, 0);
		createTableSection(form, 1);

		createResultSection(form);

		createActionBar(managedForm);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the action bar.
	 * @param managedForm the managed form
	 */
	private void createActionBar(final IManagedForm managedForm) {
		Action validate = new Action("Validate") {
			@Override
			public void run() {
				XSDXMLValidator xsdxmlValidator = getXSDXMLValidator();
				BusyIndicator.showWhile(Display.getDefault(), xsdxmlValidator);

				String result = xsdxmlValidator.getResult();
				if (result.equals("Valid")) {
					((MaintainEditor) getEditor()).getForm().setMessage(
							"Valid comparision", IMessageProvider.INFORMATION);
				} else {
					((MaintainEditor) getEditor()).getForm().setMessage(
							"Invalid comparision", IMessageProvider.WARNING);
				}
				resultText.setText(result);
			}
		};
		managedForm.getForm().getToolBarManager().add(validate);
		managedForm.getForm().getToolBarManager().update(true);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the XSDXML validator.
	 * @return the XSDXML validator
	 */
	private XSDXMLValidator getXSDXMLValidator() {
		File[] xsdsFiles = new File[xsds.size()];

		for (int i = 0; i < xsdsFiles.length; i++) {
			xsdsFiles[i] = new File(xsds.get(i));
		}
		return new XSDXMLValidator(new File(xml), xsdsFiles);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the table section.
	 * @param form the form
	 * @param type the type
	 */
	private void createTableSection(final ScrolledForm form, int type) {
		Section section = toolkit.createSection(form.getBody(),
				Section.DESCRIPTION | Section.COMPACT);
		section.setActiveToggleColor(toolkit.getHyperlinkGroup()
				.getActiveForeground());
		section.setToggleColor(toolkit.getColors().getColor(
				IFormColors.SEPARATOR));
		toolkit.createCompositeSeparator(section);
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		gridLayout = new GridLayout();
		gridLayout.numColumns = 2;

		client.setLayout(gridLayout);

		Table table = createTable(client, type);
		createButtons(client, table, type);

		section.setText(type == 0 ? "XML" : "XSD");
		section.setDescription(type == 0 ? "XML source" : "XSD sources");
		section.setClient(client);
		section.setExpanded(true);

		section.addExpansionListener(new ExpansionAdapter() {
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(false);
			}
		});
		gridData = new GridData(GridData.FILL_BOTH);
		section.setLayoutData(gridData);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the result section.
	 * @param form the form
	 */
	private void createResultSection(final ScrolledForm form) {
		Section section = toolkit.createSection(form.getBody(),
				Section.DESCRIPTION | Section.COMPACT);
		section.setActiveToggleColor(toolkit.getHyperlinkGroup()
				.getActiveForeground());
		section.setToggleColor(toolkit.getColors().getColor(
				IFormColors.SEPARATOR));

		toolkit.createCompositeSeparator(section);
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		gridLayout = new GridLayout();
		gridLayout.numColumns = 1;

		client.setLayout(gridLayout);

		createText(client);

		section.setClient(client);
		section.setExpanded(true);

		section.addExpansionListener(new ExpansionAdapter() {
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(false);
			}
		});
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		section.setLayoutData(gridData);

	}

	// ---------------------------------------------------------------

	/**
	 * Creates the buttons.
	 * @param client the client
	 * @param table the table
	 * @param type the type
	 */
	private void createButtons(Composite client, final Table table,
			final int type) {
		if (type == 0) {
			Button changeBtn = toolkit.createButton(client, "Set", SWT.PUSH);
			gridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
			gridData.widthHint = 50;
			changeBtn.setLayoutData(gridData);

			changeBtn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String openFile = Utils.openFile(true, filterPath);
					if (openFile != null) {
						table.getItem(0).setText(openFile);
						xml = openFile;
					}
				}
			});
		} else {
			Button addBtn = toolkit.createButton(client, "Add", SWT.PUSH);
			gridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
			gridData.widthHint = 50;
			addBtn.setLayoutData(gridData);

			addBtn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String openFile = Utils.openFile(true, filterPath);
					if (openFile != null) {
						TableItem tableItem = new TableItem(table, SWT.NO);
						tableItem.setText(openFile);
						xsds.add(openFile);
					}
				}
			});

			Button deleteBtn = toolkit.createButton(client, "Remove", SWT.PUSH);
			gridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
			gridData.widthHint = 50;
			deleteBtn.setLayoutData(gridData);

			deleteBtn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					TableItem[] items = table.getItems();
					for (int i = 0; i < items.length; i++) {
						if (items[i].getChecked()) {
							xsds.remove(items[i].getText());
							items[i].dispose();
						}
					}
					table.update();
				}
			});
		}

	}

	// ---------------------------------------------------------------

	/**
	 * Creates the table.
	 * @param client the client
	 * @param type the type
	 * @return the table
	 */
	private Table createTable(Composite client, int type) {
		Table table = toolkit.createTable(client, type == 0 ? SWT.MULTI
				: SWT.CHECK);
		gridData = new GridData(GridData.FILL_BOTH);
		// gridData.heightHint = 100;
		gridData.widthHint = 100;
		gridData.verticalSpan = 3;
		table.setLayoutData(gridData);
		toolkit.paintBordersFor(client);
		table.setLinesVisible(true);

		if (type == 0) {
			new TableItem(table, SWT.NO);
		}

		return table;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the text.
	 * @param client the client
	 */
	private void createText(Composite client) {
		resultText = toolkit.createText(client, "", SWT.MULTI | SWT.WRAP);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = 50;
		// gridData.widthHint = 100;
		// gridData.;
		resultText.setLayoutData(gridData);
		toolkit.paintBordersFor(client);
	}

}
