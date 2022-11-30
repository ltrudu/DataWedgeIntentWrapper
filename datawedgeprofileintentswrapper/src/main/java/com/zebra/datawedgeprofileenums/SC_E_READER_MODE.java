package com.zebra.datawedgeprofileenums;

public enum SC_E_READER_MODE {
    TRIGGERED_MODE("0"),
    PRESENTATION_MODE("7");

    private String enumString;
    private SC_E_READER_MODE(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
