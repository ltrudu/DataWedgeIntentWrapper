package com.zebra.datawedgeprofileenums;

public enum SC_E_AIM_MODE {
    ON("1"),
    OFF("0");

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
