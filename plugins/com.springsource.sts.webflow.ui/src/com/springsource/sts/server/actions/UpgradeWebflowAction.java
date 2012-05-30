/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.server.actions;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.wst.xml.core.internal.provisional.format.FormatProcessorXML;
import org.springframework.core.io.FileSystemResource;
import org.springframework.ide.eclipse.webflow.core.internal.model.WebflowModelUtils;
import org.springframework.ide.eclipse.webflow.core.internal.model.WebflowModelXmlUtils;
import org.springframework.ide.eclipse.webflow.core.model.IWebflowConfig;
import org.springframework.webflow.upgrade.WebFlowUpgrader;

/**
 * Action to trigger web flow upgrader to upgrade from web flow 1.0 to web flow 2.0
 * @author Terry Denney
 * @author Steffen Pingel
 * @author Christian Dupuis
 */
public class UpgradeWebflowAction extends Action implements
		IObjectActionDelegate {

	private IFile selectedFile;

	public UpgradeWebflowAction() {
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	public void run(IAction action) {
		IPath absoluteFilePath = selectedFile.getLocation();
	
			WebFlowUpgrader webFlowUpgrader = new WebFlowUpgrader();
			String newContent = webFlowUpgrader
					.convert(new FileSystemResource(absoluteFilePath
							.toOSString()));
	
			String newFileName = absoluteFilePath.segment(absoluteFilePath
					.segmentCount() - 1);
			int pos = newFileName.indexOf(".");
			if (pos < 0) {
				newFileName += "1.0";
			} else {
				String name = newFileName.substring(0, pos);
				String extension = newFileName.substring(pos + 1);
				newFileName = name + "1.0." + extension;
			}
	
			IPath newPath = selectedFile.getProjectRelativePath()
					.removeLastSegments(1).append(newFileName);
	
			try {
				selectedFile.copy(newPath.removeFirstSegments(newPath.segmentCount()-1), false, null);
				selectedFile.setContents(new ByteArrayInputStream(newContent.getBytes()), true, true, null);
				
				new FormatProcessorXML().formatFile(selectedFile);
				IEditorPart editor = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), selectedFile);
				editor.doSave(null);
			} catch (CoreException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof TreeSelection) {
			TreeSelection treeSelection = (TreeSelection) selection;
			Object element = treeSelection.getFirstElement();
			if (element != null && element instanceof IFile) {
				IFile file = (IFile) element;
				if (WebflowModelUtils.isWebflowConfig(file)) {
					IWebflowConfig webflowConfig = WebflowModelUtils.getWebflowConfig(file);
					if (WebflowModelXmlUtils.isVersion1Flow(webflowConfig)) {
						this.selectedFile = file;
						action.setEnabled(true);
						return;
					}
				}
			}
		}
		action.setEnabled(false);
		selectedFile = null;
	}

}
