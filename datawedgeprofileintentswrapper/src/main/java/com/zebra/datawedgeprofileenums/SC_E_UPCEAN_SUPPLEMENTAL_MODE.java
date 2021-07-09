package com.zebra.datawedgeprofileenums;

public enum SC_E_UPCEAN_SUPPLEMENTAL_MODE {
    NO_SUPPLEMENTALS("0"),
    SUPPLEMENTAL_ALWAYS("1"),
    SUPPLEMENTAL_AUTO("2"),
    SUPPLEMENTAL_SMART("3"),
    SUPPLEMENTAL_378_379("4"),
    SUPPLEMENTAL_978_979("5"),
    SUPPLEMENTAL_414_419_434_439("6"),
    SUPPLEMENTAL_977("7");

    private String enumString;

    private SC_E_UPCEAN_SUPPLEMENTAL_MODE(String confName) {
        this.enumString = confName;
    }

    @Override
    public String toString() {
        return enumString;
    }

}
