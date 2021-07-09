package com.zebra.datawedgeprofileenums;

/*
Different scanning modes
Default is Single
 */
public enum SC_E_SCANNINGMODE
{
    SINGLE("1"),
    UDI("2"),
    MULTIBARCODE("3");

    private String enumString;
    private SC_E_SCANNINGMODE(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}