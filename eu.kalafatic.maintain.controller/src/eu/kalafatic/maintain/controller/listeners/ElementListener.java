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

import java.util.Map;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.utils.constants.EProject;

/**
 * The listener interface for receiving element events. The class that is interested in processing a element event implements this interface, and the
 * object created with that class is registered with a component using the component's <code>addElementListener<code> method. When
 * the element event occurs, that object's appropriate
 * method is invoked.
 * @see ElementEvent
 */
public class ElementListener implements Listener {

	/** The control. */
	private Control control;
	
	/** The ui. */
	private int ui;

	/** The decoration. */
	private ControlDecoration decoration;;

	/**
	 * Instantiates a new element listener.
	 * @param control the control
	 * @param ui the ui
	 */
	public ElementListener(Control control, int ui) {
		this.control = control;
		this.ui = ui;

		initDecoration(control);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------
	/**
	 * Inits the decoration.
	 * @param control the control
	 */
	private void initDecoration(Control control) {
		// this.decoration = GUIFactory.getControlDecorator(control, IStatus.OK, GUIFactory.getDecorationInfo(ui).toString());
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	@Override
	public void handleEvent(Event event) {
		Map map = (Map) control.getData();
		String key = (String) control.getData(EProject.KEY.name());

		switch (event.type) {
		case SWT.Selection:
			System.err.println();

			break;
		case SWT.Modify:

			if (control instanceof Text) {
				Text text = (Text) control;

				System.err.println();

			} else if (control instanceof Combo) {
				Combo new_name = (Combo) control;

			}
			System.err.println();
			break;

		default:
			break;
		}
	}

	// ---------------------------------------------------------------

}
