/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;

import com.springsource.sts.ide.metadata.ui.RequestMappingMethodToClassMap;
import com.springsource.sts.ide.metadata.ui.RequestMappingViewLabelProvider;

/**
 * @author Leo Dos Santos
 */
public class OpenRequestMappingUrlWizard extends Wizard {

	private RequestMappingMethodToClassMap input;

	private RequestMappingViewLabelProvider labelProvider;

	private IProject project;

	private OpenRequestMappingUrlWizardPage page;

	public OpenRequestMappingUrlWizard(RequestMappingMethodToClassMap input,
			RequestMappingViewLabelProvider labelProvider, IProject project) {
		this.input = input;
		this.labelProvider = labelProvider;
		this.project = project;
		setWindowTitle(Messages.OpenRequestMappingUrlWizard_TITLE);
	}

	@Override
	public void addPages() {
		page = new OpenRequestMappingUrlWizardPage(input, labelProvider,
				project);
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		page.performPageFinish();
		return true;
	}

}
