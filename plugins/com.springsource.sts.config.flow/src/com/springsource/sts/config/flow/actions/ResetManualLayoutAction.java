/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.actions;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.springsource.sts.config.core.preferences.SpringConfigPreferenceConstants;
import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.ConfigFlowPlugin;

/**
 * @author Leo Dos Santos
 */
public class ResetManualLayoutAction extends SelectionAction implements IPropertyChangeListener {

	public static String RESET_LAYOUT_ID = "ResetLayout"; //$NON-NLS-1$

	private final IPreferenceStore prefStore;

	public ResetManualLayoutAction(IWorkbenchPart part) {
		super(part);
		setId(RESET_LAYOUT_ID);
		setText(Messages.ResetManualLayoutAction_RESET_LAYOUT_ACTION_LABEL);
		prefStore = ConfigFlowPlugin.getDefault().getPreferenceStore();
		prefStore.addPropertyChangeListener(this);
	}

	@Override
	protected boolean calculateEnabled() {
		return prefStore.getBoolean(SpringConfigPreferenceConstants.PREF_MANUAL_LAYOUT);
	}

	public void propertyChange(PropertyChangeEvent event) {
		String property = event.getProperty();
		if (SpringConfigPreferenceConstants.PREF_MANUAL_LAYOUT.equals(property)) {
			setEnabled(calculateEnabled());
		}
	}

	@Override
	public void run() {
		IWorkbenchPart editor = getWorkbenchPart();
		if (editor instanceof AbstractConfigGraphicalEditor) {
			AbstractConfigGraphicalEditor graph = (AbstractConfigGraphicalEditor) editor;
			String uri = graph.getNamespaceUri();
			prefStore.firePropertyChangeEvent(SpringConfigPreferenceConstants.PROP_RESET_LAYOUT, null, uri);
		}
	}

}
