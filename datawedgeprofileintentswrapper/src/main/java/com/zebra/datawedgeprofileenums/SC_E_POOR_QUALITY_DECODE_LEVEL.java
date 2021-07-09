package com.zebra.datawedgeprofileenums;

public enum SC_E_POOR_QUALITY_DECODE_LEVEL {
            SECURITY_LEVEL_0("0"),
            SECURITY_LEVEL_1("1"),
            SECURITY_LEVEL_2("2"),
            SECURITY_LEVEL_3("3");

    private String enumString;
    private SC_E_POOR_QUALITY_DECODE_LEVEL(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
