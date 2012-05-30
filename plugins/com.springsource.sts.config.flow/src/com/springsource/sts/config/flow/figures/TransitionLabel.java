/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.figures;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Font;

/**
 * @author Leo Dos Santos
 */
public class TransitionLabel extends SimpleActivityLabel {

	private int maxLength;

	private String subStringText;

	public TransitionLabel() {
		this(-1);
	}

	public TransitionLabel(int maxLength) {
		super(PositionConstants.EAST);
		this.maxLength = maxLength;
	}

	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		int hint;
		if (maxLength < 0) {
			hint = wHint;
		}
		else {
			hint = maxLength;
		}
		return super.getPreferredSize(hint, calculateTextSize().height);
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	@Override
	public String getSubStringText() {
		subStringText = getText();
		int widthShrink = getTextSize().width - getPreferredSize().width;
		if (widthShrink <= 0) {
			return subStringText;
		}

		Dimension effectiveSize = getTextSize().getExpanded(-widthShrink, 0);
		Font currentFont = getFont();
		int dotsWidth = getTextUtilities().getTextExtents(getTruncationString(), currentFont).width;

		if (effectiveSize.width < dotsWidth) {
			effectiveSize.width = dotsWidth;
		}

		int subStringLength = getTextUtilities().getLargestSubstringConfinedTo(getText(), currentFont,
				effectiveSize.width - dotsWidth);
		subStringText = new String(getText().substring(0, subStringLength) + getTruncationString());
		return subStringText;
	}

}
