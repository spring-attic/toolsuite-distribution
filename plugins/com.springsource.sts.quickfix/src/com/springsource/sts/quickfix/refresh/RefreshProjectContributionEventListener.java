/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.refresh;

import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.springframework.ide.eclipse.beans.core.internal.model.validation.BeansConfigValidator;
import org.springframework.ide.eclipse.core.project.IProjectContributor;
import org.springframework.ide.eclipse.core.project.ProjectContributionEventListenerAdapter;

/**
 * @author Christian Dupuis
 * @since 2.1.1
 */
public class RefreshProjectContributionEventListener extends ProjectContributionEventListenerAdapter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void finishContributor(IProjectContributor contributor, Set<IResource> affectedResources) {
		if (contributor instanceof BeansConfigValidator && affectedResources != null && affectedResources.size() > 0) {
			RefreshUtils.refreshEditors(affectedResources);
		}
	}
}
