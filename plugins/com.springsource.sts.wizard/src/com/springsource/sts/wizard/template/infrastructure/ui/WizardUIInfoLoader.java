/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.template.infrastructure.ui;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class WizardUIInfoLoader {
	public WizardUIInfo load(InputStream jsonDescriptionInputStream) throws IOException {
		return load(new InputStreamReader(jsonDescriptionInputStream));
	}

	public WizardUIInfo load(Reader jsonDescriptionReader) throws IOException {
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.alias("info", WizardUIInfo.class);
		xstream.alias("element", WizardUIInfoElement.class);
		xstream.alias("page", WizardUIInfoPage.class);
		return (WizardUIInfo) xstream.fromXML(jsonDescriptionReader);
	}

	public WizardUIInfo load(String jsonDescriptionFile) throws IOException {
		return load(new FileReader(jsonDescriptionFile));
	}

}
