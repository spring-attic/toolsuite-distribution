/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.roo.ui.internal.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.springframework.ide.eclipse.core.SpringCoreUtils;

import com.springsource.sts.roo.core.RooCoreActivator;
import com.springsource.sts.roo.core.model.IRooInstall;
import com.springsource.sts.roo.ui.RooUiActivator;
import com.springsource.sts.roo.ui.internal.RooShellView;
import com.springsource.sts.roo.ui.internal.RooUiUtil;

/**
 * An action to open a Roo Shell tab for a group of projects
 * @author Christian Dupuis
 * @since 2.1.0
 */
public class RooAddOnManagerAction extends Action {

	private final RooShellView view;

	public RooAddOnManagerAction(RooShellView view) {
		super("Roo Add-on Manager", RooUiActivator.getImageDescriptor("icons/full/obj16/addon.gif"));
		this.view = view;
	}

	@Override
	public void run() {
		RooAddOnManagerActionDelegate delegate = new RooAddOnManagerActionDelegate();
		if (view.getActiveProject() != null) {
			delegate.selectionChanged(this, new StructuredSelection(view.getActiveProject()));
		}
		else {
			delegate.selectionChanged(this, new StructuredSelection(RooUiUtil.getAllRooProjects()));
		}
		delegate.run(view);
	}

}
