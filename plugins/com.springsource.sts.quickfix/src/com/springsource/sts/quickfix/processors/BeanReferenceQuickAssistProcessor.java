/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.processors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.springframework.ide.eclipse.beans.core.BeansCorePlugin;
import org.springframework.ide.eclipse.beans.core.internal.model.BeansProject;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.beans.core.model.IBeansConfig;
import org.springframework.ide.eclipse.beans.core.model.IBeansConfigSet;
import org.springframework.ide.eclipse.beans.core.model.IBeansModel;

import com.springsource.sts.quickfix.ContentAssistProposalWrapper;
import com.springsource.sts.quickfix.QuickfixContentAssistConverter;
import com.springsource.sts.quickfix.QuickfixUtils;
import com.springsource.sts.quickfix.proposals.AddConfigSetQuickFixProposal;
import com.springsource.sts.quickfix.proposals.AddToConfigSetQuickFixProposal;
import com.springsource.sts.quickfix.proposals.CreateImportQuickFixProposal;
import com.springsource.sts.quickfix.proposals.CreateNewBeanQuickFixProposal;
import com.springsource.sts.quickfix.proposals.RenameToSimilarNameQuickFixProposal;

/**
 * Quick assist processor for bean parent attribute in beans XML editor.
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
public class BeanReferenceQuickAssistProcessor extends BeanQuickAssistProcessor {

	public static List<ICompletionProposal> computeBeanReferenceQuickAssistProposals(IDOMNode node,
			String attributeName, IFile file, String text, String beanName, int offset, int length,
			boolean missingEndQuote) {
		List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();

		Set<ContentAssistProposalWrapper> possibleProposals = new QuickfixContentAssistConverter(node, attributeName,
				file).getReferenceableBeanDescriptions(text, false);
		for (ContentAssistProposalWrapper proposal : possibleProposals) {
			proposals.add(new RenameToSimilarNameQuickFixProposal(proposal.getName(), offset, length, missingEndQuote,
					proposal.getDisplayText()));
		}

		IDOMNode beanNode = QuickfixUtils.getEnclosingBeanNode(node);
		if (beanNode != null) {
			proposals.add(new CreateNewBeanQuickFixProposal(offset, length, missingEndQuote, text, beanNode));
		}
		else {
			// this case is only for name space elements where there is no
			// enclosing bean node
			proposals.add(new CreateNewBeanQuickFixProposal(offset, length, missingEndQuote, text, node));
		}

		IBeansModel model = BeansCorePlugin.getModel();
		BeansProject project = (BeansProject) model.getProject(file.getProject());
		if (project != null) {

			Set<IResource> foundResources = new HashSet<IResource>();
			List<ICompletionProposal> importProposals = new ArrayList<ICompletionProposal>();
			List<ICompletionProposal> addToConfigSetProposals = new ArrayList<ICompletionProposal>();
			List<ICompletionProposal> addConfigSetProposals = new ArrayList<ICompletionProposal>();

			Set<IBeansConfig> configs = project.getConfigs();
			for (IBeansConfig config : configs) {
				IBean bean = config.getBean(beanName);
				if (bean != null) {
					importProposals.add(new CreateImportQuickFixProposal(offset, length, missingEndQuote, bean,
							beanNode, project, file));

					Set<IBeansConfigSet> configSets = project.getConfigSets();

					for (IBeansConfigSet configSet : configSets) {
						if (configSet.hasConfig((IFile) bean.getElementResource())) {
							addToConfigSetProposals.add(new AddToConfigSetQuickFixProposal(offset, length,
									missingEndQuote, file, configSet, project));
						}
					}
					addConfigSetProposals.add(new AddConfigSetQuickFixProposal(offset, length, missingEndQuote, bean,
							file));
					foundResources.add(bean.getElementResource());
				}

			}

			proposals.addAll(importProposals);
			proposals.addAll(addToConfigSetProposals);
			proposals.addAll(addConfigSetProposals);
		}

		return proposals;
	}

	private final IDOMNode node;

	private final IFile file;

	private final String attributeName;

	private final String beanName;

	public BeanReferenceQuickAssistProcessor(int offset, int length, String text, boolean missingEndQuote,
			IDOMNode node, String attributeName, String beanName, IFile file) {
		super(offset, length, text, missingEndQuote);
		this.node = node;
		this.attributeName = attributeName;
		this.beanName = beanName;
		this.file = file;
	}

	public BeanReferenceQuickAssistProcessor(String beanName, IFile file) {
		super(0, 0, beanName, false);
		this.node = null;
		this.attributeName = null;
		this.beanName = beanName;
		this.file = file;
	}

	public ICompletionProposal[] computeQuickAssistProposals(IQuickAssistInvocationContext invocationContext) {
		List<ICompletionProposal> proposals = computeBeanReferenceQuickAssistProposals(node, attributeName, file, text,
				beanName, offset, length, missingEndQuote);
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

}
