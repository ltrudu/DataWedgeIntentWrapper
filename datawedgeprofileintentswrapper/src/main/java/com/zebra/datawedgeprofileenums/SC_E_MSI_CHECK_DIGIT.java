package com.zebra.datawedgeprofileenums;

public enum SC_E_MSI_CHECK_DIGIT {
    ONE_CHECK_DIGIT("0"),
    TWO_CHECK_DIGIT("1");

    private String enumString;
    private SC_E_MSI_CHECK_DIGIT(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
