/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.wizard.listeners.PropertiesPageShownListener;

/**
 * Wizard dialog for Bean Wizard. Use factory create methods to create these
 * dialogs to ensure Bean Wizard to work properly.
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
@SuppressWarnings("restriction")
public class BeanWizardDialog extends WizardDialog {

	public static BeanWizardDialog createBeanWizardDialog(Shell shell) {
		return createBeanWizardDialog(shell, null, true);
	}

	public static BeanWizardDialog createBeanWizardDialog(Shell shell, IFile beanFile, boolean enabledFileBrowsing) {
		BeanWizard wizard = new BeanWizard(beanFile, enabledFileBrowsing);
		return createDialog(shell, wizard);
	}

	public static WizardDialog createBeanWizardDialog(Shell shell, IFile file, boolean enabledFileBrowsing,
			String qualifiedTypeName) {
		BeanWizard wizard = new BeanWizard(file, enabledFileBrowsing);
		IDOMElement newBean = wizard.getNewBean();
		newBean.setAttribute(BeansSchemaConstants.ATTR_CLASS, qualifiedTypeName);
		return createDialog(shell, wizard);
	}

	private static BeanWizardDialog createDialog(Shell shell, BeanWizard wizard) {
		BeanWizardDialog dialog = new BeanWizardDialog(shell, wizard);
		dialog.addPageChangedListener(new PropertiesPageShownListener(wizard));
		return dialog;
	}

	public static BeanWizardDialog createModifyBeanWizardDialog(Shell shell, IFile beanFile, IDOMElement existingNode) {
		BeanWizard wizard = new BeanWizard(existingNode, beanFile);
		return createDialog(shell, wizard);
	}

	private final BeanWizard wizard;

	private BeanWizardDialog(Shell shell, BeanWizard wizard) {
		super(shell, wizard);
		this.wizard = wizard;
	}

	public IDOMElement getNewBean() {
		return wizard.getNewBean();
	}

}
