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
package eu.kalafatic.maintain.controller.listeners;

import java.io.File;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;

import eu.kalafatic.maintain.core.utils.Utils;

/**
 * The listener interface for receiving elementBtn events. The class that is interested in processing a elementBtn event implements this interface,
 * and the object created with that class is registered with a component using the component's <code>addElementBtnListener<code> method. When
 * the elementBtn event occurs, that object's appropriate
 * method is invoked.
 * @see ElementBtnEvent
 */
public class ElementBtnListener extends SelectionAdapter {

	/** The control. */
	private Control control;
	
	/** The ui. */
	private int ui;
	
	/** The bool. */
	private boolean[] bool;
	
	/** The last action. */
	private Object lastAction = null;

	/**
	 * Instantiates a new element btn listener.
	 * @param control the control
	 * @param ui the ui
	 * @param bool the bool
	 */
	public ElementBtnListener(Control control, int ui, boolean... bool) {
		this.control = control;
		this.ui = ui;
		this.bool = bool;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	@Override
	public void widgetSelected(SelectionEvent e) {
		if (control instanceof Combo) {
			widgetSelected((Combo) control);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Widget selected.
	 * @param combo the combo
	 */
	private void widgetSelected(Combo combo) {
		String filterPath = null, filterExt = "*.";
		try {
			String item = combo.getItems()[combo.getSelectionIndex()];
			filterExt += Utils.getExtension(item);

			if (lastAction == null) {
				File file = new File(item);
				filterPath = (String) (lastAction = file.getParentFile()
						.getAbsolutePath());
			} else {
				filterPath = (String) lastAction;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		String path = Utils.openFile(bool[0], filterPath, new String[] {
				filterExt, "*.*" });

		if (path != null) {
			select(combo, path);
		}

	}

	// ---------------------------------------------------------------

	/**
	 * Select.
	 * @param combo the combo
	 * @param path the path
	 */
	private void select(Combo combo, String path) {
		String[] items = combo.getItems();
		for (int i = 0; i < items.length; i++) {
			if (items[i].equalsIgnoreCase(path)) {
				return;
			}
		}
		combo.add(path);
		combo.select(items.length);
	}
}
