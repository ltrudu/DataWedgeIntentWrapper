package com.zebra.datawedgeprofileenums;

public enum SC_E_PICKLIST_MODE {
    DISABLED("0"),
    HARDWARE_PICKLIST_ENABLED("1"),
    SOFTWARE_PICKLIST_ENABLED("2");


    private String enumString;
    private SC_E_PICKLIST_MODE(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
