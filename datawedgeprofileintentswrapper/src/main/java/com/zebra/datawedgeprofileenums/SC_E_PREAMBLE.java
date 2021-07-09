package com.zebra.datawedgeprofileenums;

public enum SC_E_PREAMBLE {
    PREAMBLE_NONE("0"),
    PREAMBLE_SYS_CHAR("1"),
    PREAMBLE_COUNTRY_AND_SYS_CHAR("2");

    private String enumString;
    private SC_E_PREAMBLE(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}