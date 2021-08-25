package com.zebra.datawedgeprofileenums;

public enum SC_S_SCANNER_STATUS {
    WAITING("WAITING"),
    SCANNING("SCANNING"),
    CONNECTED("CONNECTED"),
    DISCONNECTED("DISCONNECTED"),
    IDLE ("IDLE "),
    DISABLED ("DISABLED ");

    private String enumString;
    private SC_S_SCANNER_STATUS(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
