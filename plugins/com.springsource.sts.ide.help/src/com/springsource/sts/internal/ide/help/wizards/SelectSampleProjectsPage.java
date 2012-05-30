/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.wizards;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.springsource.ide.eclipse.commons.content.core.ContentItem;
import org.springsource.ide.eclipse.commons.content.core.ContentManager;
import org.springsource.ide.eclipse.commons.content.core.ContentPlugin;


/**
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @author Terry Denney
 */
public class SelectSampleProjectsPage extends WizardPage {

	private CheckboxTableViewer sampleProjectsViewer;

	public SelectSampleProjectsPage() {
		super("selectSampleProjectsPage");

		setTitle("Import Sample Projects");
		setDescription("Select projects to import.");
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);

		// TODO allow single selection only?
		sampleProjectsViewer = CheckboxTableViewer.newCheckList(composite, SWT.TOP | SWT.BORDER);
		sampleProjectsViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		sampleProjectsViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((ContentItem) element).getName();
			}
		});

		sampleProjectsViewer.setContentProvider(new IStructuredContentProvider() {
			public void dispose() {
			}

			public Object[] getElements(Object parent) {
				return ContentPlugin.getDefault().getManager().getItemsByKind(ContentManager.KIND_SAMPLE).toArray();
			}

			public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			}
		});

		sampleProjectsViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				getContainer().updateButtons();
			}
		});

		sampleProjectsViewer.setSorter(new ViewerSorter());
		sampleProjectsViewer.setInput(new Object());

		setControl(composite);
	}

	public ContentItem[] getSelectedProjects() {
		Object[] checkedProjects = sampleProjectsViewer.getCheckedElements();
		ContentItem[] sampleProjects = new ContentItem[checkedProjects.length];
		System.arraycopy(checkedProjects, 0, sampleProjects, 0, sampleProjects.length);
		return sampleProjects;
	}

	@Override
	public boolean isPageComplete() {
		return sampleProjectsViewer.getCheckedElements().length > 0;
	}
}
