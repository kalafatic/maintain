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

import java.util.Map;

import eu.kalafatic.maintain.view.editors.MaintainEditor;

/**
 * The Class class OverviewPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("rawtypes")
public class OverviewPage extends ProjectPage {

	/** The Constant ID. */
	public static final String ID = "eu.kalafatic.maintain.core.editors.pages.OverviewPage";

	/**
	 * Instantiates a new overview page.
	 * @param editor the editor
	 * @param index the index
	 * @param input the input
	 */

	public OverviewPage(MaintainEditor editor, int index, Map input) {
		super(editor, index, input);
	}
}