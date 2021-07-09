package com.zebra.datawedgeprofileenums;

public enum SC_E_LINEAR_SECURITY_LEVEL {
    SECURITY_SHORT_OR_CODABAR("1"),
    SECURITY_ALL_TWICE("2"),
    SECURITY_LONG_AND_SHORT("3"),
    SECURITY_ALL_THRICE("4");

    private String enumString;
    private SC_E_LINEAR_SECURITY_LEVEL(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
