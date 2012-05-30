/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model.commands;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.core.internal.document.NodeImpl;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Node;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.StructuredActivity;

/**
 * AddCommand
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class AddCommand extends AbstractTextCommand {

	private StructuredActivity parent;

	private Activity child;

	private IDOMElement parentElement;

	private IDOMElement childElement;

	private final EditPartViewer viewer;

	public AddCommand(ITextEditor textEditor, EditPartViewer viewer) {
		super(textEditor);
		this.viewer = viewer;
	}

	@Override
	public boolean canExecute() {
		parentElement = parent.getInput();
		childElement = child.getInput();
		if (parentElement == null || childElement == null) {
			return false;
		}

		Node ancestor = ((NodeImpl) parentElement).getCommonAncestor(childElement);
		if (ancestor != null && (ancestor.equals(childElement))) {
			return false;
		}

		List<String> children = processor.getChildNames(parentElement);
		return children.contains(childElement.getNodeName());
	}

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		parent.addChild(child);

		Object part = viewer.getEditPartRegistry().get(child);
		if (part instanceof EditPart) {
			viewer.flush();
			viewer.reveal((EditPart) part);
			viewer.select((EditPart) part);
		}
	}

	/**
	 * Returns the StructuredActivity that is the parent
	 * @return the parent
	 */
	public StructuredActivity getParent() {
		return parent;
	}

	/**
	 * Sets the child to the passed Activity
	 * @param subpart the child
	 */
	public void setChild(Activity newChild) {
		child = newChild;
	}

	/**
	 * Sets the parent to the passed StructuredActiivty
	 * @param newParent the parent
	 */
	public void setParent(StructuredActivity newParent) {
		parent = newParent;
	}

}
