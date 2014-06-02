package com.synapse.scada.ws;

import com.synapse.scada.config.Area;
import com.synapse.scada.config.SubArea;
import com.synapse.scada.config.SystemConfig;
import com.synapse.scada.config.Unit;

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
        return null;
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
    public String getVersion() {
        if (wsContext == null) {
            throw new RuntimeException("WS context null");
        }
        System.out.println("ContextMsg: " + wsContext.getMessageContext().toString());
        return "1.0.0";
    }
}
