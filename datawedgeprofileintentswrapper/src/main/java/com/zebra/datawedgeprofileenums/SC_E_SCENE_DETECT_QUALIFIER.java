package com.zebra.datawedgeprofileenums;

public enum SC_E_SCENE_DETECT_QUALIFIER {
    NONE("0"),
    PROXIMITY_SENSOR_INPUT("1");

    private String enumString;
    private SC_E_SCENE_DETECT_QUALIFIER(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
