/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.graphics.Image;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistProposalRecorder;

/**
 * Content assisnt proposal recorder for beans editor quickfix
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
public class QuickFixContentAssistProposalRecorder implements IContentAssistProposalRecorder {

	private final Set<ContentAssistProposalWrapper> proposals;

	public QuickFixContentAssistProposalRecorder() {
		this.proposals = new HashSet<ContentAssistProposalWrapper>();
	}

	public Set<ContentAssistProposalWrapper> getProposals() {
		return proposals;
	}

	public void recordProposal(Image image, int relevance, String displayText, String replaceText) {
		proposals.add(new ContentAssistProposalWrapper(replaceText, displayText));
	}

	public void recordProposal(Image image, int relevance, String displayText, String replaceText, Object proposedObject) {
		recordProposal(image, relevance, displayText, replaceText);
	}

}
