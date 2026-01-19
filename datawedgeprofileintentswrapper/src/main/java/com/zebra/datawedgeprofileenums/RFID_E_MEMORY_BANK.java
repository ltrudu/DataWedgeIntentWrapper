package com.zebra.datawedgeprofileenums;

public enum RFID_E_MEMORY_BANK {
    NONE("0"), USER("1"), RESERVED("2"), TID("3"), EPC("4");

    private final String code;
    RFID_E_MEMORY_BANK(String code) { this.code = code; }

    @Override
    public String toString() { return code; }
}
