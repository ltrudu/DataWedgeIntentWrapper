package com.zebra.datawedgeprofileenums;

public enum SC_E_UPCEAN_COUPON_REPORT {
    OLD_COUPON_REPORT_MODE("0"),
    NEW_COUPON_REPORT_MODE("1"),
    BOTH_COUPON_REPORT_MODES("2");

    private String enumString;

    private SC_E_UPCEAN_COUPON_REPORT(String confName) {
        this.enumString = confName;
    }

    @Override
    public String toString() {
        return enumString;
    }
}
