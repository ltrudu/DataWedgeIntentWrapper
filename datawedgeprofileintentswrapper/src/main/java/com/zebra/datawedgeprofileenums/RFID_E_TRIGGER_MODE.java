package com.zebra.datawedgeprofileenums;

public enum RFID_E_TRIGGER_MODE {
    IMMEDIATE("0"), CONTINUOUS("1");

    private final String code;
    RFID_E_TRIGGER_MODE(String code) { this.code = code; }

    @Override
    public String toString() { return code; }
}
