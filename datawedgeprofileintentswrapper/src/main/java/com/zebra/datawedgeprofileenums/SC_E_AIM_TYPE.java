package com.zebra.datawedgeprofileenums;

public enum SC_E_AIM_TYPE {
    TRIGGER("0"),
    TIMED_HOLD("1"),
    TIMED_RELEASE("2"),
    PRESS_AND_RELEASE("3"),
    CONTINUOUS_READ("4"),
    PRESS_AND_SUSTAIN("5");

    private String enumString;
    private SC_E_AIM_TYPE(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return enumString;
    }
}
