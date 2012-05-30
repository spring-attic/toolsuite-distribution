/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.maven.internal.ui;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.springsource.sts.maven.internal.legacyconversion.LegacyProjectsJob;

/**
 * Convert all legacy maven projects in the workspace
 * 
 * @author Andrew Eisenberg
 * @since 2.8.0
 */
public class ConvertLegacyProjectAction implements IObjectActionDelegate {

    public void run(IAction action) {
        LegacyProjectsJob job = new LegacyProjectsJob(true, false);
        job.schedule();
    }

    public void selectionChanged(IAction action, ISelection selection) {
        // we don't care about the actual selection
    }

    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

}
