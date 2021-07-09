package com.zebra.datawedgeprofileenums;

public enum SC_E_LINK_MODE {
    LINK_FLAG_IGNORED("0"),
    ALWAYS_LINKED("1"),
    AUTO_DISCRIMINATE("2");

    private String enumString;
    private SC_E_LINK_MODE(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
