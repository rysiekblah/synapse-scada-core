package com.synapse.scada;

import com.synapse.scada.core.Server;
import com.synapse.scada.core.ServerShutDown;
import com.synapse.scada.core.SynapseException;
import com.synapse.scada.core.SynapseServer;
import com.synapse.scada.ws.SynapseWSImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.xml.ws.Endpoint;
import java.rmi.ServerException;

/**
 * Created by tomek on 5/29/14.
 */
//public class Service {
//
//    public static void main(String args[]) {
//        final String url = "http://localhost:8888/synapse";
//        System.out.println("Publishing SynapseWS at endpoint " + url);
//        Endpoint.publish(url, new SynapseWSImpl());
//
//        SynapseServer server = new SynapseServer();
//        try {
//            server.create();
//            server.start();
//        } catch (SynapseException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}

public class Service extends Thread {

    /** The LOG. */
    static Logger LOG = Logger.getLogger(Service.class.getName());

    /** The server body. */
    Server synapseCore;

    /**
     * Instantiates a new server.
     *
     * @param server the body
     */
    public Service(Server server) {
        synapseCore = server;
    }

    /**
     * Inits the.
     *
     * @throws ServerException the server exception
     */
    public void init() throws ServerException {

        LOG.debug("Server initialization");

        try {
            synapseCore.create();
        } catch (SynapseException e) {
            LOG.error("Server initialization FAILED");
            throw new ServerException(e.getMessage());
        }
    }

    /**
     * Run.
     */
    public void run() {

        LOG.info("Start synapse server");

        try {

            synapseCore.start();

        } catch (SynapseException e) {
            LOG.error("Server startup FAILED", e);
        }
    }

    public void estabWebService() {
        final String url = "http://localhost:8888/synapse";
        System.out.println("Publishing SynapseWS at endpoint " + url);
        Endpoint.publish(url, new SynapseWSImpl());
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        PropertyConfigurator.configure("log.properties");
        Server server = new SynapseServer();
        Service service = new Service(server);
        try {
            service.init();
            Runtime rt = Runtime.getRuntime();
            rt.addShutdownHook(new ServerShutDown(server));
            service.start();
            service.estabWebService();
        } catch (ServerException e) {
            LOG.error("Server FAILED", e);
        }
    }
}
