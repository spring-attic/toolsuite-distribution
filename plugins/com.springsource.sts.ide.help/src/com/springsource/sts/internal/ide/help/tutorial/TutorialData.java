/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.tutorial;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.springsource.ide.eclipse.commons.content.core.util.ContentUtil;
import org.springsource.ide.eclipse.commons.content.core.util.Descriptor;
import org.springsource.ide.eclipse.commons.content.core.util.IContentConstants;
import org.springsource.ide.eclipse.commons.core.StatusHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
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
public class TutorialData {

	public static class TutorialProject {

		private String archiveFileName;

		private String name;

		private String sampleProjectId;

		public String getArchiveFileName() {
			return archiveFileName;
		}

		public String getName() {
			return name;
		}

		public String getSampleProjectId() {
			return sampleProjectId;
		}

		public void setArchiveFileName(String archiveFileName) {
			this.archiveFileName = archiveFileName;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setSampleProjectId(String id) {
			this.sampleProjectId = id;
		}

	}

	public static class TutorialStep {

		private final Map<String, String> attributes;

		private String stepId;

		public TutorialStep(String stepId) {
			this(stepId, new HashMap<String, String>());
		}

		public TutorialStep(String stepId, Map<String, String> attributes) {
			Assert.isNotNull(attributes);
			this.attributes = attributes;
			this.stepId = stepId;
		}

		public Map<String, String> getAttributes() {
			return attributes;
		}

		public String getContextFileName() {
			return attributes.get(ATTRIBUTE_STEP_CONTEXT_FILE_NAME);
		}

		public String getContextMode() {
			return attributes.get(ATTRIBUTE_STEP_CONTEXT_MODE);
		}

		public String getEditorFileName() {
			return attributes.get(ATTRIBUTE_STEP_EDITOR_FILE_NAME);
		}

		public String getStepId() {
			return stepId;
		}

		public void setContextFileName(String contextFileName) {
			if (contextFileName == null) {
				attributes.remove(ATTRIBUTE_STEP_CONTEXT_FILE_NAME);
			}
			else {
				attributes.put(ATTRIBUTE_STEP_CONTEXT_FILE_NAME, contextFileName);
			}
		}

		public void setContextMode(String contextMode) {
			attributes.put(ATTRIBUTE_STEP_CONTEXT_MODE, contextMode);
		}

		public void setEditorFileName(String editorFileName) {
			if (editorFileName == null) {
				attributes.remove(ATTRIBUTE_STEP_EDITOR_FILE_NAME);
			}
			else {
				attributes.put(ATTRIBUTE_STEP_EDITOR_FILE_NAME, editorFileName);
			}
		}

		/**
		 * Only {@link TutorialData#refactorStepIds(Map)} shoud call this.
		 */
		void setStepId(String stepId) {
			this.stepId = stepId;
		}

	}

	public static class TutorialTask {

		private String description;

		private String summary;

		public String getDescription() {
			return description;
		}

		public String getSummary() {
			return summary;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

	}

	private static final String ATTRIBUTE_PROJECT_SAMPLE_PROJECT_ID = "id";

	private static final String ATTRIBUTE_PROJECT_NAME = "name";

	private static final String ATTRIBUTE_PROJECT_ARCHIVE_FILE_NAME = "archiveFilename";

	private static final String ATTRIBUTE_STEP_CONTEXT_FILE_NAME = "filename";

	private static final String ATTRIBUTE_STEP_CONTEXT_MODE = "mode";

	private static final String ATTRIBUTE_STEP_EDITOR_FILE_NAME = "editor";

	private static final String ATTRIBUTE_STEP_ID = "id";

	private static final String ATTRIBUTE_TASK_SUMMARY = "summary";

	private static final String TAG_PROJECT = "project";

	private static final String TAG_STEP = "step";

	private static final String TAG_TASK = "task";

	private static final String TAG_TASK_DESCRIPTION = "description";

	private static final String TAG_TUTORIAL = "tutorial";

	private static final String TAG_DESCRIPTOR = "descriptor";

	private final String id;

	private final File path;

	private final List<TutorialProject> projects = new ArrayList<TutorialProject>();

	private final Map<String, TutorialStep> stepById = new LinkedHashMap<String, TutorialStep>();

	private final List<TutorialTask> tasks = new ArrayList<TutorialTask>();

	private Descriptor descriptor = new Descriptor();

	/**
	 * Constructs a local tutorial.
	 */
	public TutorialData(File path) {
		Assert.isNotNull(path);
		this.path = path;
		this.id = path.getName();
	}

	public void addProject(TutorialProject project) {
		projects.add(project);
	}

	public void addStep(TutorialStep step) {
		Assert.isNotNull(step);
		if (stepById.get(step.getStepId()) != null) {
			throw new IllegalArgumentException("Step with id \"" + step.getStepId() + "\" already exists.");
		}

		stepById.put(step.getStepId(), step);
	}

	public void addTask(TutorialTask task) {
		tasks.add(task);
	}

	public Descriptor getDescriptor() {
		return descriptor;
	}

	public File getFile() throws CoreException {
		return new File(path, IContentConstants.TUTORIAL_DATA_FILE_NAME);
	}

	public File getFile(String filename) throws CoreException {
		File file = new File(path, filename);
		if (!file.exists()) {
			StatusHandler.log(new Status(IStatus.WARNING, HelpPlugin.PLUGIN_ID, "Tutorial failed to access file \""
					+ file + "\""));
			return null;
		}
		return file;
	}

	public File getPath() {
		return path;
	}

	public TutorialProject[] getProjects() {
		return projects.toArray(new TutorialProject[0]);
	}

	public TutorialStep getStep(String stepId) {
		return stepById.get(stepId);
	}

	public TutorialTask getTask() {
		if (!tasks.isEmpty()) {
			return tasks.get(0);
		}
		return null;
	}

	public void read() throws CoreException {
		File file = getFile();
		DocumentBuilder documentBuilder = ContentUtil.createDocumentBuilder();
		Document document = null;
		try {
			document = documentBuilder.parse(file);
			Element rootNode = document.getDocumentElement();
			if (rootNode == null) {
				throw new SAXException("No root node");
			}
			if (!TAG_TUTORIAL.equals(rootNode.getNodeName())) {
				throw new SAXException("Not a a valid tutorial");
			}
			readTutorial(rootNode);
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

	private void readProjectNode(Node node) throws SAXException {
		String projectId = ContentUtil.getAttributeValue(node, ATTRIBUTE_PROJECT_SAMPLE_PROJECT_ID);
		if (projectId == null) {
			String archiveFileName = ContentUtil.getAttributeValue(node, ATTRIBUTE_PROJECT_ARCHIVE_FILE_NAME);
			String name = ContentUtil.getAttributeValue(node, ATTRIBUTE_PROJECT_NAME);
			if (archiveFileName == null || name == null) {
				throw new SAXException("The project record is invalid");
			}

			TutorialProject project = new TutorialProject();
			project.setArchiveFileName(archiveFileName);
			project.setName(name);
			projects.add(project);
		}
		else {
			TutorialProject project = new TutorialProject();
			project.setSampleProjectId(projectId);
			projects.add(project);
		}
	}

	private void readStepNode(Node node) throws SAXException {
		String stepId = ContentUtil.getAttributeValue(node, ATTRIBUTE_STEP_ID);
		if (stepId == null) {
			throw new SAXException("Missing attribute for step id");
		}

		Map<String, String> attributes = new HashMap<String, String>();
		NamedNodeMap nodeAttributes = node.getAttributes();
		for (int i = 0; i < nodeAttributes.getLength(); i++) {
			Node attributeNode = nodeAttributes.item(i);
			if (!ATTRIBUTE_STEP_ID.equals(attributeNode.getNodeName())) {
				attributes.put(attributeNode.getNodeName(), attributeNode.getNodeValue());
			}
		}

		TutorialStep step = new TutorialStep(stepId, attributes);
		stepById.put(stepId, step);
	}

	private void readTaskNode(Node node) throws SAXException {
		String summary = ContentUtil.getAttributeValue(node, ATTRIBUTE_TASK_SUMMARY);
		if (summary == null) {
			throw new SAXException("Missing summary attribute for task");
		}

		String description = null;
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node childNode = children.item(i);
			if (TAG_TASK_DESCRIPTION.equals(childNode.getNodeName())) {
				description = ContentUtil.getTextValue(childNode);
			}
		}

		TutorialTask data = new TutorialTask();
		data.setSummary(summary);
		data.setDescription(description);
		tasks.add(data);
	}

	void readTutorial(Node node) throws SAXException {
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node childNode = children.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				if (TAG_DESCRIPTOR.equals(childNode.getNodeName())) {
					descriptor = Descriptor.read(childNode);
				}
				else if (TAG_TASK.equals(childNode.getNodeName())) {
					readTaskNode(childNode);
				}
				else if (TAG_PROJECT.equals(childNode.getNodeName())) {
					readProjectNode(childNode);
				}
				else if (TAG_STEP.equals(childNode.getNodeName())) {
					readStepNode(childNode);
				}
				else {
					StatusHandler.log(new Status(IStatus.WARNING, HelpPlugin.PLUGIN_ID, "Unknown tag \""
							+ childNode.getNodeName() + "\" in tutorial \"" + id + "\""));
				}
			}
		}
	}

	public void refactorStepIds(Map<String, String> newIdByOldId) {
		List<TutorialStep> steps = new ArrayList<TutorialStep>(stepById.values());
		stepById.clear();
		for (TutorialStep step : steps) {
			String newId = newIdByOldId.get(step.getStepId());
			if (newId != null) {
				step.setStepId(newId);
			}
			stepById.put(step.getStepId(), step);
		}
	}

	public void validate() throws CoreException {
		if (projects.isEmpty()) {
			throw new CoreException(new Status(Status.ERROR, HelpPlugin.PLUGIN_ID, "The tutorial \"" + id
					+ "\" does not specify a project"));
		}
		if (tasks.isEmpty()) {
			throw new CoreException(new Status(Status.ERROR, HelpPlugin.PLUGIN_ID, "The tutorial \"" + id
					+ "\" does not specify a task"));
		}
	}

	public void write() throws CoreException {
		File file = getFile();
		DocumentBuilder documentBuilder = ContentUtil.createDocumentBuilder();
		Transformer serializer = ContentUtil.createTransformer();
		Document document = documentBuilder.newDocument();
		writeDocument(document);
		DOMSource source = new DOMSource(document);
		try {
			StreamResult target = new StreamResult(file);
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.transform(source, target);
		}
		catch (TransformerException e) {
			throw new CoreException(new Status(Status.ERROR, HelpPlugin.PLUGIN_ID,
					"Could not write initialization data for tutorial \"" + id + "\""));
		}
	}

	private void writeDocument(Document document) {
		Element rootNode = document.createElement(TAG_TUTORIAL);
		document.appendChild(rootNode);

		Element descriptorNode = document.createElement(TAG_DESCRIPTOR);
		rootNode.appendChild(descriptorNode);
		Descriptor.write(descriptor, descriptorNode);

		for (TutorialProject project : projects) {
			Element projectNode = document.createElement(TAG_PROJECT);
			rootNode.appendChild(projectNode);
			writeProject(document, projectNode, project);
		}

		for (TutorialTask task : tasks) {
			Element taskNode = document.createElement(TAG_TASK);
			rootNode.appendChild(taskNode);
			writeTask(document, taskNode, task);
		}

		for (TutorialStep step : stepById.values()) {
			Element stepNode = document.createElement(TAG_STEP);
			rootNode.appendChild(stepNode);
			writeStep(document, stepNode, step);
		}
	}

	private void writeProject(Document document, Element projectNode, TutorialProject project) {
		if (project.getSampleProjectId() != null) {
			projectNode.setAttribute(ATTRIBUTE_PROJECT_SAMPLE_PROJECT_ID, project.getSampleProjectId());
		}
		else {
			projectNode.setAttribute(ATTRIBUTE_PROJECT_ARCHIVE_FILE_NAME, project.getArchiveFileName());
			projectNode.setAttribute(ATTRIBUTE_PROJECT_NAME, project.getName());
		}
	}

	private void writeStep(Document document, Element node, TutorialStep task) {
		node.setAttribute(ATTRIBUTE_STEP_ID, task.getStepId());
		for (String key : task.getAttributes().keySet()) {
			node.setAttribute(key, task.getAttributes().get(key));
		}

	}

	private void writeTask(Document document, Element taskNode, TutorialTask task) {
		taskNode.setAttribute(ATTRIBUTE_TASK_SUMMARY, task.getSummary());

		Element descriptionNode = document.createElement(TAG_TASK_DESCRIPTION);
		descriptionNode.setTextContent(task.getDescription());
		taskNode.appendChild(descriptionNode);
	}

}
