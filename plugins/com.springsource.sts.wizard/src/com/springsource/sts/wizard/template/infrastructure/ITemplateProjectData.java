package com.springsource.sts.wizard.template.infrastructure;

import java.io.File;

import org.springsource.ide.eclipse.commons.content.core.util.Descriptor;


public interface ITemplateProjectData {

	public Descriptor getDescriptor();

	public File getJsonDescriptor();

	// public abstract File getTemplateDirectory();

	public File getZippedProject();

	public static final String TAG_TEMPLATE = "template";

	public static final String TAG_DESCRIPTOR = "descriptor";

	public static final String TAG_PROJECT = "project";

	public static final String TAG_JSON = "json";

	public static final String ATTRIBUTE_PATH = "path";

}