/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.navigator;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.part.FileEditorInput;
import org.springframework.ide.eclipse.ui.SpringUIUtils;
import org.springframework.ide.eclipse.webflow.core.internal.model.WebflowModelUtils;
import org.springframework.ide.eclipse.webflow.core.model.IWebflowConfig;
import org.springframework.ide.eclipse.webflow.ui.navigator.actions.OpenWebflowGraphAction;

import com.springsource.sts.config.core.schemas.WebFlowSchemaConstants;
import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.ui.editors.AbstractConfigEditor;
import com.springsource.sts.config.ui.editors.SpringWebFlowEditor;

/**
 * @author Leo Dos Santos
 * @since 2.0
 */
public class StsOpenWebFlowGraphAction extends OpenWebflowGraphAction {

	private IFile file;

	public StsOpenWebFlowGraphAction(ICommonActionExtensionSite actionSite) {
		super(actionSite);
	}

	@Override
	public boolean isEnabled(IStructuredSelection selection) {
		if (selection.size() == 1) {
			Object sElement = selection.getFirstElement();
			if (sElement instanceof IWebflowConfig) {
				file = ((IWebflowConfig) sElement).getResource();
				return true;
			}
			else if (sElement instanceof IFile) {
				if (WebflowModelUtils.isWebflowConfig((IFile) sElement)) {
					file = (IFile) sElement;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void run() {
		IEditorInput input = new FileEditorInput(file);
		IEditorPart part = SpringUIUtils.openInEditor(input, SpringWebFlowEditor.ID_EDITOR);
		if (part instanceof AbstractConfigEditor) {
			AbstractConfigEditor cEditor = (AbstractConfigEditor) part;
			AbstractConfigGraphicalEditor graph = cEditor.getGraphicalEditorForUri(WebFlowSchemaConstants.URI);
			if (graph != null) {
				cEditor.setActiveEditor(graph);
			}
		}
	}

}
