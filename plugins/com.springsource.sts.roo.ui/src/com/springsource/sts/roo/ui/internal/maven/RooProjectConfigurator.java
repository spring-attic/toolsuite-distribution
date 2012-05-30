/******************************************************************************************
 * Copyright (c) 2010 - 2012 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.roo.ui.internal.maven;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.configurator.ProjectConfigurationRequest;
import org.springframework.ide.eclipse.core.SpringCore;
import org.springframework.ide.eclipse.core.SpringCoreUtils;

import com.springsource.sts.maven.AbstractSpringProjectConfigurator;
import com.springsource.sts.roo.core.RooCoreActivator;
import com.springsource.sts.roo.ui.internal.actions.OpenShellJob;

/**
 * M2Eclipse project configuration extension that configures a project to get
 * the Roo project nature.
 * @author Christian Dupuis
 * @author Leo Dos Santos
 * @since 2.5.0
 */
public class RooProjectConfigurator extends AbstractSpringProjectConfigurator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doConfigure(MavenProject mavenProject, IProject project, ProjectConfigurationRequest request,
			IProgressMonitor monitor) throws CoreException {
		// first apply Roo project nature
		boolean hasNature = false;
		for (Artifact art : mavenProject.getArtifacts()) {
			if (art.getArtifactId().startsWith("org.springframework.roo")
					&& art.getGroupId().equals("org.springframework.roo")) {
				SpringCoreUtils.addProjectNature(project, SpringCore.NATURE_ID, monitor);
				SpringCoreUtils.addProjectNature(project, RooCoreActivator.NATURE_ID, monitor);
				hasNature = true;
			}
		}
		if (!hasNature) {
			hasNature = (configureNature(project, mavenProject, SpringCore.NATURE_ID, true, monitor) && configureNature(
					project, mavenProject, RooCoreActivator.NATURE_ID, true, monitor));
		}

		if (hasNature) {
			Artifact parent = mavenProject.getParentArtifact();
			if (parent != null) {
				// traverse the parent chain
				IMavenProjectFacade facade = projectManager.getMavenProject(parent.getGroupId(),
						parent.getArtifactId(), parent.getVersion());
				if (facade != null && facade.getMavenProject() != null && facade.getProject() != null) {
					doConfigure(facade.getMavenProject(), facade.getProject(), request, monitor);
				}
			}
			else {
				// open the Roo Shell for the project
				new OpenShellJob(project).schedule();
			}
		}
	}

}
