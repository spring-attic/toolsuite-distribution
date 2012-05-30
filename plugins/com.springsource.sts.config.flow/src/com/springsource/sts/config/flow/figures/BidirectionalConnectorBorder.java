/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * @author Leo Dos Santos
 */
public class BidirectionalConnectorBorder extends ConnectorBorder {

	private final boolean leftSide;

	public BidirectionalConnectorBorder(int direction, int inputs, int outputs, boolean leftSide) {
		super(direction, inputs, outputs);
		this.leftSide = leftSide;
	}

	@Override
	protected void drawAnchors(Graphics graphics, Rectangle rect) {
		Color foreground = graphics.getForegroundColor();
		Color background = graphics.getBackgroundColor();
		graphics.setBackgroundColor(ColorConstants.gray);
		int capacity = inputCapacity + outputCapacity;
		PointList pointlist;
		if (direction == PositionConstants.EAST) {
			int y1;
			int x1 = rect.x;
			int end;
			int height = rect.height;
			if (leftSide) {
				end = x1;
				pointlist = connector;
			}
			else {
				end = x1 + rect.width;
				pointlist = bottomConnector;
			}
			for (int i = 0; i < capacity; i++) {
				y1 = rect.y + (2 * i + 1) * height / (capacity * 2);
				graphics.setForegroundColor(ColorConstants.gray);
				pointlist.translate(end, y1);
				graphics.fillPolygon(pointlist);
				graphics.drawPolygon(pointlist);
				pointlist.translate(-end, -y1);
			}
		}
		else {
			int x1;
			int y1 = rect.y;
			int width = rect.width;
			int bottom;
			if (leftSide) {
				bottom = y1;
				pointlist = connector;
			}
			else {
				bottom = y1 + rect.height;
				pointlist = bottomConnector;
			}
			for (int i = 0; i < capacity; i++) {
				x1 = rect.x + (2 * i + 1) * width / (capacity * 2);
				graphics.setForegroundColor(ColorConstants.gray);
				pointlist.translate(x1, bottom);
				graphics.fillPolygon(pointlist);
				graphics.drawPolygon(pointlist);
				pointlist.translate(-x1, -bottom);
			}
		}
		graphics.setForegroundColor(foreground);
		graphics.setBackgroundColor(background);
	}

}
