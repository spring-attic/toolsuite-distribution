/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.tutorial;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.mylyn.context.core.ContextCore;
import org.eclipse.mylyn.internal.context.core.ContextCorePlugin;
import org.eclipse.mylyn.internal.context.core.InteractionContext;
import org.eclipse.mylyn.internal.context.core.InteractionContextManager;
import org.eclipse.mylyn.internal.context.core.InteractionContextScaling;
import org.eclipse.mylyn.internal.resources.ui.ResourcesUiBridgePlugin;
import org.eclipse.mylyn.internal.tasks.core.AbstractTask;
import org.eclipse.mylyn.internal.tasks.core.LocalRepositoryConnector;
import org.eclipse.mylyn.internal.tasks.core.LocalTask;
import org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin;
import org.eclipse.mylyn.monitor.core.InteractionEvent;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.ITask.PriorityLevel;
import org.eclipse.mylyn.tasks.core.ITaskActivityManager;
import org.eclipse.mylyn.tasks.ui.TasksUi;
import org.eclipse.mylyn.tasks.ui.TasksUiUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;
import org.eclipse.ui.actions.DeleteResourceAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.eclipse.ui.internal.IWorkbenchConstants;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.cheatsheets.views.CheatSheetView;
import org.eclipse.wst.server.core.IServer;
import org.springframework.ide.eclipse.webflow.core.Activator;
import org.springsource.ide.eclipse.commons.configurator.ServerHandler;
import org.springsource.ide.eclipse.commons.content.core.ContentItem;
import org.springsource.ide.eclipse.commons.content.core.ContentPlugin;
import org.springsource.ide.eclipse.commons.content.core.util.IContentConstants;
import org.springsource.ide.eclipse.commons.core.Policy;
import org.springsource.ide.eclipse.commons.core.StatusHandler;
import org.springsource.ide.eclipse.commons.internal.configurator.server.ServerConfigurator;
import org.springsource.ide.eclipse.commons.internal.configurator.server.ServerDescriptor;
import org.springsource.ide.eclipse.commons.ui.ICoreRunnable;
import org.springsource.ide.eclipse.commons.ui.UiUtil;

import com.springsource.sts.ide.help.TutorialUtils;
import com.springsource.sts.internal.ide.help.HelpPlugin;
import com.springsource.sts.internal.ide.help.SampleProjectData;
import com.springsource.sts.internal.ide.help.SampleProjectData.WorkspaceProject;
import com.springsource.sts.internal.ide.help.SampleProjectImporter;
import com.springsource.sts.internal.ide.help.tutorial.TutorialData.TutorialProject;
import com.springsource.sts.internal.ide.help.tutorial.TutorialData.TutorialStep;
import com.springsource.sts.internal.ide.help.tutorial.TutorialData.TutorialTask;

/**
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Terry Denney
 * @author Christian Dupuis
 */
public class TutorialManager {

	public enum ServerType {
		TOMCAT, DM_SERVER
	};

	public static final String CONTEXT_MODE_ADD = "add";

	public static final String CONTEXT_MODE_REPLACE = "replace";

	private static final String ERROR_MISSING_PROJECT = "This tutorial requires a project that was not found in the workspace. Please restart the tutorial to create the project.";

	private static final String KEY_TASK_HANDLE = "taskHandle";

	/**
	 * Task attribute that stores id of associated tutorial.
	 */
	public static final String KEY_TUTORIAL_URL = "tutorialUrl";

	private static final String KEY_PROJECT_NAME_PREFIX = "project";

	private static final String PREFS_PREFIX = "editors.task.";

	private static final String DEFAULT_CHEAT_SHEET_ID = "cheatsheet.xml";

	private static final int DEFAULT_HTTP_PORT = 8080;

	private static TutorialManager activeTutorial;

	public static TutorialManager getActiveTutorial() {
		return activeTutorial;
	}

	public static TutorialManager getActiveTutorial(IWorkbenchWindow window) throws CoreException {
		if (activeTutorial == null) {
			CheatSheetView view = TutorialUtils.getCheatSheetView(window);
			if (view != null) {
				ICheatSheetManager manager = TutorialUtils.getCheatSheetManager(view.getCheatSheetViewer());
				if (manager != null) {
					TutorialManager tutorial = new TutorialManager(manager);
					if (tutorial.isTutorial()) {
						setActiveTutorial(tutorial);
					}
				}
			}
		}

		return activeTutorial;
	}

	public static void setActiveTutorial(TutorialManager activeTutorial) {
		TutorialManager.activeTutorial = activeTutorial;
	}

	private final ICheatSheetManager cheatSheetManager;

	private TutorialData tutorialData;

	private CheatSheetData cheatSheetData;

	private static InteractionContextScaling contextScaling = new InteractionContextScaling();
	static {
		contextScaling.setDecay(0.0f);
	}

	public TutorialManager(ICheatSheetManager cheatSheetManager) {
		this.cheatSheetManager = cheatSheetManager;
	}

	private void activateTask(ITask task) {
		if (task.isActive()) {
			return;
		}

		// avoid having mylyn restore editors for us
		ResourcesUiBridgePlugin.getDefault().getPreferenceStore()
				.setValue(PREFS_PREFIX + task.getHandleIdentifier(), "");
		TasksUi.getTaskActivityManager().activateTask(task);
	}

	public void addToContext(final File contextFile, final File editorFile) throws CoreException {
		ICoreRunnable runner = new ICoreRunnable() {
			public void run(final IProgressMonitor monitor) throws CoreException {
				try {
					monitor.beginTask("Loading context", 100);
					Policy.checkCancelled(monitor);

					final ITask task = getTask();
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							activateTask(task);
							monitor.worked(10);
						}
					});

					final InteractionContext context = loadContext(contextFile);
					monitor.worked(40);

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							List<InteractionEvent> events = context.getInteractionHistory();
							ContextCorePlugin.getContextManager().processInteractionEvents(events, true);
							monitor.worked(40);

							if (editorFile != null) {
								restoreEditors(editorFile);
							}

							// TODO refreshViews(contextFileName);
							monitor.worked(10);
						}
					});
				}
				finally {
					monitor.done();
				}
			}

		};
		org.springsource.ide.eclipse.commons.ui.UiUtil.busyCursorWhile(runner);
	}

	@SuppressWarnings("deprecation")
	public void cleanupWorkspace() throws CoreException {
		if (cheatSheetManager.getData(KEY_TASK_HANDLE) != null) {
			deactivateAllTasks();
			TasksUiPlugin.getTaskList().deleteTask(getTask());
		}

		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		// API SDK switch to 3.4 API
		DeleteResourceAction action = new DeleteResourceAction(shell);
		String[] projectNames = getProjectNames();
		if (projectNames != null) {
			List<IProject> projects = new ArrayList<IProject>(projectNames.length);
			for (String projectName : projectNames) {
				projects.add(getProject(projectName));
			}
			action.selectionChanged(new StructuredSelection(projects));
			action.run();
		}
	}

	private void closeAllEditors() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if (page != null) {
			page.closeAllEditors(true);
		}
	}

	private void deactivateAllTasks() {
		final ITaskActivityManager taskActivityManager = TasksUi.getTaskActivityManager();
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				taskActivityManager.deactivateActiveTask();
			}
		});
	}

	public CheatSheetData getCheatSheetData() throws CoreException {
		if (cheatSheetData == null) {
			File path = getPath();
			if (path == null) {
				throw new CoreException(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, "Could not locate tutorial: \""
						+ getCheatSheetId() + "\""));
			}

			cheatSheetData = new CheatSheetData(path);
			cheatSheetData.read();
		}
		return cheatSheetData;
	}

	public IFile getCheatSheetFile() {
		URI uri;
		try {
			uri = new URL(getTutorialUrl()).toURI();
		}
		catch (Exception e) {
			return null;
		}
		IFile[] files = ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(uri);
		return (files.length > 0) ? files[0] : null;
	}

	/**
	 * Returns the id of the tutorial cheat sheet. Returns a URL encoded url if
	 * the tutorial was opened from a file.
	 */
	public String getCheatSheetId() {
		return cheatSheetManager.getCheatSheetID();
	}

	private String getFirstContextHandle(File sourceFile) throws CoreException {
		try {
			ZipFile zipFile = new ZipFile(sourceFile);
			try {
				for (Enumeration<?> e = zipFile.entries(); e.hasMoreElements();) {
					ZipEntry entry = (ZipEntry) e.nextElement();
					String name = entry.getName();
					String decodedName = URLDecoder.decode(name, InteractionContextManager.CONTEXT_FILENAME_ENCODING);
					if (decodedName.length() > InteractionContextManager.CONTEXT_FILE_EXTENSION_OLD.length()) {
						return decodedName.substring(0, decodedName.length()
								- InteractionContextManager.CONTEXT_FILE_EXTENSION_OLD.length());
					}
				}
				return null;
			}
			finally {
				zipFile.close();
			}
		}
		catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, ContextCorePlugin.ID_PLUGIN,
					"Could not get context handle from " + sourceFile, e));
		}
	}

	// protected void refreshViews(String contextFileName) {
	// IViewPart view =
	// FileUtil.getViewFromActivePerspective(VIEW_ID_PACKAGE_EXPLORER);
	// if (view instanceof IPackagesViewPart) {
	// IPackagesViewPart packageView = (IPackagesViewPart) view;
	// IProject project = getProject();
	// if (project != null) {
	// packageView.selectAndReveal(project);
	// }
	// }
	//
	// view = FileUtil.getViewFromActivePerspective(VIEW_ID_PROJECT_EXPLORER);
	// if (view instanceof CommonNavigator) {
	// CommonNavigator commonNavigator = (CommonNavigator) view;
	// IProject project = getProject();
	// if (project != null) {
	// commonNavigator.selectReveal(new StructuredSelection(project));
	// }
	// }
	// }

	public File getPath() {
		String id = getCheatSheetId();
		if (id.startsWith("file")) {
			try {
				return new File(new URL(URLDecoder.decode(id, "UTF-8")).toURI()).getParentFile();
			}
			catch (Exception e) {
			}
		}

		return null;
	}

	public IProject getProject(String projectName) throws CoreException {
		if (projectName == null) {
			throw new CoreException(new Status(IStatus.INFO, HelpPlugin.PLUGIN_ID,
					IContentConstants.ERROR_MISSING_PROJECT, ERROR_MISSING_PROJECT, null));
		}

		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		if (!project.exists() || !project.isOpen()) {
			throw new CoreException(new Status(IStatus.INFO, HelpPlugin.PLUGIN_ID,
					IContentConstants.ERROR_MISSING_PROJECT, ERROR_MISSING_PROJECT, null));
		}
		return project;
	}

	private String[] getProjectNames() {
		List<String> projectNames = new ArrayList<String>();
		int i = 0;
		String name;
		while ((name = cheatSheetManager.getData(KEY_PROJECT_NAME_PREFIX + i)) != null) {
			projectNames.add(name);
			i++;
		}

		if (projectNames.isEmpty()) {
			return null;
		}
		else {
			return projectNames.toArray(new String[0]);
		}
	}

	public ITask getTask() throws CoreException {
		// if (task != null) {
		// return task;
		// }

		ITask task = null;
		String taskHandle = cheatSheetManager.getData(KEY_TASK_HANDLE);
		if (taskHandle != null) {
			task = TasksUiPlugin.getTaskList().getTask(taskHandle);
		}

		if (task == null) {
			// search for existing task
			Set<ITask> tasks = TasksUiPlugin.getTaskList().getTasks(LocalRepositoryConnector.REPOSITORY_URL);
			for (ITask existingTask : tasks) {
				if (getCheatSheetId().equals(existingTask.getAttribute(KEY_TUTORIAL_URL))) {
					task = existingTask;
					cheatSheetManager.setData(KEY_TASK_HANDLE, task.getHandleIdentifier());
				}
			}
		}

		if (task == null) {
			// create a new task
			TutorialTask tutorialTaskData = getTutorialData().getTask();
			task = new LocalTask("" + TasksUiPlugin.getTaskList().getNextLocalTaskId(), tutorialTaskData.getSummary());
			task.setPriority(PriorityLevel.P3.toString());
			((AbstractTask) task).setNotes(tutorialTaskData.getDescription());
			TasksUiPlugin.getTaskList().addTask(task);
			task.setAttribute(KEY_TUTORIAL_URL, getTutorialUrl());
			cheatSheetManager.setData(KEY_TASK_HANDLE, task.getHandleIdentifier());
		}

		return task;
	}

	public TutorialData getTutorialData() throws CoreException {
		if (tutorialData == null) {
			File path = getPath();
			if (path == null) {
				throw new CoreException(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, "Could not locate tutorial: \""
						+ getCheatSheetId() + "\""));
			}

			tutorialData = new TutorialData(path);
			tutorialData.read();
			tutorialData.validate();
		}
		return tutorialData;
	}

	public String getTutorialUrl() {
		try {
			return URLDecoder.decode(getCheatSheetId(), "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public boolean isCheatSheetPreview() {
		return DEFAULT_CHEAT_SHEET_ID.equals(getCheatSheetId());
	}

	public boolean isTutorial() {
		return getPath() != null;
	}

	private InteractionContext loadContext(final File sourceFile) throws CoreException {
		if (sourceFile == null || !sourceFile.exists()) {
			return new InteractionContext(getTask().getTaskId(), ContextCore.getCommonContextScaling());
		}
		String handleToImportFrom = getFirstContextHandle(sourceFile);
		InteractionContext context = (InteractionContext) ContextCorePlugin.getContextStore().loadContext(
				handleToImportFrom, sourceFile, contextScaling);
		return context;
	}

	public void replaceContext(final File contextFile, final File editorFile) throws CoreException {
		ICoreRunnable runner = new ICoreRunnable() {
			public void run(final IProgressMonitor monitor) throws CoreException {
				try {
					monitor.beginTask("Loading context", 100);
					Policy.checkCancelled(monitor);

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							TasksUi.getTaskActivityManager().deactivateActiveTask();
							monitor.worked(5);
						}
					});

					final ITask task = getTask();

					InteractionContext context = loadContext(contextFile);
					monitor.worked(40);

					context.setHandleIdentifier(getTask().getHandleIdentifier());
					ContextCorePlugin.getContextStore().saveContext(context);
					monitor.worked(40);

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							activateTask(task);
							monitor.worked(10);

							if (editorFile != null) {
								restoreEditors(editorFile);
							}

							// TODO refreshViews(contextFileName);
							monitor.worked(5);
						}
					});
				}
				finally {
					monitor.done();
				}
			}

		};
		org.springsource.ide.eclipse.commons.ui.UiUtil.busyCursorWhile(runner);
	}

	private void restoreEditors(File file) {
		Assert.isNotNull(file);

		try {
			InputStreamReader in = new InputStreamReader(new GZIPInputStream(new FileInputStream(file)));
			try {
				IMemento memento = XMLMemento.createReadRoot(in);
				if (memento != null) {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					//restoreEditors((WorkbenchPage) page, memento);
				}
			}
			finally {
				in.close();
			}
		}
		catch (IOException e) {
			StatusHandler.log(new Status(IStatus.WARNING, HelpPlugin.PLUGIN_ID,
					"Could not restore editors from file \"" + file + "\""));
		}
		catch (WorkbenchException e) {
			StatusHandler.log(new Status(IStatus.WARNING, HelpPlugin.PLUGIN_ID,
					"Could not restore editors from file \"" + file + "\""));
		}
	}

	public void runInServer(final ServerType serverType, final String projectName, String url) throws CoreException {
		Assert.isNotNull(projectName);

		validateProjects();

		try {
			final int[] port = new int[1];
			UiUtil.busyCursorWhile(new ICoreRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					String id;
					if (serverType == ServerType.TOMCAT) {
						id = ServerConfigurator.ID_TOMCAT;
					}
					else if (serverType == ServerType.DM_SERVER) {
						id = ServerConfigurator.ID_DM_SERVER_1;
					}
					else {
						throw new RuntimeException("Unexpected ServerType: " + serverType);
					}

					ServerConfigurator importer = new ServerConfigurator();
					ServerDescriptor descriptor = importer.getDescriptor(id);
					if (descriptor == null) {
						throw new CoreException(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, NLS.bind(
								"Could not find information to provision server with id ''{0}''", id)));
					}
					ServerHandler serverHandler = importer.installServer(descriptor, ServerHandler.NEVER_OVERWRITE,
							monitor);
					IServer server = serverHandler.launch(getProject(projectName), monitor);
					port[0] = serverHandler.getHttpPort(server, monitor);
				}
			});

			if (url != null) {
				if (port[0] == -1) {
					port[0] = DEFAULT_HTTP_PORT;
				}
				url.replace("${port}", port[0] + "");
				TasksUiUtil.openUrl(url);
			}
		}
		catch (CoreException e) {
			String append = "";
			IStatus status = e.getStatus();
			if (status.getMessage() != null) {
				append = "\n\n" + status.getMessage();
			}
			throw new CoreException(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, "Could not launch Tomcat" + append,
					e));
		}
	}

	public void runStep(String stepId) throws CoreException {
		activateTask(getTask());

		TutorialStep step = getTutorialData().getStep(stepId);
		if (step != null) {
			if (step.getContextFileName() != null) {
				File contextFile = getTutorialData().getFile(step.getContextFileName());
				File editorFile = null;
				if (step.getEditorFileName() != null) {
					editorFile = getTutorialData().getFile(step.getEditorFileName());
				}

				validateProjects();

				try {
					if (CONTEXT_MODE_ADD.equals(step.getContextMode())) {
						addToContext(contextFile, editorFile);
					}
					else if (CONTEXT_MODE_REPLACE.equals(step.getContextMode())) {
						replaceContext(contextFile, editorFile);
					}
					else {
						StatusHandler.log(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, "Unknown context mode: \""
								+ step.getContextMode() + "\""));
					}
				}
				catch (CoreException e) {
					StatusHandler.log(e.getStatus());
				}
			}
		}
	}

	private void setProjectNames(String[] names) {
		for (int i = 0; i < names.length; i++) {
			cheatSheetManager.setData(KEY_PROJECT_NAME_PREFIX + i, names[i]);
		}
	}

	public void setupWorkspace() throws CoreException {
		// avoid adding imported resources to active context
		deactivateAllTasks();
		closeAllEditors();

		final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		final TutorialProject[] projects = getTutorialData().getProjects();

		ICoreRunnable runner = new ICoreRunnable() {
			private void importSampleProject(TutorialProject tutorialProject, List<String> projectNames,
					IProgressMonitor monitor) throws CoreException {
				String sampleProjectId = tutorialProject.getSampleProjectId();
				if (sampleProjectId != null) {
					ContentItem sampleProject = ContentPlugin.getDefault().getManager().getItem(sampleProjectId);
					if (sampleProject == null) {
						StatusHandler.log(new Status(IStatus.WARNING, HelpPlugin.PLUGIN_ID,
								"Could not import sample project \"" + sampleProjectId + "\""));
						return;
					}
					SampleProjectData data = TutorialUtils.importSample(sampleProject, shell, monitor);
					for (WorkspaceProject project : data.getProjects()) {
						projectNames.add(project.getName());
					}
				}
				else {
					File file = getTutorialData().getFile(tutorialProject.getArchiveFileName());
					if (file == null) {
						StatusHandler.log(new Status(IStatus.WARNING, HelpPlugin.PLUGIN_ID,
								"Could not find sample project archive \"" + tutorialProject.getArchiveFileName()
										+ "\""));
						return;
					}
					SampleProjectImporter importer = new SampleProjectImporter(tutorialProject.getName(), file, shell);
					importer.importProject(monitor);
					projectNames.add(tutorialProject.getName());
				}
			}

			public void run(final IProgressMonitor monitor) throws CoreException {
				try {
					monitor.beginTask("Setup Workspace", 2 * projects.length * 100);

					List<String> projectNames = new ArrayList<String>();

					for (TutorialProject tutorialProject : projects) {
						try {
							importSampleProject(tutorialProject, projectNames, new SubProgressMonitor(monitor, 100));
						}
						catch (OperationCanceledException e) {
							// ignore cancellation of the import of a single
							// project
						}
					}

					setProjectNames(projectNames.toArray(new String[0]));

					// XXX make sure web flow model is up-to-date and rebuild
					Activator.getModel().removeProject(null);

					for (String projectName : projectNames) {
						getProject(projectName).build(IncrementalProjectBuilder.FULL_BUILD,
								new SubProgressMonitor(monitor, 100));
					}
				}
				finally {
					monitor.done();
				}
			}
		};
		org.springsource.ide.eclipse.commons.ui.UiUtil.busyCursorWhile(runner);

		// create new task
		getTask();
	}

	private void validateProjects() throws CoreException {
		String[] projects = getProjectNames();
		if (projects != null) {
			for (String projectName : projects) {
				getProject(projectName);
			}
		}
	}

}
