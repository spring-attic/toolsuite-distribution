/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.roo.ui.internal.wizard;

import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.springsource.ide.eclipse.commons.frameworks.core.internal.commands.IFrameworkCommand;
import org.springsource.ide.eclipse.commons.frameworks.ui.internal.wizard.GenericCommandWizard;

import com.springsource.sts.roo.core.commands.RooCommandUtils;
import com.springsource.sts.roo.ui.internal.RooShellTab;

/**
 * Roo Command Wizard entry point.
 * @author Christian Dupuis
 * @since 2.5.0
 */
public class RooCommandWizard extends GenericCommandWizard {

	public static final String WIZARD_TITLE = "Roo Command Wizard";
	public static final String WIZARD_IMAGE_LOCATION = "platform:/plugin/com.springsource.sts.roo.ui/icons/full/wizban/roo_wizban.png";

	private RooShellTab tab;
	
	public RooCommandWizard(Collection<IProject> projects,
			IFrameworkCommand command, RooShellTab tab) {
		super(command, WIZARD_TITLE, null, WIZARD_IMAGE_LOCATION, projects);
		this.tab = tab;
	}
	
	@Override
	protected void executeCommand(IFrameworkCommand command) {
		tab.executeCommand(RooCommandUtils.constructCommandString(command));
	}
}
