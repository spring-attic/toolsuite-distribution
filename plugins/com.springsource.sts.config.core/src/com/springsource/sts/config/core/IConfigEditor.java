/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core;

import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.IEditorPart;
import org.eclipse.wst.sse.ui.StructuredTextEditor;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.w3c.dom.Node;

import com.springsource.sts.config.core.contentassist.SpringConfigContentAssistProcessor;

/**
 * Public interface for the configuration editor.
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0.0
 */
@SuppressWarnings("restriction")
public interface IConfigEditor extends IEditorPart {

	/**
	 * Returns whether the given URI is enabled in the XML file.
	 * 
	 * @param uri a namespace URI
	 * @return true or false, whether the given URI is enabled in the XML file
	 */
	public boolean containsNamespaceUri(String uri);

	/**
	 * Widens the visibility of the method in the superclass.
	 * 
	 * @return the active nested editor
	 */
	public IEditorPart getActiveEditor();

	/**
	 * Returns all form page adapter definitions for the configuration editor.
	 * 
	 * @return set of form page adapter definitions for the configuration
	 * editor.
	 */
	public Set<IConfigurationElement> getAdapterDefinitions();

	/**
	 * Returns the {@link IDOMDocument} representation of the XML source file.
	 * 
	 * @return document object model of the XML source file
	 */
	public IDOMDocument getDomDocument();

	/**
	 * Returns all page definitions for the configuration editor.
	 * 
	 * @return set of page definitions for the configuration editor
	 */
	public Set<IConfigurationElement> getPageDefinitions();

	/**
	 * Returns the {@link IFile} of the XML source.
	 * 
	 * @return resource file of the XML source
	 */
	public IFile getResourceFile();

	/**
	 * Returns the page for the XML source editor.
	 * 
	 * @return XML source editor page
	 */
	public StructuredTextEditor getSourcePage();

	/**
	 * Returns the text viewer for the XML source editor.
	 * 
	 * @return XML source textviewer
	 */
	public StructuredTextViewer getTextViewer();

	/**
	 * Returns all best practice wizard definitions for the configuration
	 * editor.
	 * 
	 * @return set of best practice wizard definitions for the configuration
	 * editor
	 */
	public Set<IConfigurationElement> getWizardDefinitions();

	/**
	 * Returns a content assist processor for the XML source file.
	 * 
	 * @return content assist processor for the XML source file
	 */
	public SpringConfigContentAssistProcessor getXmlProcessor();

	/**
	 * Opens the editor to the tab for the given element's namespace and
	 * displays the element.
	 * 
	 * @param element the XML element to display in the editor
	 */
	public void revealElement(Node element);

}
