/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.mylyn.commons.core.ICoreRunnable;
import org.eclipse.mylyn.commons.ui.CommonImages;
import org.eclipse.mylyn.commons.ui.CommonUiUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.ScrolledPageBook;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.springsource.ide.eclipse.commons.content.core.ContentItem;
import org.springsource.ide.eclipse.commons.content.core.ContentPlugin;
import org.springsource.ide.eclipse.commons.core.StatusHandler;
import org.springsource.ide.eclipse.commons.ui.StsUiImages;
import org.springsource.ide.eclipse.commons.ui.UiStatusHandler;
import org.springsource.ide.eclipse.dashboard.ui.AbstractDashboardPart;

import com.springsource.sts.ide.help.TutorialUtils;
import com.springsource.sts.internal.ide.help.tutorial.OpenTutorialSelectionDialogAction;
import com.springsource.sts.internal.ide.help.tutorial.TutorialCategory;

/**
 * @author Steffen Pingel
 * @author Christian Dupuis
 * @author Terry Denney
 */
public class TutorialDashboardPart extends AbstractDashboardPart {

	private Composite composite;

	/**
	 * Stores a list of expanded category names to retain the expansion state on
	 * refresh.
	 */
	private List<String> expandedCategories;

	private final PropertyChangeListener tutorialListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					if (getManagedForm().getForm() != null && !getManagedForm().getForm().isDisposed()) {
						refresh();
					}
				}
			});
		}
	};

	@Override
	public Control createPartContent(Composite parent) {
		FormToolkit toolkit = getToolkit();
		Section section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE);
		section.setText("Tutorials");
		GridDataFactory.fillDefaults().grab(false, true).applyTo(section);

		createToolBar(toolkit, section);

		ScrolledPageBook pageBook = toolkit.createPageBook(section, SWT.V_SCROLL);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(pageBook);

		composite = toolkit.createComposite(pageBook);
		composite.setLayout(new GridLayout());

		section.setClient(pageBook);

		createTutorialLinks();
		ContentPlugin.getDefault().getManager().addListener(tutorialListener);

		pageBook.setContent(composite);
		return section;
	}

	private ToolBarManager createToolBar(FormToolkit toolkit, Section section) {
		Composite headerComposite = toolkit.createComposite(section);
		headerComposite.setBackground(null);

		RowLayout rowLayout = new RowLayout();
		rowLayout.marginTop = 0;
		rowLayout.marginBottom = 0;
		headerComposite.setLayout(rowLayout);

		ToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT);
		toolBarManager.createControl(headerComposite);
		section.setTextClient(headerComposite);

		toolBarManager.add(new Action("Open Tutorials", StsUiImages.CHEATSHEET) {
			@Override
			public void run() {
				OpenTutorialSelectionDialogAction dialogAction = new OpenTutorialSelectionDialogAction();
				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

				if (window != null) {
					dialogAction.init(window);
					dialogAction.run();
				}
			}
		});

		toolBarManager.add(new Action("Refresh", CommonImages.REFRESH) {
			@Override
			public void run() {
				final IStatus[] result = new IStatus[1];
				try {
					CommonUiUtil.run(PlatformUI.getWorkbench().getProgressService(), new ICoreRunnable() {
						public void run(IProgressMonitor monitor) throws CoreException {
							result[0] = ContentPlugin.getDefault().getManager().refresh(monitor);
						}
					});
				}
				catch (CoreException e) {
					StatusHandler.log(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID,
							"Unexpected error while refreshing tutorials and sample projects", e));
				}
				catch (OperationCanceledException e) {
					// ignore
				}

				if (result[0] != null && !result[0].isOK()) {
					MultiStatus error = new MultiStatus(HelpPlugin.PLUGIN_ID, 0,
							"Unexpected error while refreshing tutorials and sample projects", null);
					error.addAll(result[0]);
					if (result[0].getSeverity() == IStatus.ERROR) {
						UiStatusHandler.logAndDisplay(error);
					}
					else {
						StatusHandler.log(error);
					}
				}

				// no need to refresh, the tutorialListener will trigger the
				// update
			}

		});

		toolBarManager.update(true);
		return toolBarManager;
	}

	private void createTutorialLinks() {
		FormToolkit toolkit = getToolkit();

		TutorialCategory[] categories = HelpPlugin.getDefault().getTutorialCategories()
				.toArray(new TutorialCategory[0]);
		Arrays.sort(categories, new Comparator<TutorialCategory>() {
			public int compare(TutorialCategory o1, TutorialCategory o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (TutorialCategory tutorialCategory : categories) {
			ContentItem[] tutorials = tutorialCategory.getTutorials();
			if (tutorials.length > 0) {
				Arrays.sort(tutorials, new Comparator<ContentItem>() {
					public int compare(ContentItem o1, ContentItem o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});

				int style = ExpandableComposite.TWISTIE;
				if (expandedCategories != null && expandedCategories.contains(tutorialCategory.getName())) {
					style |= ExpandableComposite.EXPANDED;
				}
				Section exp = toolkit.createSection(composite, style);
				exp.setText(tutorialCategory.getName());

				Composite expClient = toolkit.createComposite(exp);
				expClient.setLayout(new TableWrapLayout());
				exp.setClient(expClient);

				for (final ContentItem tutorial : tutorials) {
					ImageHyperlink hyperlink = toolkit.createImageHyperlink(expClient, SWT.WRAP);
					hyperlink.setText(tutorial.getName());
					hyperlink.setImage(getImage(tutorial));
					hyperlink.addHyperlinkListener(new HyperlinkAdapter() {
						@Override
						public void linkActivated(HyperlinkEvent e) {
							TutorialUtils.openTutorial(tutorial.getId());
						}
					});
				}
			}
		}
	}

	@Override
	public void dispose() {
		ContentPlugin.getDefault().getManager().removeListener(tutorialListener);
		super.dispose();
	}

	private Image getImage(ContentItem tutorial) {
		if (tutorial.needsDownload()) {
			return StsUiImages.getImage(StsUiImages.DOWNLOAD_OVERLAY);
		}
		return null;
	}

	@Override
	public void refresh() {
		super.refresh();

		if (composite == null || composite.isDisposed()) {
			return;
		}
		try {
			composite.setRedraw(false);
			expandedCategories = new ArrayList<String>();
			Control[] children = composite.getChildren();
			for (Control child : children) {
				if (child instanceof ExpandableComposite && ((ExpandableComposite) child).isExpanded()) {
					expandedCategories.add(((ExpandableComposite) child).getText());
				}
				child.dispose();
			}
			createTutorialLinks();
		}
		finally {
			expandedCategories = null;
			composite.layout(true, true);
			composite.setRedraw(true);
		}
	}

}
