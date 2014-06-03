package com.synapse.scada.core;

import org.apache.log4j.Logger;

/**
 * Created by tomek on 6/3/14.
 */
public class ServerShutDown extends Thread {

    /** The LOG. */
    static Logger LOG = Logger.getLogger(ServerShutDown.class);

    /** The body. */
    Server body;

    /**
     * Instantiates a new server shut down.
     *
     * @param body the body
     */
    public ServerShutDown(Server body) {
        LOG.debug("Creation shodown hook");
        this.body = body;
    }

    /* (non-Javadoc)
* @see java.lang.Thread#run()
*/
    public void run() {
        LOG.debug("Run shutdown hook");
        try {
            body.stop();
        } catch (SynapseException e) {
            LOG.error("Server deactivation FAILED", e);
        }
        LOG.info("Server has been stoped and cleaned");
    }
}
