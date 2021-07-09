package com.zebra.datawedgeprofileenums;

public enum SC_E_1D_MARGINLESS_DECODE_EFFORT_LEVEL {
            LEVEL_0("0"),
            LEVEL_1("1"),
            LEVEL_2("2"),
            LEVEL_3("3");

    private String enumString;
    private SC_E_1D_MARGINLESS_DECODE_EFFORT_LEVEL(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
