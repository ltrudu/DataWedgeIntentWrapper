package com.zebra.datawedgeprofileenums;

public enum SC_E_CONCAT_MODE {
    CONCAT_MODE_NEVER("0"),
    CONCAT_MODE_ALWAYS("1"),
    CONCAT_MODE_AUTO("2");

    private String enumString;
    private SC_E_CONCAT_MODE(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
