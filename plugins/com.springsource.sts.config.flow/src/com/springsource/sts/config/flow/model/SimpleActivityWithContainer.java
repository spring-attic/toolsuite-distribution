/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public abstract class SimpleActivityWithContainer extends StructuredActivity {

	public SimpleActivityWithContainer() {
		super();
	}

	public SimpleActivityWithContainer(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	protected List<Activity> getChildrenFromXml() {
		return new ArrayList<Activity>();
	}

	@Override
	protected List<Transition> getOutgoingTransitionsFromXml() {
		List<Transition> list = super.getOutgoingTransitionsFromXml();
		List<Activity> registry = getDiagram().getModelRegistry();
		for (Activity activity : registry) {
			if (activity instanceof ParallelActivity && activity.getInput().equals(getInput())) {
				Transition trans = new Transition(this, activity, getInput());
				trans.setLineStyle(Transition.DASHED_CONNECTION);
				list.add(trans);
			}
		}
		return list;
	}

}
