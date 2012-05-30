/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.processors;

import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;
import org.eclipse.jface.text.quickassist.IQuickAssistProcessor;
import org.eclipse.jface.text.source.Annotation;

/**
 * Parent abstract quick assist processor for beans XML editor.
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public abstract class BeanQuickAssistProcessor implements IQuickAssistProcessor {

	protected int offset, length;

	protected String text;

	protected boolean missingEndQuote;

	public BeanQuickAssistProcessor(int offset, int length, String text, boolean missingEndQuote) {
		this.offset = offset;
		this.length = length;
		this.text = text;
		this.missingEndQuote = missingEndQuote;
	}

	public boolean canAssist(IQuickAssistInvocationContext invocationContext) {
		return false;
	}

	public boolean canFix(Annotation annotation) {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BeanQuickAssistProcessor) {
			BeanQuickAssistProcessor processor = (BeanQuickAssistProcessor) obj;
			return processor.offset == offset && processor.text.equals(text);
		}
		return super.equals(obj);
	}

	public String getErrorMessage() {
		return null;
	}

	@Override
	public int hashCode() {
		return ("" + offset + text).hashCode();
	}

}
