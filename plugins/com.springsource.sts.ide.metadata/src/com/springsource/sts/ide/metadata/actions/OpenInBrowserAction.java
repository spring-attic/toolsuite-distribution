/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.springframework.ide.eclipse.beans.core.internal.model.BeansModelUtils;
import org.springframework.ide.eclipse.beans.core.model.IBeansModelElement;
import org.springframework.ide.eclipse.beans.core.model.IBeansProject;

import com.springsource.sts.ide.metadata.MetadataUIImages;
import com.springsource.sts.ide.metadata.ui.RequestMappingMethodToClassMap;
import com.springsource.sts.ide.metadata.ui.RequestMappingView;
import com.springsource.sts.ide.metadata.ui.RequestMappingViewLabelProvider;
import com.springsource.sts.ide.metadata.wizards.OpenRequestMappingUrlWizard;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class OpenInBrowserAction extends BaseSelectionListenerAction {

	private RequestMappingView viewPart;

	private RequestMappingViewLabelProvider labelProvider;

	private IBeansModelElement element;

	public OpenInBrowserAction(RequestMappingView viewPart,
			RequestMappingViewLabelProvider labelProvider) {
		super(Messages.OpenInBrowserAction_TITLE);
		setImageDescriptor(MetadataUIImages.DESC_OBJS_BROWSER);
		this.viewPart = viewPart;
		this.labelProvider = labelProvider;
	}

	@Override
	protected boolean updateSelection(IStructuredSelection selection) {
		element = viewPart.getInput();
		if (getProject(element) == null) {
			return false;
		}

		Object obj = selection.getFirstElement();
		if (obj instanceof RequestMappingMethodToClassMap) {
			String methodType = labelProvider.getColumnText(obj,
					RequestMappingView.COLUMN_REQUEST_METHOD);
			return "RequestMethod.GET".equalsIgnoreCase(methodType); //$NON-NLS-1$
		}
		return false;
	}

	@Override
	public void run() {
		String url = labelProvider.getColumnText(getStructuredSelection()
				.getFirstElement(), RequestMappingView.COLUMN_URL);
		if (url != null) {
			IWizard wizard = new OpenRequestMappingUrlWizard(
					(RequestMappingMethodToClassMap) getStructuredSelection()
							.getFirstElement(), labelProvider,
					getProject(element));
			Shell shell = viewPart.getSite().getShell();
			if (shell != null) {
				WizardDialog dialog = new WizardDialog(shell, wizard);
				dialog.create();
				dialog.setBlockOnOpen(true);
				dialog.open();
			}
		}
	}

	private IProject getProject(IBeansModelElement modelElement) {
		if (modelElement == null) {
			return null;
		}
		if (modelElement instanceof IBeansProject) {
			return ((IBeansProject) modelElement).getProject();
		}
		return BeansModelUtils.getParentOfClass(modelElement,
				IBeansProject.class).getProject();
	}

}
