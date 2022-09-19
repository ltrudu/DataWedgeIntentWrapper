package com.zebra.datawedgeprofileenums;

public enum SC_E_AIM_MODE {
    ON("on"),
    OFF("off");

    private String enumString;
    private SC_E_AIM_MODE(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
