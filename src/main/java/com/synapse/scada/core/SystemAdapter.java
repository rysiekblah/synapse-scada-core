package com.synapse.scada.core;

import com.synapse.scada.client.JMXSettingsReader;
import com.synapse.scada.client.SynapseClientException;
import com.synapse.scada.config.SystemConfig;
import com.synapse.scada.config.Unit;
import com.synapse.scada.core.element.ElementMBean;
import com.synapse.scada.core.jmx.JmxHelper;
import com.synapse.scada.core.jmx.SynapseJMXException;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tomek on 6/5/14.
 */
public class SystemAdapter {

    private static final String DOMAIN = "EssProject";
    private String connString;
    private JMXConnector jmxc;
    private MBeanServerConnection mbsc;
    private ObjectName cfgObject;
    private ConfigurationMBean config;

    private static SystemAdapter instance;

    private SystemAdapter() {
        JMXSettingsReader reader = new JMXSettingsReader();
        try (InputStream in = new FileInputStream("/home/tomek/IdeaProjects/synapse-scada-core/server_config.xml");) {

            reader.parse(in);
            connString = "service:" + reader.getService() + "://"
                    + reader.getHost() + ":" + reader.getPort() + "/"
                    + reader.getName();
            System.out.println("conn: " + connString);
            connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SystemAdapter getInstance() {
        if (instance == null) {
            instance = new SystemAdapter();
        }
        return instance;
    }

    public void connect() throws SynapseClientException {
        try {
            jmxc = JMXConnectorFactory.connect(new JMXServiceURL(connString),
                    null);
            mbsc = jmxc.getMBeanServerConnection();
            cfgObject = ObjectName.getInstance(DOMAIN, "Configuration", "1");
            config = JMX.newMBeanProxy(mbsc, cfgObject,
                    ConfigurationMBean.class);
        } catch (Exception e) {
            throw new SynapseClientException("Connection FAILED."
                    + e.getMessage());
        }

    }

    public String getConnectionId() {
        try {
            return jmxc.getConnectionId();
        } catch (Exception e) {
            return null;
        }
    }

    public void init(ServletContext context) throws SynapseClientException {
        JMXSettingsReader reader = new JMXSettingsReader();
        reader.parse(context.getResourceAsStream("/server_config.xml"));
        connString = "service:" + reader.getService() + "://"
                + reader.getHost() + ":" + reader.getPort() + "/"
                + reader.getName();
    }

    public void close() {
        try {
            jmxc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the config x.
     *
     * @return the config x
     */
    public SystemConfig getConfig() {

        SystemConfig syscfg=null;

        try {
            syscfg = config.getConfig();
        } catch (Exception e) {

        }

        return syscfg;
    }

    public String getUnitState(String elementName, String id) {

        try {
            ObjectName name = JmxHelper.Instance().createObjectName("Element", elementName);
            System.out.println("Name: " + name);
            Integer state = JMX.newMBeanProxy(JmxHelper.Instance().getMBServer(), name, ElementMBean.class).getUnitState(Integer.parseInt(id));
            System.out.println("State: " + state);
            return state.toString();
        } catch (SynapseJMXException e) {
            e.printStackTrace();
        }

        return null;
    }
}
