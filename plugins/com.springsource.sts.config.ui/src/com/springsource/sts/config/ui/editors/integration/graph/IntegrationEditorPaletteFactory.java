/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.ToolEntry;

import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.FlowCommonImages;
import com.springsource.sts.config.flow.model.ModelElementCreationFactory;
import com.springsource.sts.config.flow.model.TransitionCreationFactory;
import com.springsource.sts.config.flow.parts.AbstractConfigPaletteFactory;
import com.springsource.sts.config.ui.editors.integration.graph.model.AggregatorModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.BridgeModelElement;
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
import com.springsource.sts.config.ui.editors.integration.graph.model.InboundChannelAdapterModelElement;
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
import com.springsource.sts.config.ui.editors.integration.graph.model.PublishSubscribeChannelModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.RecipientListRouterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ResequencerModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ResourceInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.RouterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ServiceActivatorModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.SplitterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.TransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.parts.AlternateTransitionCreationFactory;

/**
 * @author Leo Dos Santos
 */
public class IntegrationEditorPaletteFactory extends AbstractConfigPaletteFactory {

	public IntegrationEditorPaletteFactory(AbstractConfigGraphicalEditor editor) {
		super(editor);
	}

	@Override
	protected List<PaletteDrawer> createComponentDrawers() {
		List<PaletteDrawer> categories = new ArrayList<PaletteDrawer>();
		categories.add(createMessagingChannelsDrawer());
		categories.add(createMessageRoutingDrawer());
		categories.add(createMessageTransformationDrawer());
		categories.add(createMessagingEndpointsDrawer());
		return categories;
	}

	@Override
	protected List<PaletteEntry> createConnectionTools() {
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();
		ToolEntry tool = new ConnectionCreationToolEntry(
				Messages.getString("IntegrationEditorPaletteFactory.CONNECTION_COMPONENT_TITLE"), Messages.getString("IntegrationEditorPaletteFactory.CONNECTION_COMPONENT_DESCRIPTION"), //$NON-NLS-1$ //$NON-NLS-2$
				new TransitionCreationFactory(), FlowCommonImages.CONNECTION_SOLID, FlowCommonImages.CONNECTION_SOLID);
		entries.add(tool);

		tool = new ConnectionCreationToolEntry(
				"mapping/recipient/wire-tap", //$NON-NLS-1$
				Messages.getString("IntegrationEditorPaletteFactory.WIRE_TAP_COMPONENT_DESCRIPTION"), new AlternateTransitionCreationFactory(), FlowCommonImages.CONNECTION_DASHED, //$NON-NLS-1$
				FlowCommonImages.CONNECTION_DASHED);
		entries.add(tool);
		return entries;
	}

	private PaletteDrawer createMessageRoutingDrawer() {
		PaletteDrawer drawer = new PaletteDrawer(
				Messages.getString("IntegrationEditorPaletteFactory.ROUTING_PALETTE_TITLE"), IntegrationImages.BADGE_SI); //$NON-NLS-1$
		drawer.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_AGGREGATOR,
				Messages.getString("IntegrationEditorPaletteFactory.AGGREGATOR_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(AggregatorModelElement.class, getDiagram()),
				IntegrationImages.AGGREGATOR_SMALL, IntegrationImages.AGGREGATOR);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_BRIDGE,
				Messages.getString("IntegrationEditorPaletteFactory.BRIDGE_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(BridgeModelElement.class, getDiagram()),
				IntegrationImages.BRIDGE_SMALL, IntegrationImages.BRIDGE);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_CONTROL_BUS,
				Messages.getString("IntegrationEditorPaletteFactory.CONTROL_BUS_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory(ControlBusModelElement.class, //$NON-NLS-1$
						getDiagram()), IntegrationImages.CONTROL_BUS_SMALL, IntegrationImages.CONTROL_BUS);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_DELAYER,
				Messages.getString("IntegrationEditorPaletteFactory.DELAYER_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(DelayerModelElement.class, getDiagram()),
				IntegrationImages.DELAYER_SMALL, IntegrationImages.DELAYER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_EXCEPTION_TYPE_ROUTER,
				Messages.getString("IntegrationEditorPaletteFactory.EXCEPTION_TYPE_ROUTER_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						ExceptionTypeRouterModelElement.class, getDiagram()), IntegrationImages.EXCEPTION_ROUTER_SMALL,
				IntegrationImages.EXCEPTION_ROUTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_FILTER,
				Messages.getString("IntegrationEditorPaletteFactory.FILTER_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(FilterModelElement.class, getDiagram()),
				IntegrationImages.FILTER_SMALL, IntegrationImages.FILTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_HEADER_FILTER,
				Messages.getString("IntegrationEditorPaletteFactory.HEADER_FILTER_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory(HeaderFilterModelElement.class, //$NON-NLS-1$
						getDiagram()), IntegrationImages.CONTENT_FILTER_SMALL, IntegrationImages.CONTENT_FILTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_HEADER_VALUE_ROUTER,
				Messages.getString("IntegrationEditorPaletteFactory.HEADER_VALUE_ROUTER_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						HeaderValueRouterModelElement.class, getDiagram()), IntegrationImages.ROUTER_SMALL,
				IntegrationImages.ROUTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_PAYLOAD_TYPE_ROUTER,
				Messages.getString("IntegrationEditorPaletteFactory.PAYLOAD_TYPE_ROUTER_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						PayloadTypeRouterModelElement.class, getDiagram()), IntegrationImages.ROUTER_SMALL,
				IntegrationImages.ROUTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_ROUTER,
				Messages.getString("IntegrationEditorPaletteFactory.ROUTER_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(RouterModelElement.class, getDiagram()),
				IntegrationImages.ROUTER_SMALL, IntegrationImages.ROUTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_RECIPIENT_LIST_ROUTER,
				Messages.getString("IntegrationEditorPaletteFactory.RECIPIENT_LIST_ROUTER_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						RecipientListRouterModelElement.class, getDiagram()), IntegrationImages.RECIPIENT_LIST_SMALL,
				IntegrationImages.RECIPIENT_LIST);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_RESEQUENCER,
				Messages.getString("IntegrationEditorPaletteFactory.RESEQUENCER_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory(ResequencerModelElement.class, //$NON-NLS-1$
						getDiagram()), IntegrationImages.RESEQUENCER_SMALL, IntegrationImages.RESEQUENCER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_SPLITTER,
				Messages.getString("IntegrationEditorPaletteFactory.SPLITTER_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(SplitterModelElement.class, getDiagram()),
				IntegrationImages.SPLITTER_SMALL, IntegrationImages.SPLITTER);
		entries.add(entry);

		drawer.addAll(entries);
		return drawer;
	}

	private PaletteDrawer createMessageTransformationDrawer() {
		PaletteDrawer drawer = new PaletteDrawer(
				Messages.getString("IntegrationEditorPaletteFactory.TRANSFORMATION_PALETTE_TITLE"), IntegrationImages.BADGE_SI); //$NON-NLS-1$
		drawer.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_CLAIM_CHECK_IN,
				Messages.getString("IntegrationEditorPaletteFactory.CLAIM_CHECK_IN_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(ClaimCheckInModelElement.class, getDiagram()),
				IntegrationImages.CLAIM_CHECK_SMALL, IntegrationImages.CLAIM_CHECK);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_CLAIM_CHECK_OUT,
				Messages.getString("IntegrationEditorPaletteFactory.CLAIM_CHECK_OUT_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory(ClaimCheckOutModelElement.class, //$NON-NLS-1$
						getDiagram()), IntegrationImages.CLAIM_CHECK_SMALL, IntegrationImages.CLAIM_CHECK);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_ENRICHER,
				Messages.getString("IntegrationEditorPaletteFactory.ENRICHER_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(EnricherModelElement.class, getDiagram()),
				IntegrationImages.ENRICHER_SMALL, IntegrationImages.ENRICHER);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_HEADER_ENRICHER,
				Messages.getString("IntegrationEditorPaletteFactory.HEADER_ENRICHER_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(HeaderEnricherModelElement.class, getDiagram()),
				IntegrationImages.ENRICHER_SMALL, IntegrationImages.ENRICHER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_JSON_TO_OBJECT_TRANSFORMER,
				Messages.getString("IntegrationEditorPaletteFactory.JSON_TO_OBJECT_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						JsonToObjectTransformerModelElement.class, getDiagram()), IntegrationImages.TRANSFORMER_SMALL,
				IntegrationImages.TRANSFORMER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_MAP_TO_OBJECT_TRANSFORMER,
				Messages.getString("IntegrationEditorPaletteFactory.MAP_TO_OBJECT_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						MapToObjectTransformerModelElement.class, getDiagram()), IntegrationImages.TRANSFORMER_SMALL,
				IntegrationImages.TRANSFORMER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_OBJECT_TO_JSON_TRANSFORMER,
				Messages.getString("IntegrationEditorPaletteFactory.OBJECT_TO_JSON_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						ObjectToJsonTransformerModelElement.class, getDiagram()), IntegrationImages.TRANSFORMER_SMALL,
				IntegrationImages.TRANSFORMER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_OBJECT_TO_MAP_TRANSFORMER,
				Messages.getString("IntegrationEditorPaletteFactory.OBJECT_TO_MAP_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						ObjectToMapTransformerModelElement.class, getDiagram()), IntegrationImages.TRANSFORMER_SMALL,
				IntegrationImages.TRANSFORMER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_OBJECT_TO_STRING_TRANSFORMER,
				Messages.getString("IntegrationEditorPaletteFactory.OBJECT_TO_STRING_TRANSFORMER_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						ObjectToStringTransformerModelElement.class, getDiagram()),
				IntegrationImages.TRANSFORMER_SMALL, IntegrationImages.TRANSFORMER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_PAYLOAD_DESERIALIZING_TRANSFORMER,
				Messages.getString("IntegrationEditorPaletteFactory.PAYLOAD_DESERIALIZING_TRANSFORMER_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						PayloadDeserializingTransformerModelElement.class, getDiagram()),
				IntegrationImages.TRANSFORMER_SMALL, IntegrationImages.TRANSFORMER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_PAYLOAD_SERIALIZING_TRANSFORMER,
				Messages.getString("IntegrationEditorPaletteFactory.PAYLOAD_SERIALIZING_TRANSFORMER_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						PayloadSerializingTransformerModelElement.class, getDiagram()),
				IntegrationImages.TRANSFORMER_SMALL, IntegrationImages.TRANSFORMER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_TRANSFORMER,
				Messages.getString("IntegrationEditorPaletteFactory.TRANSFORMER_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory(TransformerModelElement.class, //$NON-NLS-1$
						getDiagram()), IntegrationImages.TRANSFORMER_SMALL, IntegrationImages.TRANSFORMER);
		entries.add(entry);

		drawer.addAll(entries);
		return drawer;
	}

	private PaletteDrawer createMessagingChannelsDrawer() {
		PaletteDrawer drawer = new PaletteDrawer(
				Messages.getString("IntegrationEditorPaletteFactory.CHANNELS_PALETTE_TITLE"), IntegrationImages.BADGE_SI); //$NON-NLS-1$
		drawer.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_CHANNEL,
				Messages.getString("IntegrationEditorPaletteFactory.CHANNEL_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						ChannelModelElement.class, getDiagram()), IntegrationImages.CHANNEL_SMALL,
				IntegrationImages.CHANNEL);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_INBOUND_CHANNEL_ADAPTER,
				Messages.getString("IntegrationEditorPaletteFactory.INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						InboundChannelAdapterModelElement.class, getDiagram()),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_LOGGING_CHANNEL_ADAPTER,
				Messages.getString("IntegrationEditorPaletteFactory.LOGGING_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						LoggingChannelAdapterModelElement.class, getDiagram()),
				IntegrationImages.OUTBOUND_ADAPTER_SMALL, IntegrationImages.OUTBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_OUTBOUND_CHANNEL_ADAPTER,
				Messages.getString("IntegrationEditorPaletteFactory.OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						OutboundChannelAdapterModelElement.class, getDiagram()),
				IntegrationImages.OUTBOUND_ADAPTER_SMALL, IntegrationImages.OUTBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_PUBLISH_SUBSCRIBE_CHANNEL,
				Messages.getString("IntegrationEditorPaletteFactory.PUBLISH_SUBSCRIBE_CHANNEL_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						PublishSubscribeChannelModelElement.class, getDiagram()),
				IntegrationImages.PUBSUB_CHANNEL_SMALL, IntegrationImages.PUBSUB_CHANNEL);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_RESOURCE_INBOUND_CHANNEL_ADAPTER,
				Messages.getString("IntegrationEditorPaletteFactory.RESOURCE_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						ResourceInboundChannelAdapterModelElement.class, getDiagram()),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);

		drawer.addAll(entries);
		return drawer;
	}

	private PaletteDrawer createMessagingEndpointsDrawer() {
		PaletteDrawer drawer = new PaletteDrawer(
				Messages.getString("IntegrationEditorPaletteFactory.ENDPOINTS_PALETTE_TITLE"), IntegrationImages.BADGE_SI); //$NON-NLS-1$
		drawer.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_CHAIN,
				Messages.getString("IntegrationEditorPaletteFactory.CHAIN_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory(ChainModelElement.class, getDiagram()), //$NON-NLS-1$
				IntegrationImages.CHAIN_SMALL, IntegrationImages.CHAIN);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_GATEWAY,
				Messages.getString("IntegrationEditorPaletteFactory.GATEWAY_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(GatewayModelElement.class, getDiagram()),
				IntegrationImages.INBOUND_GATEWAY_SMALL, IntegrationImages.INBOUND_GATEWAY);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntegrationSchemaConstants.ELEM_SERVICE_ACTIVATOR,
				Messages.getString("IntegrationEditorPaletteFactory.SERVICE_ACTIVATOR_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
						ServiceActivatorModelElement.class, getDiagram()), IntegrationImages.SERVICE_ACTIVATOR_SMALL,
				IntegrationImages.SERVICE_ACTIVATOR);
		entries.add(entry);

		drawer.addAll(entries);
		return drawer;
	}

}
