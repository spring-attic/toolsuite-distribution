/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.proposals;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.internal.ui.text.correction.proposals.ModifierChangeCorrectionProposal;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.swt.graphics.Image;

import com.springsource.sts.quickfix.QuickfixUtils;

/**
 * @author Terry Denney
 */
public class AddStaticToFieldQuickFixProposal extends BeanAttributeQuickFixProposal implements ICompletionProposal {

	private final String fieldName;

	private final ModifierChangeCorrectionProposal proposal;

	public AddStaticToFieldQuickFixProposal(int offset, int length, boolean missingEndQuote, IJavaProject javaProject,
			String className, String fieldName) {
		super(offset, length, missingEndQuote);
		this.fieldName = fieldName;

		this.proposal = QuickfixUtils.createModifierChangeCorrectionProposal(className, fieldName, javaProject,
				getDisplayString(), true);
	}

	@Override
	public void applyQuickFix(IDocument document) {
		proposal.apply(document);
	}

	public String getDisplayString() {
		return "change modifier of " + fieldName + " to static";
	}

	public Image getImage() {
		return JavaPluginImages.get(JavaPluginImages.IMG_CORRECTION_CHANGE);
	}
}
