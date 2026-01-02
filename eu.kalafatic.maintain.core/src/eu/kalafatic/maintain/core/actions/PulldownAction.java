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
package eu.kalafatic.maintain.core.actions;

import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * The Class class PulldownAction.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class PulldownAction extends Action {

	/**
	 * The Class class MenuCreator.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	private class MenuCreator implements IMenuCreator {

		/** The menu. */
		Menu menu;
		
		/** The v. */
		Vector<MenuItem> v = new Vector<MenuItem>();

		/* (non-Javadoc)
		 * @see org.eclipse.jface.action.IMenuCreator#dispose()
		 */
		@Override
		public void dispose() {
			for (int i = 0; i < v.size(); i++) {
				MenuItem item = v.get(i);
				if (item != null) {
					item.dispose();
					item = null;
				}
			}
			if (menu != null) {
				menu.dispose();
				menu = null;
			}
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Control)
		 */
		@Override
		public Menu getMenu(Control parent) {
			int num = 2;
			if (num > 0) {
				menu = new Menu(parent);
				for (int i = 1; i <= num; i++) {
					MenuItem item = new MenuItem(menu, SWT.PUSH);
					item.setText(Integer.toString(i));
					final int index = i;
					item.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent event) {
							// do something for sub menu
						}
					});
					v.add(item);
				}
				return menu;
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Menu)
		 */
		@Override
		public Menu getMenu(Menu parent) {
			return null;
		}

	}

	/**
	 * Instantiates a new pulldown action.
	 * @param name the name
	 */
	public PulldownAction(String name) {
		super(name, AS_DROP_DOWN_MENU);
		// setImageDescriptor(ImageFactory.getImageDescriptor("icons/1.gif "));
		setMenuCreator(new MenuCreator());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		// do something for top menu
	}

}
