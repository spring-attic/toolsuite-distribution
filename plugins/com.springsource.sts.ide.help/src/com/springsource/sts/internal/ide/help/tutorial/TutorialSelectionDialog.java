/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.tutorial;

import java.lang.reflect.Constructor;
import java.util.Collection;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.springframework.ide.eclipse.core.SpringCoreUtils;
import org.springsource.ide.eclipse.commons.content.core.ContentItem;

import com.springsource.sts.internal.ide.help.HelpImages;
import com.springsource.sts.internal.ide.help.HelpPlugin;

/**
 * @author Steffen Pingel
 * @author Christian Dupuis
 * @author Wesley Coelho
 * @author Leo Dos Santos
 * @author Terry Denney
 */
public class TutorialSelectionDialog extends TitleAreaDialog {

	private static final String DIALOG_TITLE = "Task-Focused Tutorial Selection";

	private static final String TITLE = "Select a task-focused tutorial";

	private static final int DIALOG_WIDTH = 350;

	private static final int DIALOG_HEIGHT = 250;

	private ContentItem selectedTutorial;

	public TutorialSelectionDialog(Shell parentShell) {
		super(parentShell);
		setTitleImage(HelpImages.getImage(HelpImages.TUTORIAL_WIZBAN));
	}

	public ContentItem getSelectedTutorial() {
		return selectedTutorial;
	}

	private void createCenterArea(Composite composite) {
		FilteredTree filteredTree = null;
		// TODO CD remove reflection hack once we drop 3.4 support
		if (SpringCoreUtils.isEclipseSameOrNewer(3, 5)) {
			try {
				Constructor<FilteredTree> ctor = FilteredTree.class.getConstructor(Composite.class, int.class,
						PatternFilter.class, boolean.class);
				filteredTree = ctor.newInstance(composite, SWT.BORDER, new PatternFilter(), true);
			}
			catch (Exception e) {
				filteredTree = new FilteredTree(composite, SWT.BORDER, new PatternFilter());
			}
		}
		else {
			filteredTree = new FilteredTree(composite, SWT.BORDER, new PatternFilter());
		}
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(filteredTree);

		TreeViewer treeViewer = filteredTree.getViewer();

		final Object root = new Object();
		final Collection<TutorialCategory> tutorialCategories = HelpPlugin.getDefault().getTutorialCategories();
		treeViewer.setContentProvider(new ITreeContentProvider() {

			public void dispose() {
			}

			public Object[] getChildren(Object item) {
				return getElements(item);
			}

			public Object[] getElements(Object item) {
				if (item == root) {
					return tutorialCategories.toArray(new TutorialCategory[0]);
				}
				else if (item instanceof TutorialCategory) {
					TutorialCategory category = (TutorialCategory) item;
					return category.getTutorials();
				}
				return new Object[0];
			}

			public Object getParent(Object element) {
				return null;
			}

			public boolean hasChildren(Object item) {
				if (item instanceof TutorialCategory) {
					return ((TutorialCategory) item).hasTutorials();
				}
				return false;
			}

			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}

		});
		treeViewer.setLabelProvider(new LabelProvider() {
			@Override
			public Image getImage(Object item) {
				if (item instanceof TutorialCategory) {
					return HelpImages.getImage(HelpImages.TUTORIAL_CATEGORY);
				}
				else if (item instanceof ContentItem) {
					return HelpImages.getImage(HelpImages.TUTORIAL);
				}
				return null;
			}

			@Override
			public String getText(Object item) {
				if (item instanceof TutorialCategory) {
					TutorialCategory category = (TutorialCategory) item;
					return category.getName();
				}
				else if (item instanceof ContentItem) {
					ContentItem tutorial = (ContentItem) item;
					return tutorial.getName();
				}
				return "";
			}
		});
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if (selection instanceof StructuredSelection && !selection.isEmpty()) {
					Object item = ((StructuredSelection) selection).getFirstElement();
					if (item instanceof ContentItem) {
						selectedTutorial = (ContentItem) item;
						setMessage(selectedTutorial.getDescription());
						getButton(IDialogConstants.OK_ID).setEnabled(true);
						return;
					}
				}
				getButton(IDialogConstants.OK_ID).setEnabled(false);
				setMessage(null);
			}
		});
		treeViewer.addOpenListener(new IOpenListener() {
			public void open(OpenEvent event) {
				okPressed();
			}
		});
		treeViewer.setSorter(new ViewerSorter());
		treeViewer.setInput(root);
		treeViewer.expandAll();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		getShell().setText(DIALOG_TITLE);
		setTitle(TITLE);

		Composite parent2 = (Composite) super.createDialogArea(parent);

		Composite composite = new Composite(parent2, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(composite);

		createCenterArea(composite);

		return parent;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(convertHorizontalDLUsToPixels(DIALOG_WIDTH), convertVerticalDLUsToPixels(DIALOG_HEIGHT));
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

}
