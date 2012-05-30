/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.model;

import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.IDiagramModelFactory;
import com.springsource.sts.config.flow.model.Transition;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class IntegrationDiagram extends AbstractConfigFlowDiagram {

	private int channelCount;

	public IntegrationDiagram(AbstractConfigGraphicalEditor editor) {
		super(editor);
	}

	@Override
	protected IDiagramModelFactory getModelFactory() {
		return new IntegrationModelFactory();
	}

	public String getNewChannelId() {
		return Integer.toString(++channelCount);
	}

	@Override
	protected void getTransitionsFromXml(Activity source, Activity target, List<Transition> transitions,
			List<String> attrs, boolean incoming, boolean primary) {
		for (String label : attrs) {
			Attr attr = source.getInput().getAttributeNode(label);
			if (attr instanceof IDOMAttr) {
				Node ref = getReferencedNode(attr.getValue());
				if (ref instanceof IDOMElement && target.getInput().equals(ref)) {
					Transition trans;
					if (target instanceof ImplicitChannelModelElement) {
						if (incoming) {
							trans = new ImplicitTransition(target, source, (IDOMAttr) attr);
						}
						else {
							trans = new ImplicitTransition(source, target, (IDOMAttr) attr);
						}
					}
					else {
						if (incoming) {
							trans = new Transition(target, source, (IDOMAttr) attr);
						}
						else {
							trans = new Transition(source, target, (IDOMAttr) attr);
						}
					}
					if (!(trans instanceof ImplicitTransition) && !primary) {
						trans.setLineStyle(Transition.DASHED_CONNECTION);
					}
					transitions.add(trans);
				}
			}
		}
	}

	@Override
	public boolean listContainsElement(List<Activity> list, Activity element) {
		for (Activity activity : list) {
			if (element instanceof ImplicitChannelModelElement) {
				if (activity instanceof ImplicitChannelModelElement && element.getName() != null
						&& activity.getName() != null && (element.getName().equals(activity.getName()))) {
					return true;
				}
			}
			else if (element instanceof PlaceholderModelElement) {
				if (element.getInput().equals(activity.getInput())) {
					return true;
				}
			}
		}
		return super.listContainsElement(list, element);
	}
}
