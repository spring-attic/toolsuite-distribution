/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt.proposals;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ITrackedNodePosition;
import org.eclipse.jdt.internal.ui.text.correction.proposals.LinkedCorrectionProposal;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * @author Terry Denney
 * @since 2.6
 */
public abstract class AnnotationCompletionProposal extends LinkedCorrectionProposal {

	private ITrackedNodePosition track;

	public AnnotationCompletionProposal(String name, ICompilationUnit cu, Image image) {
		super(name, cu, null, 0, image);
	}

	@Override
	protected void performChange(IEditorPart part, IDocument document) throws CoreException {
		super.performChange(part, document);
		if (part instanceof ITextEditor && track != null) {
			((ITextEditor) part).selectAndReveal(track.getStartPosition(), 0);
		}
	}

	@Override
	/**
	 * Subclass should overwrite this method and call 
	 * setTrackPosition(ITrackedNodePosition) to set cursor location 
	 * after rewrite
	 */
	protected ASTRewrite getRewrite() throws CoreException {
		return super.getRewrite();
	}

	/**
	 * @param track tracked position for setting cursor after change is
	 * performed
	 */
	protected void setTrackPosition(ITrackedNodePosition track) {
		this.track = track;
	}

}
