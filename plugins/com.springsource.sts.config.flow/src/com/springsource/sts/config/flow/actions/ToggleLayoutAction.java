/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import com.springsource.sts.config.core.preferences.SpringConfigPreferenceConstants;
import com.springsource.sts.config.flow.ConfigFlowPlugin;

/**
 * @author Leo Dos Santos
 */
public class ToggleLayoutAction extends Action implements IPropertyChangeListener {

	private String message;

	private final IPreferenceStore prefStore;

	private final String LAYOUT_PATH = "icons/layout.gif"; //$NON-NLS-1$

	private final String LAYOUT_DISABLED_PATH = "icons/layout_disabled.gif"; //$NON-NLS-1$

	public ToggleLayoutAction() {
		super(Messages.ToggleLayoutAction_ACTION_NAME, Action.AS_CHECK_BOX);
		ImageDescriptor imageDesc = ConfigFlowPlugin.imageDescriptorFromPlugin(ConfigFlowPlugin.PLUGIN_ID, LAYOUT_PATH);
		ImageDescriptor disabledDesc = ConfigFlowPlugin.imageDescriptorFromPlugin(ConfigFlowPlugin.PLUGIN_ID,
				LAYOUT_DISABLED_PATH);
		setImageDescriptor(imageDesc);
		setDisabledImageDescriptor(disabledDesc);
		prefStore = ConfigFlowPlugin.getDefault().getPreferenceStore();
		prefStore.addPropertyChangeListener(this);
		setState();
	}

	public void propertyChange(PropertyChangeEvent event) {
		String property = event.getProperty();
		if (SpringConfigPreferenceConstants.PREF_MANUAL_LAYOUT.equals(property)) {
			setState();
		}
	}

	@Override
	public void run() {
		prefStore.setValue(SpringConfigPreferenceConstants.PREF_MANUAL_LAYOUT, isChecked());
	}

	private void setState() {
		boolean state = prefStore.getBoolean(SpringConfigPreferenceConstants.PREF_MANUAL_LAYOUT);
		if (state) {
			message = Messages.ToggleLayoutAction_TOOLTIP_ENABLE_AUTO_LAYOUT;
		}
		else {
			message = Messages.ToggleLayoutAction_TOOLTIP_ENABLE_MANUAL_LAYOUT;
		}
		setToolTipText(message);
		setChecked(state);
	}

}
