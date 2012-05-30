/******************************************************************************************
 * Copyright (c) 2007 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.springsource.ide.eclipse.commons.content.core.ContentItem;
import org.springsource.ide.eclipse.commons.content.core.ContentManager;
import org.springsource.ide.eclipse.commons.content.core.ContentPlugin;
import org.springsource.ide.eclipse.commons.content.core.util.Descriptor;
import org.springsource.ide.eclipse.commons.content.core.util.DescriptorReader;
import org.springsource.ide.eclipse.commons.core.StatusHandler;

import com.springsource.sts.internal.ide.help.tutorial.TutorialCategory;

/**
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @author Terry Denney
 */
public class HelpPlugin extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "com.springsource.sts.ide.help";

	// private static final String EXTENSION_ID_SAMPLE =
	// "com.springsource.sts.ide.help.samples";
	//
	// private static final String EXTENSION_ID_TUTORIAL =
	// "com.springsource.sts.ide.help.tutorials";
	//
	// private static final String EXTENSION_ELEMENT_SAMPLE_PROJECT =
	// "sampleProject";
	//
	// private static final String EXTENSION_ELEMENT_CATEGORY = "category";
	//
	// private static final String EXTENSION_ELEMENT_TUTORIAL = "tutorial";
	//
	// private static final String EXTENSION_ELEMENT_ID = "id";
	//
	// private static final String EXTENSION_ELEMENT_NAME = "name";
	//
	// private static final String EXTENSION_ELEMENT_PATH = "path";
	//
	// private static final String EXTENSION_ELEMENT_DESCRIPTION =
	// "description";
	//
	// private static final String EXTENSION_ELEMENT_PROJECT_NAME =
	// "projectName";
	//
	// private static final String CATEGORY_ID_OTHER =
	// "com.springsource.sts.ide.help.category.other";
	//
	// private static final String CATEGORY_LABEL_OTHER = "Other";

	private static HelpPlugin plugin;

	public static HelpPlugin getDefault() {
		return plugin;
	}

	public static Image getImage(String path) {
		ImageRegistry imageRegistry = getDefault().getImageRegistry();
		Image image = imageRegistry.get(path);
		if (image == null) {
			ImageDescriptor imageDescriptor = getImageDescriptor(path);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			image = imageDescriptor.createImage(true);
			imageRegistry.put(path, image);
		}
		return image;
	}

	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, "icons/" + path);
	}

	/**
	 * Convenience method for logging statuses to the plug-in log
	 * 
	 * @param status the status to log
	 */
	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	public static void log(Throwable e) {
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, "Unexpected exception", e));
	}

	private final Map<String, ContentItem> itemById = new HashMap<String, ContentItem>();


	private final Map<String, TutorialCategory> tutorialCategoryById = new HashMap<String, TutorialCategory>();

	private static final String CATEGORY_ID_OTHER = "com.springsource.sts.ide.help.category.other";

	private static final String CATEGORY_LABEL_OTHER = "Other";

	public HelpPlugin() {
	}

	private ContentItem createItem(Descriptor descriptor) {
		ContentItem item = ContentPlugin.getDefault().getManager().getItem(descriptor.getId());
		if (item != null) {
			String category = descriptor.getCategory();
			TutorialCategory tutorialCategory;

			if (category != null) {
				tutorialCategory = tutorialCategoryById.get(category);
				if (tutorialCategory == null) {
					tutorialCategory = new TutorialCategory(category, category);
					tutorialCategoryById.put(tutorialCategory.getId(), tutorialCategory);
				}
			}
			else {
				tutorialCategory = tutorialCategoryById.get(CATEGORY_ID_OTHER);
				if (tutorialCategory == null) {
					tutorialCategory = new TutorialCategory(CATEGORY_ID_OTHER, CATEGORY_LABEL_OTHER);
					tutorialCategoryById.put(CATEGORY_ID_OTHER, tutorialCategory);
				}
			}

			tutorialCategory.addTutorial(item);
			return item;
		}

		return null;
	}

	public Collection<TutorialCategory> getTutorialCategories() {
		itemById.clear();
		tutorialCategoryById.clear();

		ContentManager manager = ContentPlugin.getDefault().getManager();

		File file = manager.getStateFile();
		if (file != null && file.exists()) {
			try {
				read(file);
			}
			catch (CoreException e) {
				StatusHandler.log(new Status(IStatus.WARNING, HelpPlugin.PLUGIN_ID, NLS.bind(
						"Detected error in ''{0}''", file.getAbsoluteFile()), e));
			}
		}

		file = manager.getDefaultStateFile();
		if (file != null) {
			try {
				read(file);
			}
			catch (CoreException e) {
				StatusHandler.log(new Status(IStatus.WARNING, HelpPlugin.PLUGIN_ID, NLS.bind(
						"Detected error in ''{0}''", file.getAbsoluteFile()), e));
			}
		}

		return Collections.unmodifiableCollection(tutorialCategoryById.values());
	}

	private void read(File file) throws CoreException {
		DescriptorReader reader = new DescriptorReader();
		reader.read(file);
		List<Descriptor> descriptors = reader.getDescriptors();
		for (Descriptor descriptor : descriptors) {
			if (ContentManager.KIND_TUTORIAL.equals(descriptor.getKind())) {
				ContentItem item = itemById.get(descriptor.getId());
				if (item == null) {
					item = createItem(descriptor);
					if (item != null) {
						itemById.put(item.getId(), item);
					}
				}
			}
		}
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

}
