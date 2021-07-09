package com.zebra.datawedgeprofileenums;

public enum SC_E_CODE11_VERIFY_CHECK_DIGIT {
    NO_CHECK_DIGIT("0"),
    ONE_CHECK_DIGIT("1"),
    TWO_CHECK_DIGITS("2");


    private String enumString;
    private SC_E_CODE11_VERIFY_CHECK_DIGIT(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
