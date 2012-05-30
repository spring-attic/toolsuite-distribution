/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class ParallelActivityFigure extends SubgraphFigure {

	protected boolean selected;

	/**
	 * @param header
	 * @param footer
	 */
	public ParallelActivityFigure(int direction) {
		super(new Label(""), new Label(""), direction); //$NON-NLS-1$ //$NON-NLS-2$		
		setBorder(new MarginBorder(3, 5, 3, 0));
		setOpaque(true);
	}

	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle r = getBounds();
		g.setBackgroundColor(ColorConstants.button);
		if (selected) {
			g.setBackgroundColor(ColorConstants.menuBackgroundSelected);
			g.setForegroundColor(ColorConstants.menuForegroundSelected);
		}

		g.fillRectangle(r.x, r.y, 3, r.height);
		g.fillRectangle(r.right() - 3, r.y, 3, r.height);
		g.fillRectangle(r.x, r.bottom() - 18, r.width, 18);
		g.fillRectangle(r.x, r.y, r.width, 18);
	}

	@Override
	public void setSelected(boolean selected) {
		if (this.selected == selected) {
			return;
		}
		this.selected = selected;
		if (!selected) {
			getHeader().setForegroundColor(null);
			getFooter().setForegroundColor(null);
		}
		else {
			getHeader().setForegroundColor(ColorConstants.menuForegroundSelected);
			getFooter().setForegroundColor(ColorConstants.menuForegroundSelected);
		}
		repaint();
	}

}
