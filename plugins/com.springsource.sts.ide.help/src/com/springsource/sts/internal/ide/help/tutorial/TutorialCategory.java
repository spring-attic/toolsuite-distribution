/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.tutorial;

import java.util.ArrayList;
import java.util.List;

import org.springsource.ide.eclipse.commons.content.core.ContentItem;


/**
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @author Terry Denney
 */
public class TutorialCategory {

	private final String id;

	private final String name;

	private final List<ContentItem> tutorials;

	public TutorialCategory(String id, String name) {
		this.id = id;
		this.name = name;
		this.tutorials = new ArrayList<ContentItem>();
	}

	public void addTutorial(ContentItem tutorial) {
		tutorials.add(tutorial);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ContentItem[] getTutorials() {
		return tutorials.toArray(new ContentItem[0]);
	}

	public boolean hasTutorials() {
		return !tutorials.isEmpty();
	}

	public void removeTutorial(ContentItem tutorial) {
		tutorials.remove(tutorial);
	}

}
