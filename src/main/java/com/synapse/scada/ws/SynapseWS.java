package com.synapse.scada.ws;

import com.synapse.scada.config.Area;
import com.synapse.scada.config.SubArea;
import com.synapse.scada.config.SystemConfig;
import com.synapse.scada.config.Unit;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by tomek on 5/29/14.
 *
 * Service Endpoint Interface (SEI) for Scada system.
 */
@WebService
public interface SynapseWS {

    @WebMethod
    SystemConfig getSystem();

    @WebMethod()
    Area getArea(int areaId);

    @WebMethod
    SubArea getSubArea(int areaId, int subAreaId);

    @WebMethod
    Unit getUnit(int unitId);

    @WebMethod
    String getUnitState(String elementName, String id);

    @WebMethod
    String getVersion();

}
