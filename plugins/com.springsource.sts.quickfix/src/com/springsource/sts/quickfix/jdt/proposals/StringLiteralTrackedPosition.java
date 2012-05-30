/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt.proposals;

import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.rewrite.ITrackedNodePosition;

/**
 * Wrapper class for tracking position of the string value inside
 * {@link StringLiteral}
 * 
 * @author Terry Denney
 * @since 2.6
 */
public class StringLiteralTrackedPosition implements ITrackedNodePosition {

	private final ITrackedNodePosition position;

	private final int offset;

	private final int length;

	private final boolean isEndCursor;

	/**
	 * @param position
	 * @param offset
	 * @param length if length < 0, use length of wrapped position minus the
	 * quotes
	 * @param isEndCursor true if this position is used to indicate end cursor
	 */
	public StringLiteralTrackedPosition(ITrackedNodePosition position, int offset, int length, boolean isEndCursor) {
		this.position = position;
		this.offset = offset;
		this.length = length;
		this.isEndCursor = isEndCursor;
	}

	public StringLiteralTrackedPosition(ITrackedNodePosition position) {
		this(position, 0, -1, false);
	}

	public int getLength() {
		if (length < 0) {
			return position.getLength() - offset - 2;
		}
		else {
			return length;
		}
	}

	public int getStartPosition() {
		if (isEndCursor) {
			return position.getStartPosition() + 1 + offset + getLength();
		}
		else {
			return position.getStartPosition() + 1 + offset;
		}
	}

}
