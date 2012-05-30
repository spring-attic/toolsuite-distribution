/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.tutorial;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.springsource.ide.eclipse.commons.content.core.util.ContentUtil;
import org.springsource.ide.eclipse.commons.content.core.util.IContentConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.springsource.sts.internal.ide.help.HelpPlugin;

/**
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @author Terry Denney
 */
public class CheatSheetData {

	public static class CheatSheetItem {

		private String title;

		private String stepId;

		private String commandId;

		private String description;

		public String getCommandId() {
			return commandId;
		}

		public String getDescription() {
			return description;
		}

		public String getStepId() {
			return stepId;
		}

		public String getTitle() {
			return title;
		}

		public void setCommandId(String commandId) {
			this.commandId = commandId;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public void setStepId(String stepId) {
			this.stepId = stepId;
		}

		public void setTitle(String title) {
			this.title = title;
		}

	}

	private static final String TAG_DESCRIPTION = "description";

	private static final String TAG_CHEATSHEET = "cheatsheet";

	private static final Object TAG_ITEM = "item";

	private static final String ATTRIBUTE_TITLE = "title";

	private static final Object TAG_COMMAND = "command";

	private static final String ATTRIBUTE_SERIALIZATION = "serialization";

	private static final String ATTRIBUTE_ON_ACTIVATION = "onActivation";

	private final String id;

	private final File path;

	private final Map<String, CheatSheetItem> itemByStepId = new HashMap<String, CheatSheetItem>();

	private final List<CheatSheetItem> items = new ArrayList<CheatSheetItem>();

	public CheatSheetData(File path) {
		Assert.isNotNull(path);

		this.path = path;
		this.id = path.getName();
	}

	private String getAttributeValue(Node node, String attribute) {
		Node item = node.getAttributes().getNamedItem(attribute);
		return (item != null) ? item.getNodeValue() : null;
	}

	public File getFile() throws CoreException {
		return new File(path, IContentConstants.TUTORIAL_CHEAT_SHEET_FILE_NAME);
	}

	public CheatSheetItem getItem(String stepId) {
		return itemByStepId.get(stepId);
	}

	public void read() throws CoreException {
		File file = getFile();
		DocumentBuilder documentBuilder = ContentUtil.createDocumentBuilder();
		Document document = null;
		try {
			document = documentBuilder.parse(file);
			read(document);
		}
		catch (SAXException e) {
			throw new CoreException(new Status(Status.ERROR, HelpPlugin.PLUGIN_ID,
					"Could not read initialization data for tutorial \"" + id + "\"", e));
		}
		catch (IOException e) {
			throw new CoreException(new Status(Status.ERROR, HelpPlugin.PLUGIN_ID,
					"Could not read initialization data for tutorial \"" + id + "\"", e));
		}
	}

	private void read(Document document) throws SAXException {
		Element rootNode = document.getDocumentElement();
		if (rootNode == null) {
			throw new SAXException("No root node");
		}
		if (!TAG_CHEATSHEET.equals(rootNode.getNodeName())) {
			throw new SAXException("Not a a valid cheat sheet");
		}

		NodeList children = rootNode.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node childNode = children.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				if (TAG_ITEM.equals(childNode.getNodeName())) {
					readItemNode(childNode);
				}
			}
		}
	}

	private void readItemNode(Node node) throws SAXException {
		String title = getAttributeValue(node, ATTRIBUTE_TITLE);
		if (title == null) {
			throw new SAXException("Missing title attribute for item");
		}
		String stepId = getAttributeValue(node, ATTRIBUTE_ON_ACTIVATION);

		String description = null;
		String commandId = null;
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node childNode = children.item(i);
			if (TAG_DESCRIPTION.equals(childNode.getNodeName())) {
				description = childNode.getTextContent();
			}
			else if (TAG_COMMAND.equals(childNode.getNodeName())) {
				commandId = getAttributeValue(childNode, ATTRIBUTE_SERIALIZATION);
			}
		}

		CheatSheetItem item = new CheatSheetItem();
		item.setTitle(title);
		item.setDescription(description);
		item.setCommandId(commandId);
		item.setStepId(stepId);
		if (stepId != null) {
			itemByStepId.put(stepId, item);
		}
		items.add(item);
	}

}
