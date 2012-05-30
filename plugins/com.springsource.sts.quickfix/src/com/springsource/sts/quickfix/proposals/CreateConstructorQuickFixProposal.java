/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.proposals;

import java.util.List;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;
import org.springframework.ide.eclipse.beans.ui.BeansUIImages;
import org.springsource.ide.eclipse.commons.core.StatusHandler;

import com.springsource.sts.quickfix.QuickfixUtils;

/**
 * Quick fix proposal for creating a new constructor
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
public class CreateConstructorQuickFixProposal extends BeanAttributeQuickFixProposal {

	private final IJavaProject javaProject;

	private final String className;

	private final List<String> constructorArgClassNames;

	public CreateConstructorQuickFixProposal(int offset, int length, String text, boolean missingEndQuote,
			IJavaProject javaProject, List<String> constructorArgClassNames) {
		super(offset, length, missingEndQuote);
		this.javaProject = javaProject;
		this.className = text;
		this.constructorArgClassNames = constructorArgClassNames;
	}

	@Override
	public void applyQuickFix(IDocument document) {
		try {
			IType type = javaProject.findType(className);
			if (type != null) {
				QuickfixUtils.createConstructor(document, type, constructorArgClassNames, javaProject);
			}
		}
		catch (JavaModelException e) {
			StatusHandler.log(e.getStatus());
		}
	}

	public String getDisplayString() {
		String shortClassName = className;
		int pos = className.lastIndexOf(".");
		if (pos > 0) {
			shortClassName = className.substring(pos + 1);
		}

		String args = "";
		for (int i = 0; i < constructorArgClassNames.size(); i++) {
			if (i > 0) {
				args += ", ";
			}
			args += "Object";
		}
		return "Create constructor " + shortClassName + "(" + args + ")";
	}

	public Image getImage() {
		return BeansUIImages.getImage(BeansUIImages.IMG_OBJS_CONSTRUCTOR);
	}

}
