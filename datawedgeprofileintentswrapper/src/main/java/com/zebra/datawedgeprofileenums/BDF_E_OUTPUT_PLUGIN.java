package com.zebra.datawedgeprofileenums;

public enum BDF_E_OUTPUT_PLUGIN {
    INTENT("INTENT"),
    KEYSTROKE("KEYSTROKE"),
    IP("IP");

    private String enumString;
    BDF_E_OUTPUT_PLUGIN(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
