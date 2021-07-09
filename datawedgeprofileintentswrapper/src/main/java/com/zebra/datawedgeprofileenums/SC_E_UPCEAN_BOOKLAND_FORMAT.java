package com.zebra.datawedgeprofileenums;

public enum SC_E_UPCEAN_BOOKLAND_FORMAT {
    FORMAT_ISBN_10("0"),
    FORMAT_ISBN_13("1");

    private String enumString;

    private SC_E_UPCEAN_BOOKLAND_FORMAT(String confName) {
        this.enumString = confName;
    }

    @Override
    public String toString() {
        return enumString;
    }
}
