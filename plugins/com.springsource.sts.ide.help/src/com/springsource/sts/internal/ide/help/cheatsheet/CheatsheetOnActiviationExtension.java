/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.cheatsheet;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.AbstractItemExtensionElement;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.internal.cheatsheets.views.CheatSheetPage;
import org.eclipse.ui.internal.cheatsheets.views.CheatSheetView;
import org.eclipse.ui.internal.cheatsheets.views.CheatSheetViewer;
import org.eclipse.ui.internal.cheatsheets.views.ViewItem;
import org.springsource.ide.eclipse.commons.content.core.util.IContentConstants;
import org.springsource.ide.eclipse.commons.core.StatusHandler;

import com.springsource.sts.ide.help.TutorialUtils;
import com.springsource.sts.internal.ide.help.HelpImages;
import com.springsource.sts.internal.ide.help.HelpPlugin;
import com.springsource.sts.internal.ide.help.tutorial.TutorialAction;

/**
 * @author Steffen Pingel
 * @author Wesley Coelho
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @author Terry Denney
 */
public class CheatsheetOnActiviationExtension extends AbstractItemExtensionElement {

	public static void activateNext(String stepId) {
		CheatsheetOnActiviationExtension extension = getNextExtension(stepId);
		if (extension != null) {
			extension.activate();
		}
	}

	public static CheatsheetOnActiviationExtension getNextExtension(String stepId) {
		if (stepId == null) {
			if (!extensionByStepId.isEmpty()) {
				return extensionByStepId.values().iterator().next();
			}
		}
		else {
			for (Iterator<String> it = extensionByStepId.keySet().iterator(); it.hasNext();) {
				if (it.next().equals(stepId)) {
					if (it.hasNext()) {
						return extensionByStepId.get(it.next());
					}
					else {
						// last step, restart from beginning
						return extensionByStepId.values().iterator().next();
					}
				}

			}
		}
		return null;
	}

	private String step;

	private ICheatSheetManager manager;

	protected Color activeColor;

	private CheatSheetViewer viewer;

	private ViewItem previousItem;

	private ImageHyperlink link;

	private static Map<String, CheatsheetOnActiviationExtension> extensionByStepId = new LinkedHashMap<String, CheatsheetOnActiviationExtension>();

	public CheatsheetOnActiviationExtension(String attributeName) {
		super(attributeName);
	}

	private void activate() {
		if (viewer != null && link != null && !link.isDisposed() && previousItem != null) {
			manager.setData(IContentConstants.TUTORIAL_STARTED, TutorialAction.INSTANCE_ID);
			link.setData(previousItem);
			advanceItem(viewer, link);
		}
	}

	private void advanceItem(CheatSheetViewer viewer, ImageHyperlink link) {
		try {
			Method method = CheatSheetViewer.class
					.getDeclaredMethod("advanceItem", ImageHyperlink.class, boolean.class);
			method.setAccessible(true);
			method.invoke(viewer, link, false);
		}
		catch (Throwable e) {
			StatusHandler.log(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, "Failed to advance item", e));
		}
	}

	@Override
	public void createControl(Composite composite) {
		link = new ImageHyperlink(composite, SWT.NONE) {

			@Override
			public void setBackground(Color color) {
				super.setBackground(color);
				if (activeColor == null) {
					setCheatSheetManager(getShell().getDisplay());
				}
				if (activeColor != null && activeColor.equals(color)) {
					getShell().getDisplay().asyncExec(new Runnable() {
						public void run() {
							doActivate(manager);
						}
					});
				}
			}
		};
		link.setImage(HelpImages.getImage(HelpImages.RESTART_STEP));
		link.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e) {
				activate();
			}

		});

		extensionByStepId.put(step, this);
	}

	@Override
	public void dispose() {
		if (activeColor != null) {
			activeColor.dispose();
			activeColor = null;
		}
		extensionByStepId.remove(getAttributeName());
	}

	public void doActivate(ICheatSheetManager manager) {
		String started = manager.getData(IContentConstants.TUTORIAL_STARTED);
		if (started != null && !TutorialAction.INSTANCE_ID.equals(started)) {
			// ignore events after startup, the first activation event needs to
			// be triggered by the user
			manager.setData(IContentConstants.TUTORIAL_STARTED, TutorialAction.INSTANCE_ID);
			return;
		}

		TutorialAction action = new TutorialAction();
		String[] params = new String[] { step };
		action.run(params, manager);
	}

	@Override
	public void handleAttribute(String attributeValue) {
		this.step = attributeValue;
	}

	// API SDK uses Eclipse internals to access the cheat sheet manager
	private void setCheatSheetManager(final Display display) {
		final IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (activeWorkbenchWindow != null) {
			IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
			if (activePage != null && activePage.getActivePart() instanceof CheatSheetView) {
				CheatSheetView view = (CheatSheetView) activePage.getActivePart();
				setCheatSheetManager(display, view);
			}
			else {
				display.asyncExec(new Runnable() {
					public void run() {
						CheatSheetView view = TutorialUtils.getCheatSheetView(activeWorkbenchWindow);
						if (view != null) {
							setCheatSheetManager(display, view);
						}
					}
				});
			}
		}
	}

	private void setCheatSheetManager(final Display display, CheatSheetView view) {
		viewer = view.getCheatSheetViewer();
		CheatSheetPage page = new CheatSheetPage(null, null, viewer) {
			@Override
			public void dispose() {
				init(display);
				activeColor = new Color(display, getActiveColor().getRGB());
				super.dispose();
			}
		};
		page.dispose();

		setViewItem(viewer);
		setManager(TutorialUtils.getCheatSheetManager(viewer));
	}

	private void setManager(ICheatSheetManager manager) {
		this.manager = manager;
		// TutorialManager.
		// if (manager instanceof CheatSheetManager) {
		// System.err.println("registered: " + manager.getCheatSheetID());
		// CheatSheetManager cheatSheetManager = (CheatSheetManager) manager;
		// cheatSheetManager.addListener(new CheatSheetListener() {
		//
		// @Override
		// public void cheatSheetEvent(ICheatSheetEvent event) {
		// System.err.println(event.getCheatSheetID());
		// System.err.println(event.getEventType());
		// System.err.println(CheatsheetOnActiviationExtension.this.manager.getData("taskHandle"));
		// System.out.println(CheatsheetOnActiviationExtension.this.manager ==
		// event.getCheatSheetManager());
		// }
		//
		// });
		// }
		// viewer.
	}

	@SuppressWarnings("unchecked")
	private void setViewItem(CheatSheetViewer viewer) {
		try {
			Field field = CheatSheetViewer.class.getDeclaredField("viewItemList");
			field.setAccessible(true);
			Object value = field.get(viewer);
			if (value instanceof List) {
				ViewItem previousItem = null;
				for (Object object : (List) value) {
					if (object instanceof ViewItem) {
						ViewItem viewItem = (ViewItem) object;
						ArrayList itemExtensions = viewItem.getItem().getItemExtensions();
						if (itemExtensions != null) {
							for (Object extensions : itemExtensions) {
								if (extensions instanceof AbstractItemExtensionElement[]) {
									for (Object extension : (AbstractItemExtensionElement[]) extensions) {
										if (extension == this) {
											this.previousItem = previousItem;
											// this.viewItem = viewItem;
											return;
										}
									}
								}
							}
						}
						previousItem = viewItem;
					}
				}
			}
		}
		catch (Throwable e) {
			StatusHandler
					.log(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, "Failed to access cheat sheet manager", e));
		}
	}
}
