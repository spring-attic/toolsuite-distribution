/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.wizards;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.springsource.sts.config.core.contentassist.XmlBackedContentProposalAdapter;
import com.springsource.sts.config.core.schemas.OsgiSchemaConstants;
import com.springsource.sts.wizard.core.WizardBeanReferenceContentProposalProvider;

/**
 * @author Leo Dos Santos
 * @author Terry Denney
 * @author Christian Dupuis
 */
public class OsgiServiceWizardPage extends AbstractConfigWizardPage {

	private Text idText;

	private Text refText;

	private CheckboxTableViewer interfaceTable;

	protected OsgiServiceWizardPage(AbstractConfigWizard wizard) {
		super(wizard, "OsgiServiceWizardPage"); //$NON-NLS-1$
		setTitle(Messages.getString("OsgiServiceWizardPage.PAGE_TITLE")); //$NON-NLS-1$
		// setDescription("Create a new OSGi Service definition");
	}

	@Override
	protected void createAttributes(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		Label idLabel = new Label(container, SWT.NULL);
		idLabel.setText(OsgiSchemaConstants.ATTR_ID);

		idText = new Text(container, SWT.BORDER | SWT.SINGLE);
		idText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label refLabel = new Label(container, SWT.NULL);
		refLabel.setText(OsgiSchemaConstants.ATTR_REF);

		refText = new Text(container, SWT.BORDER | SWT.SINGLE);
		refText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label interfaceLabel = new Label(container, SWT.NULL);
		interfaceLabel.setText(OsgiSchemaConstants.ATTR_INTERFACE);

		interfaceTable = CheckboxTableViewer.newCheckList(container, SWT.BORDER);
		interfaceTable.setContentProvider(new InterfaceTableContentProvider(getResourceFile(), getDomDocument()));
		interfaceTable.setLabelProvider(new JavaElementLabelProvider());
		interfaceTable.setInput(refText);

		GC gc = new GC(interfaceLabel);
		FontMetrics fm = gc.getFontMetrics();
		int height = 5 * fm.getHeight();
		gc.dispose();

		GridData data = new GridData(GridData.FILL_BOTH);
		data.heightHint = interfaceTable.getTable().computeSize(SWT.DEFAULT, height).y;
		interfaceTable.getTable().setLayoutData(data);

		refText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				interfaceTable.refresh();
			}
		});
		setControl(container);
	}

	public String getId() {
		return idText.getText();
	}

	public List<Object> getInterfaces() {
		return Arrays.asList(interfaceTable.getCheckedElements());
	}

	public String getRef() {
		return refText.getText();
	}

	@Override
	protected void hookContentProposalAdapters() {
		new XmlBackedContentProposalAdapter(refText, new TextContentAdapter(),
				new WizardBeanReferenceContentProposalProvider(getInput(), OsgiSchemaConstants.ATTR_REF,
						getResourceFile(), getDomDocument(), true));
	}

}
