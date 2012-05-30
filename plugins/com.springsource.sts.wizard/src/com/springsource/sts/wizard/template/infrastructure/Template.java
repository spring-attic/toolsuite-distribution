/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.template.infrastructure;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.springsource.ide.eclipse.commons.content.core.ContentItem;

import com.springsource.sts.wizard.WizardPlugin;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class Template implements ITemplateElement {

	private final String name;

	private final String description;

	private final ImageDescriptor icon;

	private ITemplateProjectData data;

	private final ContentItem item;

	public Template(ContentItem item, ImageDescriptor icon) {
		this.item = item;
		this.name = item.getName();
		this.description = item.getDescription();
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public ImageDescriptor getIcon() {
		return icon;
	}

	public ContentItem getItem() {
		return item;
	}

	public String getName() {
		return name;
	}

	public ITemplateProjectData getTemplateData() {
		return data;
	}

	public URL getTemplateLocation() throws CoreException {
		if (data != null) {
			try {
				return data.getJsonDescriptor().toURL();
			}
			catch (MalformedURLException e) {
				throw new CoreException(new Status(Status.ERROR, WizardPlugin.PLUGIN_ID,
						"Unable to resolve template location", e));
			}
		}
		return null;
	}

	public URL getZippedLocation() throws CoreException {
		if (data != null) {
			try {
				return data.getZippedProject().toURL();
			}
			catch (MalformedURLException e) {
				throw new CoreException(new Status(Status.ERROR, WizardPlugin.PLUGIN_ID,
						"Unable to resolve template zipped location", e));
			}
		}
		return null;
	}

	public void setTemplateData(ITemplateProjectData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return this.getClass().toString() + "-" + name.toString();
	}

}
