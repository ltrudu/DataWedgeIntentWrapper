package com.zebra.datawedgeprofileenums;

public enum SC_E_CODE_ID_TYPE {
    CODE_ID_TYPE_NONE("0"),
    CODE_ID_TYPE_AIM("1"),
    CODE_ID_TYPE_SYMBOL("2");

    private String enumString;
    private SC_E_CODE_ID_TYPE(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
