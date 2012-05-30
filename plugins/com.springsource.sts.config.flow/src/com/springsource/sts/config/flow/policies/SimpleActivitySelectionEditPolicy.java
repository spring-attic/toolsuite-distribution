/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.policies;

import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

import com.springsource.sts.config.flow.figures.SimpleActivityLabel;
import com.springsource.sts.config.flow.parts.SimpleActivityPart;

/**
 * Handles selection of SimpleActivites. Primary selection is denoted by
 * highlight and a focus rectangle. Normal selection is denoted by highlight
 * only.
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class SimpleActivitySelectionEditPolicy extends NonResizableEditPolicy {

	private SimpleActivityLabel getLabel() {
		SimpleActivityPart part = (SimpleActivityPart) getHost();
		return ((SimpleActivityLabel) part.getFigure());
	}

	/**
	 * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#hideFocus()
	 */
	@Override
	protected void hideFocus() {
		getLabel().setFocus(false);
	}

	/**
	 * @see org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#hideSelection()
	 */
	@Override
	protected void hideSelection() {
		getLabel().setSelected(false);
		getLabel().setFocus(false);

	}

	/**
	 * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#showFocus()
	 */
	@Override
	protected void showFocus() {
		getLabel().setFocus(true);
	}

	/**
	 * @see org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#showSelection()
	 */
	@Override
	protected void showPrimarySelection() {
		getLabel().setSelected(true);
		getLabel().setFocus(true);
	}

	/**
	 * @see org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#showSelection()
	 */
	@Override
	protected void showSelection() {
		getLabel().setSelected(true);
		getLabel().setFocus(false);
	}

}
