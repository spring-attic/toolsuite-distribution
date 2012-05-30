/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt.proposals;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution;

import com.springsource.sts.quickfix.QuickfixUtils;

/**
 * Abstract class for combining correction proposal with marker resolution
 * 
 * @author Terry Denney
 * @since 2.6
 */
public abstract class MarkerResolutionProposal extends AnnotationCompletionProposal implements IMarkerResolution {

	public MarkerResolutionProposal(String name, ICompilationUnit cu, Image image) {
		super(name, cu, image);
	}

	public void run(IMarker marker) {
		IDocument document = QuickfixUtils.getDocument(marker);
		if (document != null) {
			apply(document);
		}
	}

	public String getLabel() {
		return getName();
	}
}
