package com.springsource.sts.wizard.template.infrastructure;

import java.util.ArrayList;
import java.util.List;

public class TemplateCategory implements ITemplateElement {

	private final String name;

	private final List<ITemplateElement> children;

	public TemplateCategory(String name) {
		this.name = name;
		this.children = new ArrayList<ITemplateElement>();
	}

	public String getName() {
		return name;
	}

	public void addChild(ITemplateElement element) {
		this.children.add(element);
	}

	public List<ITemplateElement> getChildren() {
		return this.children;
	}

}
