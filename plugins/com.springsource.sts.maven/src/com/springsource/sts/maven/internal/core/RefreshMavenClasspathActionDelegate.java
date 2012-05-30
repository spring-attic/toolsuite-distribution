/******************************************************************************************
 * Copyright (c) 2010, 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.maven.internal.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.springframework.ide.eclipse.core.SpringCoreUtils;
import org.springframework.ide.eclipse.core.java.JdtUtils;
import org.springframework.ide.eclipse.ui.SpringUIUtils;

import com.springsource.sts.maven.MavenCorePlugin;

/**
 * @author Christian Dupuis
 * @author Martin Lippert
 */
public class RefreshMavenClasspathActionDelegate implements IObjectActionDelegate {

	private final List<IProject> selected = new ArrayList<IProject>();

	public void run(IAction action) {
		Set<IJavaProject> projects = new LinkedHashSet<IJavaProject>();
		Iterator<IProject> iter = selected.iterator();
		while (iter.hasNext()) {
			IProject project = iter.next();
			if (JdtUtils.isJavaProject(project)) {
				projects.add(JdtUtils.getJavaProject(project));
			}
		}

		for (final IJavaProject javaProject : projects) {
			MavenCorePlugin.getDefault().scheduleClasspathUpdateJob(javaProject.getProject());
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		selected.clear();
		boolean enabled = true;
		if (selection instanceof IStructuredSelection) {
			Iterator<?> iter = ((IStructuredSelection) selection).iterator();
			while (iter.hasNext()) {
				Object obj = iter.next();
				if (obj instanceof IJavaProject) {
					obj = ((IJavaProject) obj).getProject();
				}
				if (obj instanceof IResource) {
					IResource project = (IResource) obj;
					if (!project.getProject().isOpen()) {
						enabled = false;
						break;
					}
					else if (SpringCoreUtils.hasNature(project, MavenCorePlugin.M2ECLIPSE_NATURE) || SpringCoreUtils.hasNature(project, MavenCorePlugin.M2ECLIPSE_LEGACY_NATURE)) {
						enabled = false;
						break;
					}
					else {
						selected.add(project.getProject());
					}
				}
				else {
					enabled = false;
					break;
				}
			}
		}
		else {
			if (SpringUIUtils.getActiveEditor() != null) {
				if (SpringUIUtils.getActiveEditor().getEditorInput() instanceof IFileEditorInput) {
					selected.add(((IFileEditorInput) SpringUIUtils.getActiveEditor().getEditorInput()).getFile().getProject());
					enabled = true;
				}
			}
		}
		action.setEnabled(enabled);
	}
	
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

}
