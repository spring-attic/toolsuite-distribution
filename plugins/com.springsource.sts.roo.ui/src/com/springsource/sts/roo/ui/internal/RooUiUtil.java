/******************************************************************************************
 * Copyright (c) 2011 - 2012 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.roo.ui.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.osgi.framework.Version;
import org.springframework.ide.eclipse.core.SpringCoreUtils;

import com.springsource.sts.roo.core.RooCoreActivator;
import com.springsource.sts.roo.core.model.IRooInstall;

/**
 * Roo utility methods.
 * 
 * @author Steffen Pingel
 * @author Leo Dos Santos
 */
public class RooUiUtil {

	public static List<IProject> getAllRooProjects() {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		List<IProject> projects = new ArrayList<IProject>();
		for (IProject project : root.getProjects()) {
			if (project.isAccessible() && project.isOpen()
					&& SpringCoreUtils.hasNature(project, RooCoreActivator.NATURE_ID)) {
				projects.add(project);
			}
		}
		return projects;
	}
	
	public static boolean isRoo120OrGreater(IRooInstall install) {
		if (install != null) {
			String versionStr = install.getVersion();
			if (versionStr != null && versionStr != "<UNKNOWN VERSION>") {
				Version version;
				if (versionStr.contains(" ")) {
					int index = versionStr.indexOf(" ");
					version = Version.parseVersion(versionStr.substring(0, index));
				}
				else {
					version = Version.parseVersion(versionStr);
				}
				return version.compareTo(Version.parseVersion("1.2.0")) >= 0;
			}
		}
		return false;
	}
	
}
