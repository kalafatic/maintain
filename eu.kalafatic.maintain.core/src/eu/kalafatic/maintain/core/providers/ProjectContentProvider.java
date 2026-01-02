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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.kalafatic.utils.constants.EProject;

/**
 * The Class class ProjectContentProvider.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProjectContentProvider implements ITreeContentProvider {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(Object arg0) {
		return getElements(arg0);
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object arg0) {
		return null;
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Map) {
			return !((Map) element).isEmpty();
		} else if (element instanceof List) {
			return !((List) element).isEmpty();
		} else if (element instanceof Entry) {
			return hasChildren(((Entry) element).getValue());
		}
		return false;
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object element) {
		if (element instanceof Map) {
			Map<String, Object> map = (Map<String, Object>) element;
			Map<String, Object> filteredMap = new LinkedHashMap<String, Object>(map);
			filteredMap.remove(EProject.KEY.name());

			if (filteredMap.containsKey(EProject.CHILDREN.name())) {

				if (((List) filteredMap.get(EProject.CHILDREN.name())).isEmpty()) {
					filteredMap.remove(EProject.CHILDREN.name());
				}
			}
			return filteredMap.entrySet().toArray();

		} else if (element instanceof List) {
			return ((List) element).toArray();
		} else if (element instanceof Entry) {
			return getElements(((Entry) element).getValue());
		}
		return null;
	}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {}

	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// Nothing to change
	}
}
