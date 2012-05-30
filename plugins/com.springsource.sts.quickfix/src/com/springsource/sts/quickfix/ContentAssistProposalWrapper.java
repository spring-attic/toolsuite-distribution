/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix;

/**
 * Wrapper for result of content assist calculator
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
public class ContentAssistProposalWrapper {

	private final String name, displayText;

	private Object proposedObject;

	public ContentAssistProposalWrapper(String name, String displayText) {
		this.name = name;
		this.displayText = displayText;
	}

	public ContentAssistProposalWrapper(String name, String displayText, Object proposedObject) {
		this(name, displayText);
		this.proposedObject = proposedObject;
	}

	public String getDisplayText() {
		return displayText;
	}

	public String getName() {
		return name;
	}

	public Object getProposedObject() {
		return proposedObject;
	}

}
