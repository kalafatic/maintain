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
package eu.kalafatic.maintain.core.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.kalafatic.maintain.core.perspectives.MaintainCorePerspective;

/**
 * The Class class AppUtils.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class AppUtils {

	/** The Constant DEFAULT_TEXT_EDITOR_ID. */
	public static final String DEFAULT_TEXT_EDITOR_ID = "org.eclipse.ui.DefaultTextEditor";

	/** The i editor reference. */
	public static IEditorReference iEditorReference;

	/** The instance. */
	public volatile static AppUtils INSTANCE = new AppUtils();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Creates the project.
	 * @param projectName the project name
	 * @return the i project
	 */
	public IProject createProject(String projectName) {
		try {
			Assert.isNotNull(projectName);
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceRoot root = workspace.getRoot();
			IProject project = root.getProject(projectName);
			IFolder folder = project.getFolder("Core");
			IFile file = folder.getFile(projectName + ".xml");
			// at this point, no resources have been created
			if (!project.exists()) {
				project.create(null);
			}
			if (!project.isOpen()) {
				project.open(null);
			}
			if (!folder.exists()) {
				folder.create(IResource.NONE, true, new NullProgressMonitor());
			}
			if (!file.exists()) {
				byte[] bytes = ("<!-- " + projectName + "® -->").getBytes();
				InputStream source = new ByteArrayInputStream(bytes);
				file.create(source, IResource.NONE, new NullProgressMonitor());
			}
			return project;
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the marker.
	 * @param file the file
	 * @param type the type
	 * @return the i marker
	 */
	public IMarker createMarker(IFile file, String type) {
		try {
			Assert.isNotNull(file);
			return file.createMarker(type);
		} catch (CoreException e) {
			// Logger.getGlobal().(e.getLocalizedMessage());
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Schedule jobs.
	 * @param jobs the jobs
	 */
	public void scheduleJobs(List<Job> jobs) {
		for (final Job job : jobs) {
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					job.schedule();
				}
			});
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the current perspective.
	 * @return the current perspective
	 */
	public static IPerspectiveDescriptor getCurrentPerspective() {
		IPerspectiveDescriptor perspective = null;
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if ((window != null) && (window.getActivePage() != null)) {
			perspective = window.getActivePage().getPerspective();
		}
		return perspective != null ? perspective : PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId(MaintainCorePerspective.ID);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the selected adaptable.
	 * @param selection the selection
	 * @return the selected adaptable
	 * @throws PartInitException the part init exception
	 */
	public static IAdaptable getSelectedAdaptable(ISelection selection) throws PartInitException {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection iStructuredSelection = (IStructuredSelection) selection;
			if (iStructuredSelection.size() != 1) {
				return null;
			}
			return getSelectedAdaptable(iStructuredSelection.getFirstElement());
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the selected adaptable.
	 * @param object the object
	 * @return the selected adaptable
	 */
	private static IAdaptable getSelectedAdaptable(Object object) {
		if (object instanceof IAdaptable) {
			return (IAdaptable) object;
		}
		if (object instanceof File) {
			File file = (File) object;
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IPath location = Path.fromOSString(file.getAbsolutePath());

			IFileStore fileStore = EFS.getLocalFileSystem().getStore(new Path(file.getAbsolutePath()));

			// IDE.openEditorOnFileStore(getSite().getPage(), fileStore);

			// IPath iPath = new Path(file.getAbsolutePath());
			// IFile iFile = ResourcesPlugin.getWorkspace().getRoot()
			// .getFile(iPath);

			return fileStore;
		}
		return null;
	}

}
