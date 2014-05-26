package com.synapse.scada.client;

import java.io.InputStream;
import java.io.Serializable;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class JMXSettingsReader extends DefaultHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4991030024765946765L;
	private static final String JMXTAG = "synapseJmxService";
	private static final String JMXNAME = "name";
	private static final String JMXSERVICE = "service";
	private static final String JMXHOST = "host";
	private static final String JMXPORT = "port";

	private String name;
	private String service;
	private String host;
	private String port;

	public void parse(InputStream is) throws SynapseClientException {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp;
		try {
			sp = spf.newSAXParser();
			sp.parse(is, this);
			
		} catch (Exception e) {
			throw new SynapseClientException(e.getMessage());
		}
	}

	public String getName() {
		return name;
	}

	public String getService() {
		return service;
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals(JMXTAG)) {
			name = attributes.getValue(JMXNAME);
			service = attributes.getValue(JMXSERVICE);
			host = attributes.getValue(JMXHOST);
			port = attributes.getValue(JMXPORT);
		}
	}

}
