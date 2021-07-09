package com.zebra.datawedgeprofileenums;

public enum SC_E_MSI_CHECK_DIGIT_SCHEME {
    MOD_11_10("0"),
    MOD_10_10("1");


    private String enumString;
    private SC_E_MSI_CHECK_DIGIT_SCHEME(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
