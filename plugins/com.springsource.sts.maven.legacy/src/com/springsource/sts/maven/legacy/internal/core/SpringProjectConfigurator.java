/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.maven.legacy.internal.core;

import org.apache.maven.project.MavenProject;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.maven.ide.eclipse.jdt.IJavaProjectConfigurator;
import org.maven.ide.eclipse.project.configurator.ProjectConfigurationRequest;
import org.springframework.ide.eclipse.core.SpringCore;

import com.springsource.sts.maven.legacy.AbstractSpringProjectConfigurator;

/**
 * M2Eclipse project configuration extension that configures a project to get the Spring project
 * nature.
 * @author Christian Dupuis
 * @since 2.5.0
 */
public class SpringProjectConfigurator extends AbstractSpringProjectConfigurator implements IJavaProjectConfigurator {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doConfigure(MavenProject mavenProject, IProject project, ProjectConfigurationRequest request,
			IProgressMonitor monitor) throws CoreException {
		configureNature(project, mavenProject, SpringCore.NATURE_ID, true, monitor);
	}
}
