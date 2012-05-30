/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt.util;

import org.eclipse.jdt.core.dom.ASTNode;

/**
 * @author Terry Denney
 * @since 2.6
 */
public class UriTemplateVariable {

	private final String variableName;

	private final int offset;

	private final ASTNode node;

	public UriTemplateVariable(String variableName, int offset, ASTNode node) {
		this.variableName = variableName;
		this.offset = offset;
		this.node = node;
	}

	public String getVariableName() {
		return variableName;
	}

	public int getOffsetFromNode() {
		return offset;
	}

	public int getOffset() {
		return offset + node.getStartPosition();
	}

	public ASTNode getNode() {
		return node;
	}

}
