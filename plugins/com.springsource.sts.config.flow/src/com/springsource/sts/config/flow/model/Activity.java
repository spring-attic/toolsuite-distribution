/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public abstract class Activity extends AbstractConfigFlowModelElement {

	static final long serialVersionUID = 1;

	protected List<Transition> incomings = new ArrayList<Transition>();

	protected List<Transition> outgoings = new ArrayList<Transition>();

	private int sortIndex;

	public Activity() {
		super();
	}

	public Activity(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	public void addIncoming(Transition transition) {
		if (!incomings.contains(transition)) {
			incomings.add(transition);
		}
		fireStructureChange(INCOMINGS, transition);
	}

	public void addOutgoing(Transition transition) {
		if (!outgoings.contains(transition)) {
			outgoings.add(transition);
		}
		fireStructureChange(OUTGOINGS, transition);
	}

	public List<Transition> getIncomingTransitions() {
		return incomings;
	}

	protected List<Transition> getIncomingTransitionsFromXml() {
		List<Transition> list = new ArrayList<Transition>();
		List<Activity> registry = getDiagram().getModelRegistry();
		for (Activity activity : registry) {
			if (!(activity instanceof ParallelActivity)) {
				getDiagram().getTransitionsFromXml(this, activity, list, getPrimaryIncomingAttributes(), true, true);
				getDiagram().getTransitionsFromXml(this, activity, list, getSecondaryIncomingAttributes(), true, false);
			}
		}
		return list;
	}

	public List<Transition> getOutgoingTransitions() {
		return outgoings;
	}

	protected List<Transition> getOutgoingTransitionsFromXml() {
		List<Transition> list = new ArrayList<Transition>();
		List<Activity> registry = getDiagram().getModelRegistry();
		for (Activity activity : registry) {
			if (!(activity instanceof ParallelActivity)) {
				getDiagram().getTransitionsFromXml(this, activity, list, getPrimaryOutgoingAttributes(), false, true);
				getDiagram()
						.getTransitionsFromXml(this, activity, list, getSecondaryOutgoingAttributes(), false, false);
			}
		}
		return list;
	}

	/**
	 * Implementers should override to return a list of attributes that will be
	 * displayed as solid-lined incoming connections.
	 * 
	 * @return list of attribute names
	 */
	public List<String> getPrimaryIncomingAttributes() {
		return new ArrayList<String>();
	}

	/**
	 * Implementers should override to return a list of attributes that will be
	 * displayed as solid-lined outgoing connections.
	 * 
	 * @return list of attribute names
	 */
	public List<String> getPrimaryOutgoingAttributes() {
		return new ArrayList<String>();
	}

	/**
	 * Implementers should override to return a list of attributes that will be
	 * displayed as dash-lined incoming connections.
	 * 
	 * @return list of attribute names
	 */
	public List<String> getSecondaryIncomingAttributes() {
		return new ArrayList<String>();
	}

	/**
	 * Implementers should override to return a list of attributes that will be
	 * displayed as dash-lined outgoing connections.
	 * 
	 * @return list of attribute names
	 */
	public List<String> getSecondaryOutgoingAttributes() {
		return new ArrayList<String>();
	}

	public int getSortIndex() {
		if (getInput() != null) {
			return getInput().getStartOffset();
		}
		return -1;
	}

	public void removeIncoming(Transition transition) {
		incomings.remove(transition);
		fireStructureChange(INCOMINGS, transition);
	}

	public void removeOutgoing(Transition transition) {
		outgoings.remove(transition);
		fireStructureChange(OUTGOINGS, transition);
	}

	public void setSortIndex(int i) {
		sortIndex = i;
	}

	protected void updateTransitionsFromXml() {
		getIncomingTransitionsFromXml();
		getOutgoingTransitionsFromXml();
	}

}
