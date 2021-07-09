package com.zebra.datawedgeprofileenums;

public enum SC_E_CHARSET_NAME {
    ISO_8859_1("ISO-8859-1"),
    Shift_JIS("Shift_JIS"),
    UTF_8("UTF-8");

    private String enumString;
    private SC_E_CHARSET_NAME(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
