package com.zebra.datawedgeprofileenums;

public enum INT_E_DELIVERY {
    START_ACTIVITY("0"),
    START_SERVICE("1"),
    BROADCAST("2");

    private String enumString;
    private INT_E_DELIVERY(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
