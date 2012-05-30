/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.policies;

import org.eclipse.draw2d.Label;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

import com.springsource.sts.config.flow.figures.SubgraphFigure;

/**
 * StructuredActivityDirectEditPolicy
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class StructuredActivityDirectEditPolicy extends ActivityDirectEditPolicy {

	/**
	 * @see DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
	 */
	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		((Label) ((SubgraphFigure) getHostFigure()).getHeader()).setText(value);
		((Label) ((SubgraphFigure) getHostFigure()).getFooter()).setText("/" + value);//$NON-NLS-1$
		// hack to prevent async layout from placing the cell editor twice.
		getHostFigure().getUpdateManager().performUpdate();
	}

}
