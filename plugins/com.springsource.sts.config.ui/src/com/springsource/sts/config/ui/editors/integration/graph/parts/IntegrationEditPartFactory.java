/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.gef.EditPart;

import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.model.Transition;
import com.springsource.sts.config.flow.parts.AbstractConfigEditPartFactory;
import com.springsource.sts.config.flow.parts.BorderedActivityPart;
import com.springsource.sts.config.flow.parts.TransitionPart;
import com.springsource.sts.config.ui.editors.integration.graph.model.AggregatorModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.AlternateTransition;
import com.springsource.sts.config.ui.editors.integration.graph.model.BridgeModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ChainContainerElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ChainModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ChannelModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ClaimCheckInModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ClaimCheckOutModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ControlBusModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.DelayerModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.EnricherModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ExceptionTypeRouterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.FilterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.GatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.HeaderEnricherModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.HeaderFilterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.HeaderValueRouterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ImplicitChannelModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ImplicitTransition;
import com.springsource.sts.config.ui.editors.integration.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.IntegrationDiagram;
import com.springsource.sts.config.ui.editors.integration.graph.model.JsonToObjectTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.LoggingChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.MapToObjectTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ObjectToJsonTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ObjectToMapTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ObjectToStringTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.OutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.PayloadDeserializingTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.PayloadSerializingTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.PayloadTypeRouterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.PlaceholderModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.PublishSubscribeChannelModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.RecipientListRouterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ResequencerModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ResourceInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.RouterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ServiceActivatorModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.SplitterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.TransformerModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntegrationEditPartFactory extends AbstractConfigEditPartFactory {

	public IntegrationEditPartFactory(AbstractConfigGraphicalEditor editor) {
		super(editor);
	}

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = super.createEditPart(context, model);
		if (context instanceof ChainContainerEditPart && part instanceof BorderedActivityPart) {
			BorderedActivityPart borderedPart = (BorderedActivityPart) part;
			borderedPart.setHasAnchors(false);
		}
		return part;
	}

	@Override
	protected EditPart createEditPartFromModel(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof IntegrationDiagram) {
			part = new IntegrationDiagramEditPart((IntegrationDiagram) model);
		}
		else if (model instanceof AggregatorModelElement) {
			part = new AggregatorGraphicalEditPart((AggregatorModelElement) model);
		}
		else if (model instanceof BridgeModelElement) {
			part = new BridgeGraphicalEditPart((BridgeModelElement) model);
		}
		else if (model instanceof ChainContainerElement) {
			part = new ChainContainerEditPart((ChainContainerElement) model);
		}
		else if (model instanceof ChainModelElement) {
			part = new ChainGraphicalEditPart((ChainModelElement) model);
		}
		else if (model instanceof ChannelModelElement) {
			part = new ChannelGraphicalEditPart((ChannelModelElement) model);
		}
		else if (model instanceof ClaimCheckInModelElement) {
			part = new ClaimCheckInGraphicalEditPart((ClaimCheckInModelElement) model);
		}
		else if (model instanceof ClaimCheckOutModelElement) {
			part = new ClaimCheckOutGraphicalEditPart((ClaimCheckOutModelElement) model);
		}
		else if (model instanceof ControlBusModelElement) {
			part = new ControlBusGraphicalEditPart((ControlBusModelElement) model);
		}
		else if (model instanceof DelayerModelElement) {
			part = new DelayerGraphicalEditPart((DelayerModelElement) model);
		}
		else if (model instanceof EnricherModelElement) {
			part = new EnricherGraphicalEditPart((EnricherModelElement) model);
		}
		else if (model instanceof ExceptionTypeRouterModelElement) {
			part = new ExceptionTypeRouterGraphicalEditPart((ExceptionTypeRouterModelElement) model);
		}
		else if (model instanceof FilterModelElement) {
			part = new FilterGraphicalEditPart((FilterModelElement) model);
		}
		else if (model instanceof GatewayModelElement) {
			part = new GatewayGraphicalEditPart((GatewayModelElement) model);
		}
		else if (model instanceof HeaderEnricherModelElement) {
			part = new HeaderEnricherGraphicalEditPart((HeaderEnricherModelElement) model);
		}
		else if (model instanceof HeaderFilterModelElement) {
			part = new HeaderFilterGraphicalEditPart((HeaderFilterModelElement) model);
		}
		else if (model instanceof HeaderValueRouterModelElement) {
			part = new HeaderValueRouterGraphicalEditPart((HeaderValueRouterModelElement) model);
		}
		else if (model instanceof ImplicitChannelModelElement) {
			part = new ImplicitChannelGraphicalEditPart((ImplicitChannelModelElement) model);
		}
		else if (model instanceof InboundChannelAdapterModelElement) {
			part = new InboundChannelAdapterGraphicalEditPart((InboundChannelAdapterModelElement) model);
		}
		else if (model instanceof JsonToObjectTransformerModelElement) {
			part = new JsonToObjectTransformerGraphicalEditPart((JsonToObjectTransformerModelElement) model);
		}
		else if (model instanceof LoggingChannelAdapterModelElement) {
			part = new LoggingChannelAdapterGraphicalEditPart((LoggingChannelAdapterModelElement) model);
		}
		else if (model instanceof MapToObjectTransformerModelElement) {
			part = new MapToObjectTransformerGraphicalEditPart((MapToObjectTransformerModelElement) model);
		}
		else if (model instanceof ObjectToJsonTransformerModelElement) {
			part = new ObjectToJsonTransformerGraphicalEditPart((ObjectToJsonTransformerModelElement) model);
		}
		else if (model instanceof ObjectToMapTransformerModelElement) {
			part = new ObjectToMapTransformerGraphicalEditPart((ObjectToMapTransformerModelElement) model);
		}
		else if (model instanceof ObjectToStringTransformerModelElement) {
			part = new ObjectToStringTransformerGraphicalEditPart((ObjectToStringTransformerModelElement) model);
		}
		else if (model instanceof OutboundChannelAdapterModelElement) {
			part = new OutboundChannelAdapterGraphicalEditPart((OutboundChannelAdapterModelElement) model);
		}
		else if (model instanceof PayloadDeserializingTransformerModelElement) {
			part = new PayloadDeserializingTransformerGraphicalEditPart(
					(PayloadDeserializingTransformerModelElement) model);
		}
		else if (model instanceof PayloadSerializingTransformerModelElement) {
			part = new PayloadSerializingTransformerGraphicalEditPart((PayloadSerializingTransformerModelElement) model);
		}
		else if (model instanceof PayloadTypeRouterModelElement) {
			part = new PayloadTypeRouterGraphicalEditPart((PayloadTypeRouterModelElement) model);
		}
		else if (model instanceof PlaceholderModelElement) {
			part = new PlaceholderGraphicalEditPart((PlaceholderModelElement) model);
		}
		else if (model instanceof PublishSubscribeChannelModelElement) {
			part = new PublishSubscribeChannelGraphicalEditPart((PublishSubscribeChannelModelElement) model);
		}
		else if (model instanceof RecipientListRouterModelElement) {
			part = new RecipientListRouterGraphicalEditPart((RecipientListRouterModelElement) model);
		}
		else if (model instanceof ResequencerModelElement) {
			part = new ResequencerGraphicalEditPart((ResequencerModelElement) model);
		}
		else if (model instanceof ResourceInboundChannelAdapterModelElement) {
			part = new ResourceInboundChannelAdapterGraphicalEditPart((ResourceInboundChannelAdapterModelElement) model);
		}
		else if (model instanceof RouterModelElement) {
			part = new RouterGraphicalEditPart((RouterModelElement) model);
		}
		else if (model instanceof ServiceActivatorModelElement) {
			part = new ServiceActivatorGraphicalEditPart((ServiceActivatorModelElement) model);
		}
		else if (model instanceof SplitterModelElement) {
			part = new SplitterGraphicalEditPart((SplitterModelElement) model);
		}
		else if (model instanceof TransformerModelElement) {
			part = new TransformerGraphicalEditPart((TransformerModelElement) model);
		}
		else if (model instanceof AlternateTransition) {
			part = new AlternateTransitionPart((AlternateTransition) model);
		}
		else if (model instanceof ImplicitTransition) {
			part = new ImplicitTransitionPart((ImplicitTransition) model);
		}
		else if (model instanceof Transition) {
			part = new TransitionPart((Transition) model);
		}
		return part;
	}

}
