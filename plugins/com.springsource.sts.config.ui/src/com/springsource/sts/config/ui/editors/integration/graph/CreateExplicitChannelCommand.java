/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph;

import org.eclipse.ui.texteditor.ITextEditor;

import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.flow.model.ModelElementCreationFactory;
import com.springsource.sts.config.flow.model.StructuredActivity;
import com.springsource.sts.config.flow.model.commands.AbstractTextCommand;
import com.springsource.sts.config.ui.editors.integration.graph.model.ChannelModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.ImplicitChannelModelElement;

/**
 * @author Leo Dos Santos
 */
public class CreateExplicitChannelCommand extends AbstractTextCommand {

	private StructuredActivity parent;

	private ImplicitChannelModelElement channel;

	public CreateExplicitChannelCommand(ITextEditor textEditor) {
		super(textEditor);
	}

	@Override
	public void execute() {
		ModelElementCreationFactory factory = new ModelElementCreationFactory(ChannelModelElement.class, parent
				.getDiagram());
		ChannelModelElement child = (ChannelModelElement) factory.getNewObject();
		child.getInput().setAttribute(IntegrationSchemaConstants.ATTR_ID, channel.getName());
		parent.addChild(child);
	}

	public void setChannel(ImplicitChannelModelElement channel) {
		this.channel = channel;
	}

	public void setParent(StructuredActivity parent) {
		this.parent = parent;
	}

}
