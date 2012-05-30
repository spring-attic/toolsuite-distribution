/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.springsource.sts.config.core.preferences.SpringConfigPreferenceConstants;
import com.springsource.sts.config.ui.ConfigUiPlugin;

/**
 * @author Leo Dos Santos
 */
public class ToggleActionStateAction extends SelectionAction implements IPropertyChangeListener {

	public static String ID_ACTION = SpringConfigPreferenceConstants.PREF_DISPLAY_ACTION_STATE;

	private final IPreferenceStore prefStore;

	public ToggleActionStateAction(IWorkbenchPart part) {
		super(part, Action.AS_CHECK_BOX);
		setId(ID_ACTION);
		setText(Messages.ToggleActionStateAction_ACTION_LABEL);
		prefStore = ConfigUiPlugin.getDefault().getPreferenceStore();
		prefStore.addPropertyChangeListener(this);
		setState();
	}

	@Override
	protected boolean calculateEnabled() {
		return true;
	}

	@Override
	public void dispose() {
		prefStore.removePropertyChangeListener(this);
		super.dispose();
	}

	public void propertyChange(PropertyChangeEvent event) {
		if (ID_ACTION.equals(event.getProperty())) {
			setState();
		}
	}

	@Override
	public void run() {
		prefStore.setValue(ID_ACTION, isChecked());
	}

	private void setState() {
		setChecked(prefStore.getBoolean(ID_ACTION));
	}

}
