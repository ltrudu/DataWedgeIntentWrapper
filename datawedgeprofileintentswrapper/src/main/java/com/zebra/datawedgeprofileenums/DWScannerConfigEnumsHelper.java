package com.zebra.datawedgeprofileenums;

public class DWScannerConfigEnumsHelper {

    /////////////////////////////////////////////////////////////////////////////////////////
    // MAIN Bundle
    /////////////////////////////////////////////////////////////////////////////////////////
    /*
    Config mode, describe how the profile will be processed
     */
    public static String MB_GetConfigMode(MB_E_CONFIG_MODE configMode) {
        String selectedConfigMode = "";
        switch (configMode) {
            case CREATE_IF_NOT_EXIST: //Creates the Profile if string in PROFILE_NAME is not present on device
                selectedConfigMode = "CREATE_IF_NOT_EXIST";
                break;
            case OVERWRITE: // If Profile exists, resets all options to default, then configures specified settings
                selectedConfigMode = "OVERWRITE";
                break;
            case UPDATE: //Updates only specified settings
                selectedConfigMode = "UPDATE";
                break;
        }
        return selectedConfigMode;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    // BARCODE Plugin... THE BIG ONE !!!!
    /////////////////////////////////////////////////////////////////////////////////////////

}