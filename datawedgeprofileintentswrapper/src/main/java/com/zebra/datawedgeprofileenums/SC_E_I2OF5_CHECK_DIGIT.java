package com.zebra.datawedgeprofileenums;

public enum SC_E_I2OF5_CHECK_DIGIT {
    NO_CHECK_DIGIT("0"),
    USS_CHECK_DIGIT("1"),
    OPCC_CHECK_DIGIT("2");

    private String enumString;
    private SC_E_I2OF5_CHECK_DIGIT(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
