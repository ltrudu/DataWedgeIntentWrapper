package com.zebra.datawedgeprofileenums;

public enum SC_E_INVERSE1DMODE_MODE {
    DISABLE("0"),
    ENABLE("1"),
    AUTO("2");

    private String enumString;
    private SC_E_INVERSE1DMODE_MODE(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
