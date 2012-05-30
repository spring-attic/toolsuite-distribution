/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.parts;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.springsource.ide.eclipse.commons.core.StatusHandler;

import com.springsource.sts.config.core.extensions.PageAdaptersExtensionPointConstants;
import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.ConfigFlowPlugin;

/**
 * @author Leo Dos Santos
 */
public abstract class AbstractConfigEditPartFactory implements EditPartFactory {

	private final AbstractConfigGraphicalEditor editor;

	public AbstractConfigEditPartFactory(AbstractConfigGraphicalEditor editor) {
		super();
		this.editor = editor;
	}

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = createEditPartFromModel(context, model);
		if (part != null) {
			return part;
		}
		else {
			for (IConfigurationElement config : editor.getAdapterDefinitions()) {
				try {
					Object obj = config
							.createExecutableExtension(PageAdaptersExtensionPointConstants.ATTR_EDITPART_FACTORY);
					if (obj instanceof EditPartFactory) {
						EditPartFactory factory = (EditPartFactory) obj;
						part = factory.createEditPart(context, model);
						if (part != null) {
							return part;
						}
					}
				}
				catch (CoreException e) {
					StatusHandler.log(new Status(IStatus.ERROR, ConfigFlowPlugin.PLUGIN_ID,
							Messages.AbstractConfigEditPartFactory_ERROR_CREATING_GRAPH, e));
				}
			}
		}
		return null;
	}

	protected abstract EditPart createEditPartFromModel(EditPart context, Object model);

}
