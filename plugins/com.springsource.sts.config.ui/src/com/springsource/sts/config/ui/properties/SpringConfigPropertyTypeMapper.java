/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.properties;

import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.views.properties.tabbed.AbstractTypeMapper;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.Transition;
import com.springsource.sts.config.flow.parts.ActivityPart;
import com.springsource.sts.config.flow.parts.TransitionPart;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class SpringConfigPropertyTypeMapper extends AbstractTypeMapper {

	@SuppressWarnings("rawtypes")
	@Override
	public Class mapType(Object object) {
		if (object instanceof IDOMNode) {
			return object.getClass();
		}
		if (object instanceof TreeItem) {
			TreeItem item = (TreeItem) object;
			return mapType(item.getData());
		}
		if (object instanceof ActivityPart) {
			ActivityPart part = (ActivityPart) object;
			Activity model = part.getModelElement();
			return mapType(model.getInput());
		}
		if (object instanceof TransitionPart) {
			TransitionPart part = (TransitionPart) object;
			Transition trans = (Transition) part.getModel();
			return mapType(trans.getInput());
		}
		return super.mapType(object);
	}

}
