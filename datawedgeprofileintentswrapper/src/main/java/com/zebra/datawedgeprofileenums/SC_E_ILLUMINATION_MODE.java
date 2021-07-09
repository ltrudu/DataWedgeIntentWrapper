package com.zebra.datawedgeprofileenums;

public enum SC_E_ILLUMINATION_MODE {
    OFF("off"),
    ON("torch");

    private String enumString;
    private SC_E_ILLUMINATION_MODE(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
