package com.zebra.datawedgeprofileenums;

public enum SC_E_PRESENTATION_MODE_SENSITIVITY {
    LOW("80"),
    MEDIUM("120"),
    HIGH("190");

    private String enumString;
    private SC_E_PRESENTATION_MODE_SENSITIVITY(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
