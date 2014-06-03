package com.synapse.scada;

import com.synapse.scada.core.SynapseException;
import com.synapse.scada.core.SynapseServer;
import com.synapse.scada.ws.SynapseWSImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by tomek on 5/29/14.
 */
public class Main {

    public static void main(String args[]) {
        final String url = "http://localhost:8888/synapse";
        System.out.println("Publishing SynapseWS at endpoint " + url);
        Endpoint.publish(url, new SynapseWSImpl());

        SynapseServer server = new SynapseServer();
        try {
            server.create();
            server.start();
        } catch (SynapseException e) {
            e.printStackTrace();
        }

    }

}
