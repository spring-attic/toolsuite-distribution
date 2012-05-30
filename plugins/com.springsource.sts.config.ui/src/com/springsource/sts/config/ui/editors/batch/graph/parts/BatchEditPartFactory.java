/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.parts;

import org.eclipse.gef.EditPart;

import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.model.Transition;
import com.springsource.sts.config.flow.parts.AbstractConfigEditPartFactory;
import com.springsource.sts.config.flow.parts.TransitionPart;
import com.springsource.sts.config.ui.editors.batch.graph.model.BatchDiagram;
import com.springsource.sts.config.ui.editors.batch.graph.model.DecisionContainerElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.DecisionModelElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.EndModelElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.FailModelElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.FlowAnchorElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.FlowContainerElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.FlowModelElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.JobModelElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.NextModelElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.SplitContainerElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.SplitModelElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.StepContainerElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.StepModelElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.StopModelElement;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class BatchEditPartFactory extends AbstractConfigEditPartFactory {

	public BatchEditPartFactory(AbstractConfigGraphicalEditor editor) {
		super(editor);
	}

	@Override
	protected EditPart createEditPartFromModel(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof BatchDiagram) {
			part = new BatchDiagramEditPart((BatchDiagram) model);
		}
		else if (model instanceof DecisionContainerElement) {
			part = new DecisionContainerEditPart((DecisionContainerElement) model);
		}
		else if (model instanceof DecisionModelElement) {
			part = new DecisionGraphicalEditPart((DecisionModelElement) model);
		}
		else if (model instanceof EndModelElement) {
			part = new EndGraphicalEditPart((EndModelElement) model);
		}
		else if (model instanceof FailModelElement) {
			part = new FailGraphicalEditPart((FailModelElement) model);
		}
		else if (model instanceof FlowAnchorElement) {
			part = new FlowAnchorEditPart((FlowAnchorElement) model);
		}
		else if (model instanceof FlowContainerElement) {
			part = new FlowContainerEditPart((FlowContainerElement) model);
		}
		else if (model instanceof FlowModelElement) {
			part = new FlowGraphicalEditPart((FlowModelElement) model);
		}
		else if (model instanceof JobModelElement) {
			part = new JobGraphicalEditPart((JobModelElement) model);
		}
		else if (model instanceof NextModelElement) {
			part = new NextGraphicalEditPart((NextModelElement) model);
		}
		else if (model instanceof SplitContainerElement) {
			part = new SplitContainerEditPart((SplitContainerElement) model);
		}
		else if (model instanceof SplitModelElement) {
			part = new SplitGraphicalEditPart((SplitModelElement) model);
		}
		else if (model instanceof StepContainerElement) {
			part = new StepContainerEditPart((StepContainerElement) model);
		}
		else if (model instanceof StepModelElement) {
			part = new StepGraphicalEditPart((StepModelElement) model);
		}
		else if (model instanceof StopModelElement) {
			part = new StopGraphicalEditPart((StopModelElement) model);
		}
		else if (model instanceof Transition) {
			part = new TransitionPart((Transition) model);
		}
		return part;
	}

}
