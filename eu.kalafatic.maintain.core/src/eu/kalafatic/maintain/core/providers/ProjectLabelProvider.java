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
package eu.kalafatic.maintain.core.providers;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eu.kalafatic.maintain.core.lib.FMaintainConstants;
import eu.kalafatic.maintain.core.utils.Utils;
import eu.kalafatic.utils.constants.EProject;

/**
 * The Class class ProjectLabelProvider.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProjectLabelProvider extends LabelProvider implements ILabelProvider {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		try {
			if (element instanceof Entry) {
				Entry<String, Object> entry = (Entry<String, Object>) element;

				if (entry.getKey().equals(EProject.CHILDREN.name())) {
					return FMaintainConstants.TREE_IMG;
				} else if (entry.getKey().equals(EProject.ATTRIBUTES.name())) {
					return FMaintainConstants.LIST_IMG;
				} else if (entry.getKey().equals(EProject.ELEMENTS.name())) {
					return FMaintainConstants.ELEMENTS_IMG;
				} else if (entry.getKey().equals(EProject.DATA.name())) {
					return null;
				} else if (entry.getKey().equals(EProject.VALUE.name())) {
					return FMaintainConstants.FRAME_IMG;
				} else {
					return FMaintainConstants.FILE_IMG;
				}
			} else if (element instanceof Map) {
				Map<String, Object> map = (Map<String, Object>) element;

				if (map.get(EProject.KEY.name()) == null) {
					return FMaintainConstants.LIST_IMG;
				} else if (map.get(EProject.KEY.name()).equals(EProject.MANIFEST.name())) {
					return FMaintainConstants.MAINTAIN_IMG;
				} else {
					return FMaintainConstants.PROJECT_IMG;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FMaintainConstants.FILE_IMG;
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		try {
			if (element instanceof Map) {
				Map<String, Object> map = (Map<String, Object>) element;
				if (map.get(EProject.KEY.name()) == null) {
					return EProject.ATTRIBUTES.literal;
				}
				return Utils.getName(map, EProject.KEY);

			} else if (element instanceof Entry) {
				Entry<String, Object> entry = (Entry<String, Object>) element;

				if (entry.getKey().equals(EProject.ATTRIBUTES.name())) {
					return EProject.ATTRIBUTES.literal;

				} else if (entry.getKey().equals(EProject.CHILDREN.name())) {
					return EProject.SUBPROJECTS.literal;

				} else if (entry.getKey().equals(EProject.ELEMENTS.name())) {
					return EProject.ELEMENTS.literal;
				} else if (entry.getKey().equals(EProject.DATA.name())) {
					return EProject.DATA.literal + ": " + entry.getValue().getClass();

				} else if (entry.getKey().equals(EProject.VALUE.name())) {
					String name = entry.getValue().getClass().getName();
					if (name != null) {
						return entry.getValue() + " (" + name.substring(name.lastIndexOf('.') + 1) + ")";
					}
					return (String) entry.getValue();
				}
				// simple elements
				return entry.getKey().toLowerCase() + ": " + entry.getValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {
		return false;
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void addListener(ILabelProviderListener listener) {

	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void removeListener(ILabelProviderListener listener) {

	}
}
