package com.zebra.datawedgeprofileenums;

public enum SC_E_AIM_TYPE {
    TRIGGER("0"),
    TIMED_HOLD("1"),
    TIMED_RELEASE("2"),
    PRESS_AND_RELEASE("3"),
    PRESENTATION("4"),
    CONTINUOUS_READ("5"),
    PRESS_AND_SUSTAIN("6"),
    PRESS_AND_CONTINUE("7"),
    TIMED_CONTINUOUS("8");

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
