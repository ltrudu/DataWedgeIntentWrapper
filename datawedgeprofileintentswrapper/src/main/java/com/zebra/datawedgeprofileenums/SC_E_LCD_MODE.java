package com.zebra.datawedgeprofileenums;

public enum SC_E_LCD_MODE {
    DISABLED("0"),
    ENABLED("3");

    private String enumString;
    private SC_E_LCD_MODE(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
