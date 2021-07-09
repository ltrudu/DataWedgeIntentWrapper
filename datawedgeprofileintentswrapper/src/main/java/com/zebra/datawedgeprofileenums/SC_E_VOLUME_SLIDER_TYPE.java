package com.zebra.datawedgeprofileenums;

public enum SC_E_VOLUME_SLIDER_TYPE {

    RINGER("0"),
    MUSIC_AND_MEDIA("1"),
    ALARMS("2"),
    NOTIFICATION("3");

    private String enumString;
    private SC_E_VOLUME_SLIDER_TYPE(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
