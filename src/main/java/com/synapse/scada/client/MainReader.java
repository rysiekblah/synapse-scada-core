package com.synapse.scada.client;

public class MainReader {

	public static void main(String [] args) {
		JMXSettingsReader rd = new JMXSettingsReader();
		try {
			rd.parse(null);
			System.out.println(rd.getHost());
		} catch (SynapseClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
