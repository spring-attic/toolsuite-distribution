/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph.parts;

import org.eclipse.gef.EditPart;

import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.model.LabelledTransition;
import com.springsource.sts.config.flow.parts.AbstractConfigEditPartFactory;
import com.springsource.sts.config.ui.editors.webflow.graph.model.ActionStateModelElement;
import com.springsource.sts.config.ui.editors.webflow.graph.model.DecisionStateModelElement;
import com.springsource.sts.config.ui.editors.webflow.graph.model.EndStateModelElement;
import com.springsource.sts.config.ui.editors.webflow.graph.model.SubflowStateModelElement;
import com.springsource.sts.config.ui.editors.webflow.graph.model.ViewStateModelElement;
import com.springsource.sts.config.ui.editors.webflow.graph.model.WebFlowDiagram;

/**
 * @author Leo Dos Santos
 */
public class WebFlowEditPartFactory extends AbstractConfigEditPartFactory {

	public WebFlowEditPartFactory(AbstractConfigGraphicalEditor editor) {
		super(editor);
	}

	@Override
	protected EditPart createEditPartFromModel(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof WebFlowDiagram) {
			part = new WebFlowDiagramEditPart((WebFlowDiagram) model);
		}
		else if (model instanceof ActionStateModelElement) {
			part = new ActionStateGraphicalEditPart((ActionStateModelElement) model);
		}
		else if (model instanceof DecisionStateModelElement) {
			part = new DecisionStateGraphicalEditPart((DecisionStateModelElement) model);
		}
		else if (model instanceof EndStateModelElement) {
			part = new EndStateGraphicalEditPart((EndStateModelElement) model);
		}
		else if (model instanceof SubflowStateModelElement) {
			part = new SubflowStateGraphicalEditPart((SubflowStateModelElement) model);
		}
		else if (model instanceof ViewStateModelElement) {
			part = new ViewStateGraphicalEditPart((ViewStateModelElement) model);
		}
		else if (model instanceof LabelledTransition) {
			part = new WebFlowTransitionPart((LabelledTransition) model);
		}
		return part;
	}

}
