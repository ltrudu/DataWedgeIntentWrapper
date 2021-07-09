package com.zebra.datawedgeprofileenums;

/*
Available scanners list
Default is Internal Imager
 */
public enum SC_E_SCANNER_IDENTIFIER
{
    AUTO("AUTO"), //Automatic scanner selection
    INTERNAL_IMAGER("INTERNAL_IMAGER"), //Built-in imager scanner
    INTERNAL_LASER("INTERNAL_LASER"), //Built-in laser scanner
    INTERNAL_CAMERA("INTERNAL_CAMERA"), //Built-in camera scanner
    SERIAL_SSI("SERIAL_SSI"), //Pluggable Z-back scanner for ET50/ET55
    BLUETOOTH_SSI("BLUETOOTH_SSI"), //RS507 Bluetooth scanner
    BLUETOOTH_RS6000("BLUETOOTH_RS6000"), //RS6000 Bluetooth scanner
    BLUETOOTH_DS3678("BLUETOOTH_DS3678"), //DS3678 Bluetooth scanner
    PLUGABLE_SSI("PLUGABLE_SSI"), //Serial SSI scanner RS429 (for use with WT6000)
    PLUGABLE_SSI_RS5000("PLUGABLE_SSI_RS5000"), //Serial SSI scanner RS5000 (for use with WT6000)
    USB_SSI_DS3608("USB_SSI_DS3608"); //DS3608 pluggable USB scanner

    private String enumString;
    private SC_E_SCANNER_IDENTIFIER(String confName)
    {
        this.enumString = confName;
    }

    @Override
    public String toString()
    {
        return this.name();
    }

    public static SC_E_SCANNER_IDENTIFIER fromString(String identifier)
    {
        switch(identifier)
        {
            case "AUTO":
                return AUTO;
            case "INTERNAL_IMAGER":
                return INTERNAL_IMAGER;
            case "INTERNAL_LASER":
                return INTERNAL_LASER;
            case "INTERNAL_CAMERA":
                return INTERNAL_CAMERA;
            case "SERIAL_SSI":
                return SERIAL_SSI;
            case "BLUETOOTH_SSI":
                return BLUETOOTH_SSI;
            case "BLUETOOTH_RS6000":
                return BLUETOOTH_RS6000;
            case "BLUETOOTH_DS3678":
                return BLUETOOTH_DS3678;
            case "PLUGABLE_SSI":
                return PLUGABLE_SSI;
            case "PLUGABLE_SSI_RS5000":
                return PLUGABLE_SSI_RS5000;
            case "USB_SSI_DS3608":
                return USB_SSI_DS3608;
        }
        return AUTO;
    }
}
