package com.synapse.scada.ws;

import com.synapse.scada.client.Client;
import com.synapse.scada.config.Area;
import com.synapse.scada.config.SubArea;
import com.synapse.scada.config.SystemConfig;
import com.synapse.scada.config.Unit;
import com.synapse.scada.core.SystemAdapter;
import com.synapse.scada.core.UnitState;
import com.synapse.scada.core.jmx.SynapseJMXException;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

/**
 * Created by tomek on 5/29/14.
 *
 * Service Implementation Bean (SIB) for Synapse system.
 *
 */
@WebService(endpointInterface = "com.synapse.scada.ws.SynapseWS")
public class SynapseWSImpl implements SynapseWS {

    @Resource
    WebServiceContext wsContext;

    @Override
    public SystemConfig getSystem() {
        SystemConfig config = SystemAdapter.getInstance().getConfig();
        System.out.println("Config: " + config);
        return config;
    }

    @Override
    public Area getArea(int areaId) {
        return null;
    }

    @Override
    public SubArea getSubArea(int areaId, int subAreaId) {
        return null;
    }

    @Override
    public Unit getUnit(int unitId) {
        return null;
    }

    @Override
    public String getUnitState(String elementName, String id) {

        String state = SystemAdapter.getInstance().getUnitState(elementName, id);

        return state;
    }

    @Override
    public void setUnitState(String elementName, String id, UnitState state) {
        if (state == null) {
            throw new RuntimeException("unit state is null");
        }

        try {
            state.changeState(elementName, id);
        } catch (SynapseJMXException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getVersion() {
        if (wsContext == null) {
            throw new RuntimeException("WS context null");
        }
        System.out.println(" ##### " + hashCode() + " -- ContextMsg: " + wsContext.getMessageContext().toString());
        return "1.0.0";
    }
}
