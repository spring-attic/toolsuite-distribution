/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core;

import org.eclipse.ui.IEditorPart;

/**
 * Public interface for configuration editor pages.
 * @author Leo Dos Santos
 * @since 2.1.0
 */
public interface IConfigEditorPage extends IEditorPart {

	/**
	 * Returns the parent editor as a {@link IConfigEditor} object;
	 * 
	 * @return parent editor instance
	 */
	public IConfigEditor getEditor();

	/**
	 * Returns the namespace URI associated with this page. May be null.
	 * 
	 * @return namespace URI associated with the page
	 */
	public String getNamespaceUri();

	/**
	 * Returns an identifier describing the page kind, for use in preference
	 * storage.
	 * 
	 * @return metadata describing the page kind
	 */
	public String getPageKind();

	/**
	 * Initialize the page with the parent editor as a {@link IConfigEditor}
	 * instance, and with a namespace URI.
	 * 
	 * @param editor the parent editor
	 * @param uri the namespace URI associated with the page
	 */
	public void initialize(IConfigEditor editor, String uri);

	/**
	 * Updates the page to reflect the changes in the parent editor's model.
	 */
	public void modelUpdated();

	/**
	 * Updates the page to reflect the changes to the parent editor's
	 * namespaces.
	 */
	public void namespacesUpdated();

}
