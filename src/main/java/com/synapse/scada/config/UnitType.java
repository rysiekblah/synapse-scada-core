package com.synapse.scada.config;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * Created by tomek on 6/9/14.
 */
@XmlEnum
public enum UnitType {
    @XmlEnumValue("boolean")
    BOOLEAN("boolean"),
    @XmlEnumValue("number")
    NUMBER("number");

    public String type;

    UnitType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
