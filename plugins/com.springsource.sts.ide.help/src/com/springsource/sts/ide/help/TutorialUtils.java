/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.help;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.mylyn.commons.core.CoreUtil;
import org.eclipse.mylyn.internal.tasks.ui.editors.AttachmentSizeFormatter;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.eclipse.ui.cheatsheets.OpenCheatSheetAction;
import org.eclipse.ui.internal.cheatsheets.views.CheatSheetManager;
import org.eclipse.ui.internal.cheatsheets.views.CheatSheetView;
import org.eclipse.ui.internal.cheatsheets.views.CheatSheetViewer;
import org.eclipse.ui.part.ViewPart;
import org.springsource.ide.eclipse.commons.content.core.ContentItem;
import org.springsource.ide.eclipse.commons.content.core.ContentPlugin;
import org.springsource.ide.eclipse.commons.content.core.ContentManager.DownloadJob;
import org.springsource.ide.eclipse.commons.content.core.util.IContentConstants;
import org.springsource.ide.eclipse.commons.core.StatusHandler;
import org.springsource.ide.eclipse.commons.ui.UiStatusHandler;

import com.springsource.sts.internal.ide.help.HelpPlugin;
import com.springsource.sts.internal.ide.help.SampleProjectData;
import com.springsource.sts.internal.ide.help.SampleProjectImporter;
import com.springsource.sts.internal.ide.help.SampleProjectData.WorkspaceProject;

/**
 * Helper methods for invoking task-focused tutorials.
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @author Terry Denney
 */
public class TutorialUtils {

	public static final String TASK_FOCUSED_TUTORIALS = "Task-Focused Tutorials";

	private static final String CHEAT_SHEED_VIEWER_ID = "org.eclipse.ui.cheatsheets.views.CheatSheetView";

	public static final String ITEM_ID_TOMCAT = "com.springsource.sts.org.apache.tomcat";

	private static void download(final Shell shell, final ContentItem item, final Runnable runnable) {
		Job job = ContentPlugin.getDefault().getManager().createDownloadJob(item);
		job.addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(IJobChangeEvent event) {
				if (event.getResult() != null && event.getResult().isOK() && !shell.isDisposed()) {
					Display.getDefault().asyncExec(runnable);
				}
			}
		});
		PlatformUI.getWorkbench().getProgressService().showInDialog(shell, job);
		job.schedule();
	}

	// public static void downloadTomcat(IProgressMonitor monitor) throws
	// CoreException {
	// SubMonitor progress = SubMonitor.convert(monitor, 100);
	//
	// ContentManager manager = ContentPlugin.getDefault().getManager();
	// ContentItem item = manager.getItem(ITEM_ID_TOMCAT);
	// if (item != null && item.needsDownload()) {
	// final ContentItem finalItem = item;
	// final boolean[] response = new boolean[1];
	// Display.getDefault().syncExec(new Runnable() {
	// public void run() {
	// response[0] = promptForDownload(UiUtil.getShell(), finalItem);
	// }
	// });
	// if (response[0]) {
	// DownloadJob job = manager.createDownloadJob(item);
	// job.run(progress.newChild(100));
	//
	// // re-get sample project since it may have changed
	// String itemId = item.getId();
	// item = manager.getItem(itemId);
	// }
	//
	// // reset
	// TomcatUtil.setArchivePath(null);
	// }
	// if (item == null || !item.isLocal()) {
	// throw new CoreException(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID,
	// "Download of Apache Tomcat failed"));
	// }
	//
	// if (TomcatUtil.getArchivePath() == null) {
	// File[] files = manager.getInstallDirectory(item).listFiles();
	// if (files != null) {
	// for (File file : files) {
	// if (file.getName().startsWith("apache-tomcat") &&
	// file.getName().endsWith(".zip")) {
	// try {
	// TomcatUtil.setArchivePath(file.toURI().toURL());
	// }
	// catch (MalformedURLException e) {
	// StatusHandler.log(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID,
	// "Failed to configure Apache Tomcat directory", e));
	// }
	// break;
	// }
	// }
	// }
	// }
	// }

	public static ICheatSheetManager getCheatSheetManager(CheatSheetViewer viewer) {
		try {
			Method method = CheatSheetViewer.class.getDeclaredMethod("getManager");
			method.setAccessible(true);
			return (ICheatSheetManager) method.invoke(viewer);
		}
		catch (Throwable e) {
			StatusHandler
					.log(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, "Failed to access cheat sheet manager", e));
			return null;
		}
	}

	public static CheatSheetView getCheatSheetView(IWorkbenchWindow window) {
		IWorkbenchPage activePage = window.getActivePage();
		if (activePage != null) {
			IViewReference[] views = activePage.getViewReferences();
			for (IViewReference viewReference : views) {
				if (CHEAT_SHEED_VIEWER_ID.equals(viewReference.getId())) {
					CheatSheetView view = (CheatSheetView) viewReference.getPart(false);
					return view;
				}
			}
		}
		return null;
	}

	public static File importDirectory(ContentItem item, final Shell shell, SubMonitor monitor) throws CoreException {
		Assert.isNotNull(item);
		String id = item.getId();
		if (item.needsDownload()) {
			final ContentItem finalItem = item;
			final boolean[] response = new boolean[1];
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					response[0] = promptForDownload(shell, finalItem);
				}
			});
			if (response[0]) {
				DownloadJob job = ContentPlugin.getDefault().getManager().createDownloadJob(item);
				job.run(monitor.newChild(80));

				// re-get sample project since it may changed
				item = ContentPlugin.getDefault().getManager().getItem(id);
			}
			else if (!item.isLocal()) {
				return null;
			}
		}

		if (item == null || !item.isLocal()) {
			throw new CoreException(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, NLS.bind(
					"Download of sample project ''{0}'' failed", id)));
		}

		File baseDir = ContentPlugin.getDefault().getManager().getInstallDirectory();
		File projectDir = new File(baseDir, item.getPath());
		return projectDir;
	}

	public static SampleProjectData importSample(ContentItem project, Shell shell, IProgressMonitor monitor)
			throws CoreException {
		SubMonitor progress = SubMonitor.convert(monitor, 100);
		File projectDir = importDirectory(project, shell, progress);

		// import projects from local archives
		SampleProjectData data = new SampleProjectData(projectDir);
		data.read();
		progress.setWorkRemaining(data.getProjects().length * 10);
		for (WorkspaceProject importProject : data.getProjects()) {
			File archiveFile = new File(projectDir, importProject.getArchiveFileName());
			SampleProjectImporter importer = new SampleProjectImporter(importProject.getName(), archiveFile, shell);
			importer.importProject(progress.newChild(10));
		}
		return data;
	}

	private static void initializeCheatSheetViewer(IViewReference viewReference, boolean restart) {
		CheatSheetView cheatSheetView = (CheatSheetView) viewReference.getPart(false);
		if (cheatSheetView == null) {
			return;
		}

		try {
			Method method = ViewPart.class.getDeclaredMethod("setPartName", String.class);
			method.setAccessible(true);
			method.invoke(cheatSheetView, TASK_FOCUSED_TUTORIALS);
		}
		catch (Throwable e) {
			StatusHandler.log(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, "Failed to change view title", e));
		}

		if (restart) {
			cheatSheetView.getCheatSheetViewer().restart();
		}

		ICheatSheetManager manager = getCheatSheetManager(cheatSheetView.getCheatSheetViewer());
		if (manager instanceof CheatSheetManager) {
			// CheatSheetManager cheatSheetManager = (CheatSheetManager)
			// manager;
			// cheatSheetManager.addListener(new CheatSheetListener() {
			// @Override
			// public void cheatSheetEvent(ICheatSheetEvent event) {
			// System.err.println(event);
			// }
			// });
		}
	}

	public static void openTutorial(final IWorkbenchWindow window, final ContentItem tutorial, final boolean restart) {
		Assert.isNotNull(window);
		Assert.isNotNull(tutorial);
		if (tutorial.needsDownload()) {
			boolean download = promptForDownload(window.getShell(), tutorial);
			if (download) {
				download(window.getShell(), tutorial, new Runnable() {
					public void run() {
						String tutorialId = tutorial.getId();
						ContentItem tutorial = ContentPlugin.getDefault().getManager().getItem(tutorialId);
						if (tutorial == null) {
							StatusHandler.log(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID,
									"Error locating tutorial with id \"" + tutorialId + "\""));
							return;
						}
						openTutorialInternal(window, tutorial, restart);
					}
				});
			}
			else if (tutorial.isLocal()) {
				openTutorialInternal(window, tutorial, restart);
			}
		}
		else {
			openTutorialInternal(window, tutorial, restart);
		}
	}

	/**
	 * Starts a tutorial.
	 * 
	 * @param window the workbench window
	 * @param location local URL pointing to the cheatsheet file
	 * @param restart start the tutorial from the first step
	 */
	public static void openTutorial(IWorkbenchWindow window, URL location, boolean restart) {
		Assert.isNotNull(window);
		Assert.isNotNull(location);

		try {
			String path = URLEncoder.encode(location.toString(), "UTF-8");
			OpenCheatSheetAction openAction = new OpenCheatSheetAction(path, "Tutorial", location);
			openAction.run();
		}
		catch (Exception e) {
			UiStatusHandler.logAndDisplay(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, "Error opening tutorial", e));
			return;
		}

		IViewReference[] views = window.getActivePage().getViewReferences();
		for (IViewReference viewReference : views) {
			if (CHEAT_SHEED_VIEWER_ID.equals(viewReference.getId())) {
				initializeCheatSheetViewer(viewReference, restart);
			}
		}
	}

	public static void openTutorial(String tutorialId) {
		Assert.isNotNull(tutorialId);

		ContentItem tutorial = ContentPlugin.getDefault().getManager().getItem(tutorialId);
		if (tutorial == null) {
			StatusHandler.log(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, "Error locating tutorial with id \""
					+ tutorialId + "\""));
			return;
		}

		openTutorial(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), tutorial, false);
	}

	private static void openTutorialInternal(IWorkbenchWindow window, ContentItem tutorial, boolean restart) {
		try {
			File baseDir = ContentPlugin.getDefault().getManager().getInstallDirectory();
			File tutorialDir = new File(baseDir, tutorial.getPath());
			File cheatsheetFile = new File(tutorialDir, IContentConstants.TUTORIAL_CHEAT_SHEET_FILE_NAME);
			openTutorial(window, cheatsheetFile.toURI().toURL(), false);
		}
		catch (IOException e) {
			UiStatusHandler.logAndDisplay(window.getShell(), "Task-Focused Tutorial", new Status(IStatus.ERROR,
					HelpPlugin.PLUGIN_ID, "Error locating tutorial.", e));
		}
	}

	protected static boolean promptForDownload(Shell shell, ContentItem item) {
		if (CoreUtil.TEST_MODE) {
			return true;
		}
		try {
			List<ContentItem> dependencies = ContentPlugin.getDefault().getManager().getDependencies(item);
			long size = 0;
			for (ContentItem dependency : dependencies) {
				size += dependency.getDownloadSize();
			}
			AttachmentSizeFormatter formatter = new AttachmentSizeFormatter();
			String formattedSize = formatter.format(size);
			if (!item.isLocal()) {
				return MessageDialog.openQuestion(shell, "Import", NLS.bind("{0} requires a download of {1}. Proceed?",
						item.getName(), formattedSize));
			}
			else if (item.isNewerVersionAvailable()) {
				return MessageDialog.openQuestion(shell, "Import", NLS.bind(
						"An update for {0} is available which requires a download ({1}). Update?", item.getName(),
						formattedSize));
			}
		}
		catch (CoreException e) {
			UiStatusHandler.logAndDisplay(shell, e.getStatus());
			return false;
		}
		return true;
	}

}
