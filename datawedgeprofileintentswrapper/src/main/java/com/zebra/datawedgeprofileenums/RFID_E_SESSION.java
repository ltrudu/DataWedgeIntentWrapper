package com.zebra.datawedgeprofileenums;

public enum RFID_E_SESSION {
    S0("0"), S1("1"), S2("2"), S3("3");

    private final String code;
    RFID_E_SESSION(String code) { this.code = code; }

    @Override
    public String toString() { return code; }
}
