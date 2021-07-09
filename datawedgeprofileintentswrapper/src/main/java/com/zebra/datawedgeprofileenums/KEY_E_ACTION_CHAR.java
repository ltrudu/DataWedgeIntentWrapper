package com.zebra.datawedgeprofileenums;

public enum KEY_E_ACTION_CHAR {
    ASCII_NO_VALUE("NONE"),
    ASCII_TAB_VALUE("TAB"),
    ASCII_LF_VALUE("LF"),
    ASCII_CR_VALUE("CR");

    private String enumString;
    private KEY_E_ACTION_CHAR(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
