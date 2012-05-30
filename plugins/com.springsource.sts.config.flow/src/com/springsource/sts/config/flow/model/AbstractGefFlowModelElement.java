/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
abstract public class AbstractGefFlowModelElement {

	public static final String NAME = "name", //$NON-NLS-1$
			CHILDREN = "children", //$NON-NLS-1$
			INCOMINGS = "incomings", //$NON-NLS-1$
			OUTGOINGS = "outgoings", //$NON-NLS-1$
			LINESTYLE = "linestyle", //$NON-NLS-1$
			BOUNDS = "bounds"; //$NON-NLS-1$

	transient protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	static final long serialVersionUID = 1;

	public void addPropertyChangeListener(PropertyChangeListener l) {
		listeners.addPropertyChangeListener(l);
	}

	protected void firePropertyChange(String prop, Object old, Object newValue) {
		listeners.firePropertyChange(prop, old, newValue);
	}

	protected void fireStructureChange(String prop, Object child) {
		listeners.firePropertyChange(prop, null, child);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		listeners = new PropertyChangeSupport(this);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		listeners.removePropertyChangeListener(l);
	}

}
