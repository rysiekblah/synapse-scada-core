package com.synapse.scada.ws;

import com.synapse.scada.config.Area;
import com.synapse.scada.config.SubArea;
import com.synapse.scada.config.SystemConfig;
import com.synapse.scada.config.Unit;

import javax.jws.WebService;

/**
 * Created by tomek on 5/29/14.
 */
@WebService(endpointInterface = "com.synapse.scada.ws.SynapseWS")
public class SynapseWSImpl implements SynapseWS {
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
        return "1.0.0";
    }
}
