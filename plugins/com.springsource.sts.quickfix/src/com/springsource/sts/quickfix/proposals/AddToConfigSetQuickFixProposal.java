/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.proposals;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.swt.graphics.Image;
import org.springframework.ide.eclipse.beans.core.internal.model.BeansConfigSet;
import org.springframework.ide.eclipse.beans.core.internal.model.BeansProject;
import org.springframework.ide.eclipse.beans.core.internal.model.update.BeansModelUpdater;
import org.springframework.ide.eclipse.beans.core.model.IBeansConfigSet;
import org.springframework.ide.eclipse.beans.ui.BeansUIImages;

import com.springsource.sts.quickfix.QuickfixUtils;

/**
 * Quick fix proposal for adding config file into config set
 * @author Terry Denney
 */
public class AddToConfigSetQuickFixProposal extends BeanAttributeQuickFixProposal implements ICompletionProposal {

	private final IBeansConfigSet configSet;

	private final BeansProject project;

	private final IFile file;

	public AddToConfigSetQuickFixProposal(int offset, int length, boolean missingEndQuote, IFile file,
			IBeansConfigSet configSet, BeansProject project) {
		super(offset, length, missingEndQuote);
		this.configSet = configSet;
		this.project = project;
		this.file = file;
	}

	@Override
	public void applyQuickFix(IDocument document) {
		if (configSet instanceof BeansConfigSet) {
			BeansConfigSet newConfigSet = new BeansConfigSet(project, configSet.getElementName(), configSet
					.getConfigNames(), configSet.getType());
			newConfigSet.addConfig(QuickfixUtils.getConfigName(file));
			project.removeConfigSet(configSet.getElementName());
			project.addConfigSet(newConfigSet);

			project.saveDescription();
			BeansModelUpdater.updateProject(project);
		}
	}

	public String getDisplayString() {
		return "Add " + QuickfixUtils.getConfigName(file) + " to config set " + configSet.getElementName();
	}

	public Image getImage() {
		return BeansUIImages.getImage(BeansUIImages.IMG_OBJS_CONFIG_SET);
	}

}
