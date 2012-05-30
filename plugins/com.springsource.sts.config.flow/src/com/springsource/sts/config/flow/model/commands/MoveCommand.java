/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.springsource.sts.config.flow.model.Activity;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class MoveCommand extends Command {

	private final Activity activity;

	private final Rectangle oldBounds;

	private final Rectangle newBounds;

	public MoveCommand(Activity activity, Rectangle oldBounds, Rectangle newBounds) {
		this.activity = activity;
		this.oldBounds = oldBounds;
		this.newBounds = newBounds;
	}

	@Override
	public void execute() {
		activity.modifyBounds(newBounds);
	}

	@Override
	public void undo() {
		activity.modifyBounds(oldBounds);
	}

}
