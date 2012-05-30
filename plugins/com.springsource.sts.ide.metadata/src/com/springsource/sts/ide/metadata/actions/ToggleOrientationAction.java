/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

import com.springsource.sts.ide.metadata.MetadataUIImages;
import com.springsource.sts.ide.metadata.ui.RequestMappingView;

/**
 * @author Leo Dos Santos
 */
public class ToggleOrientationAction extends Action {

	private RequestMappingView view;

	private int orientation;

	public ToggleOrientationAction(RequestMappingView view, int orientation) {
		super("", AS_RADIO_BUTTON); //$NON-NLS-1$
		if (orientation == SWT.HORIZONTAL) {
			setText(Messages.ToggleOrientationAction_DESCRIPTION_HORIZONTAL);
			setDescription(Messages.ToggleOrientationAction_DESCRIPTION_HORIZONTAL);
			setToolTipText(Messages.ToggleOrientationAction_DESCRIPTION_HORIZONTAL);
			setImageDescriptor(MetadataUIImages.DESC_OBJS_ORIENTATION_HORIZONTAL);
		} else if (orientation == SWT.VERTICAL) {
			setText(Messages.ToggleOrientationAction_DESCRIPTION_VERTICAL);
			setDescription(Messages.ToggleOrientationAction_DESCRIPTION_VERTICAL);
			setToolTipText(Messages.ToggleOrientationAction_DESCRIPTION_VERTICAL);
			setImageDescriptor(MetadataUIImages.DESC_OBJS_ORIENTATION_VERTICAL);
		}
		this.view = view;
		this.orientation = orientation;
	}

	@Override
	public void run() {
		if (isChecked()) {
			view.setOrientation(orientation);
		}
	}

	public int getOrientation() {
		return orientation;
	}

}
