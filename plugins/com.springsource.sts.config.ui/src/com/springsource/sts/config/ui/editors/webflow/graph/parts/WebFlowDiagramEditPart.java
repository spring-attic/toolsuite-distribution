/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph.parts;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;

import com.springsource.sts.config.core.preferences.SpringConfigPreferenceConstants;
import com.springsource.sts.config.flow.parts.ActivityDiagramPart;
import com.springsource.sts.config.ui.ConfigUiPlugin;
import com.springsource.sts.config.ui.editors.webflow.graph.model.WebFlowDiagram;

/**
 * @author Leo Dos Santos
 */
public class WebFlowDiagramEditPart extends ActivityDiagramPart {

	private final IPreferenceStore uiPrefStore;

	public WebFlowDiagramEditPart(WebFlowDiagram diagram) {
		super(diagram, PositionConstants.EAST);
		uiPrefStore = ConfigUiPlugin.getDefault().getPreferenceStore();
	}

	@Override
	public void activate() {
		super.activate();
		uiPrefStore.addPropertyChangeListener(this);
	}

	@Override
	public void deactivate() {
		uiPrefStore.removePropertyChangeListener(this);
		super.deactivate();
	}

	@Override
	public WebFlowDiagram getModelElement() {
		return (WebFlowDiagram) getModel();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		if (SpringConfigPreferenceConstants.PREF_DISPLAY_ACTION_STATE.equals(event.getProperty())
				|| SpringConfigPreferenceConstants.PREF_DISPLAY_DECISION_STATE.equals(event.getProperty())
				|| SpringConfigPreferenceConstants.PREF_DISPLAY_END_STATE.equals(event.getProperty())
				|| SpringConfigPreferenceConstants.PREF_DISPLAY_SUBFLOW_STATE.equals(event.getProperty())
				|| SpringConfigPreferenceConstants.PREF_DISPLAY_VIEW_STATE.equals(event.getProperty())) {
			refreshAll();
		}
	}

}
