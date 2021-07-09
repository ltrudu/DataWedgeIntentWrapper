package com.zebra.datawedgeprofileintents;

/**
 * Created by Trudu Laurent on 22/11/2016.
 */
public class DataWedgeConstants {
    // Let's define some intent strings
    // This intent string contains the source of the data as a string
    public static final String SOURCE_TAG = "com.motorolasolutions.emdk.datawedge.source";
    // This intent string contains the barcode symbology as a string
    public static final String LABEL_TYPE_TAG = "com.motorolasolutions.emdk.datawedge.label_type";
    // This intent string contains the barcode data as a byte array list
    public static final String DECODE_DATA_TAG = "com.motorolasolutions.emdk.datawedge.decode_data";

    // This intent string contains the captured data as a string
    // (in the case of MSR this data string contains a concatenation of the track data)
    public static final String DATA_STRING_TAG = "com.motorolasolutions.emdk.datawedge.data_string";

    // Let's define the MSR intent strings (in case we want to use these in the future)
    public static final String MSR_DATA_TAG = "com.motorolasolutions.emdk.datawedge.msr_data";
    public static final String MSR_TRACK1_TAG = "com.motorolasolutions.emdk.datawedge.msr_track1";
    public static final String MSR_TRACK2_TAG = "com.motorolasolutions.emdk.datawedge.msr_track2";
    public static final String MSR_TRACK3_TAG = "com.motorolasolutions.emdk.datawedge.msr_track3";
    public static final String MSR_TRACK1_STATUS_TAG = "com.motorolasolutions.emdk.datawedge.msr_track1_status";
    public static final String MSR_TRACK2_STATUS_TAG = "com.motorolasolutions.emdk.datawedge.msr_track2_status";
    public static final String MSR_TRACK3_STATUS_TAG = "com.motorolasolutions.emdk.datawedge.msr_track3_status";
    public static final String MSR_TRACK1_ENCRYPTED_TAG = "com.motorolasolutions.emdk.datawedge.msr_track1_encrypted";
    public static final String MSR_TRACK2_ENCRYPTED_TAG = "com.motorolasolutions.emdk.datawedge.msr_track2_encrypted";
    public static final String MSR_TRACK3_ENCRYPTED_TAG = "com.motorolasolutions.emdk.datawedge.msr_track3_encrypted";
    public static final String MSR_TRACK1_HASHED_TAG = "com.motorolasolutions.emdk.datawedge.msr_track1_hashed";
    public static final String MSR_TRACK2_HASHED_TAG = "com.motorolasolutions.emdk.datawedge.msr_track2_hashed";
    public static final String MSR_TRACK3_HASHED_TAG = "com.motorolasolutions.emdk.datawedge.msr_track3_hashed";

    // Let's define the API intent strings for the soft scan trigger
    public static final String DWAPI_ACTION_SOFTSCANTRIGGER = "com.motorolasolutions.emdk.datawedge.api.ACTION_SOFTSCANTRIGGER";
    public static final String EXTRA_PARAMETER = "com.symbol.datawedge.api.EXTRA_PARAMETER";
    public static final String DWAPI_START_SCANNING = "START_SCANNING";
    public static final String DWAPI_STOP_SCANNING = "STOP_SCANNING";
    public static final String DWAPI_TOGGLE_SCANNING = "TOGGLE_SCANNING";

    public static final String DWAPI_ACTION_SCANNERINPUTPLUGIN = "com.symbol.datawedge.api.ACTION_SCANNERINPUTPLUGIN";
    public static final String DWAPI_PARAMETER_SCANNERINPUTPLUGIN_ENABLE = "ENABLE_PLUGIN";
    public static final String DWAPI_PARAMETER_SCANNERINPUTPLUGIN_DISABLE = "DISABLE_PLUGIN";

    public static final String NOTIFICATION_ACTION = "com.symbol.datawedge.api.NOTIFICATION_ACTION";

    public static final String NOTIFICATION_TYPE_SCANNER_STATUS = "SCANNER_STATUS";
    public static final String SCAN_STATUS_WAITING = "WAITING";
    public static final String SCAN_STATUS_SCANNING  = "SCANNING ";
    public static final String SCAN_STATUS_CONNECTED = "CONNECTED";
    public static final String SCAN_STATUS_DISCONNECTED  = "DISCONNECTED ";
    public static final String SCAN_STATUS_DISABLED  = "DISABLED ";
    public static final String NOTIFICATION_TYPE_PROFILE_SWITCH = "PROFILE_SWITCH";

    public static final String ACTION_EXTRA_REGISTER_FOR_NOTIFICATION = "com.symbol.datawedge.api.REGISTER_FOR_NOTIFICATION";
    public static final String ACTION_EXTRA_UNREGISTER_FOR_NOTIFICATION = "com.symbol.datawedge.api.UNREGISTER_FOR_NOTIFICATION";


    //  6.2 API and up Actions sent to DataWedge
    public static final String ACTION_DATAWEDGE_FROM_6_2 = "com.symbol.datawedge.api.ACTION";
    //  6.2 API and up Extras sent to DataWedge
    public static final String EXTRA_GET_ACTIVE_PROFILE = "com.symbol.datawedge.api.GET_ACTIVE_PROFILE";
    public static final String EXTRA_GET_PROFILES_LIST = "com.symbol.datawedge.api.GET_PROFILES_LIST";
    public static final String EXTRA_DELETE_PROFILE = "com.symbol.datawedge.api.DELETE_PROFILE";
    public static final String EXTRA_CLONE_PROFILE = "com.symbol.datawedge.api.CLONE_PROFILE";
    public static final String EXTRA_RENAME_PROFILE = "com.symbol.datawedge.api.RENAME_PROFILE";
    public static final String EXTRA_ENABLE_DATAWEDGE = "com.symbol.datawedge.api.ENABLE_DATAWEDGE";
    public static final String EXTRA_EMPTY = "";
    //  6.2 API and up Actions received from DataWedge
    public static final String ACTION_RESULT_DATAWEDGE_FROM_6_2 = "com.symbol.datawedge.api.RESULT_ACTION";
    //  6.2 API and up Extras received from DataWedge
    public static final String EXTRA_RESULT_GET_ACTIVE_PROFILE = "com.symbol.datawedge.api.RESULT_GET_ACTIVE_PROFILE";
    public static final String EXTRA_RESULT_GET_PROFILE_LIST = "com.symbol.datawedge.api.RESULT_GET_PROFILES_LIST";


    //  6.3 API and up Extras sent to DataWedge
    public static final String EXTRA_SOFTSCANTRIGGER_FROM_6_3 = "com.symbol.datawedge.api.SOFT_SCAN_TRIGGER";
    public static final String EXTRA_SCANNERINPUTPLUGIN_FROM_6_3 = "com.symbol.datawedge.api.SCANNER_INPUT_PLUGIN";
    public static final String EXTRA_ENUMERATESCANNERS_FROM_6_3 = "com.symbol.datawedge.api.ENUMERATE_SCANNERS";
    public static final String EXTRA_RESULT_ENUMERATE_SCANNERS_FROM_6_3 = "com.symbol.datawedge.api.RESULT_ENUMERATE_SCANNERS";
    public static final String EXTRA_SETDEFAULTPROFILE_FROM_6_3 = "com.symbol.datawedge.api.SET_DEFAULT_PROFILE";
    public static final String EXTRA_RESETDEFAULTPROFILE_FROM_6_3 = "com.symbol.datawedge.api.RESET_DEFAULT_PROFILE";
    public static final String EXTRA_SWITCHTOPROFILE_FROM_6_3 = "com.symbol.datawedge.api.SWITCH_TO_PROFILE";
    public static final String EXTRA_GET_VERSION_INFO = "com.symbol.datawedge.api.GET_VERSION_INFO";
    public static final String EXTRA_REGISTER_NOTIFICATION = "com.symbol.datawedge.api.REGISTER_FOR_NOTIFICATION";
    public static final String EXTRA_UNREGISTER_NOTIFICATION = "com.symbol.datawedge.api.UNREGISTER_FOR_NOTIFICATION";
    public static final String EXTRA_CREATE_PROFILE = "com.symbol.datawedge.api.CREATE_PROFILE";
    public static final String EXTRA_SET_CONFIG = "com.symbol.datawedge.api.SET_CONFIG";
    public static final String EXTRA_RESTORE_CONFIG = "com.symbol.datawedge.api.RESTORE_CONFIG";
    //  6.3 API and up Actions received from DataWedge
    public static final String ACTION_RESULT_NOTIFICATION = "com.symbol.datawedge.api.NOTIFICATION_ACTION";
    //  6.3 API and up Extras received from DataWedge
    public static final String EXTRA_RESULT_GET_VERSION_INFO = "com.symbol.datawedge.api.RESULT_GET_VERSION_INFO";
    public static final String EXTRA_RESULT_NOTIFICATION = "com.symbol.datawedge.api.NOTIFICATION";
    public static final String EXTRA_RESULT_ENUMERATE_SCANNERS = "com.symbol.datawedge.api.RESULT_ENUMERATE_SCANNERS";
    //  6.3 API and up Parameter keys and values associated with extras received from DataWedge
    public static final String EXTRA_KEY_APPLICATION_NAME = "com.symbol.datawedge.api.APPLICATION_NAME";
    public static final String EXTRA_KEY_NOTIFICATION_TYPE = "com.symbol.datawedge.api.NOTIFICATION_TYPE";
    public static final String EXTRA_RESULT_NOTIFICATION_TYPE = "NOTIFICATION_TYPE";
    public static final String EXTRA_KEY_VALUE_SCANNER_STATUS = "SCANNER_STATUS";
    public static final String EXTRA_KEY_VALUE_PROFILE_SWITCH = "PROFILE_SWITCH";
    public static final String EXTRA_KEY_VALUE_CONFIGURATION_UPDATE = "CONFIGURATION_UPDATE";
    public static final String EXTRA_KEY_VALUE_NOTIFICATION_STATUS = "STATUS";
    public static final String EXTRA_KEY_VALUE_NOTIFICATION_PROFILE_NAME = "PROFILE_NAME";

    //  6.4 API and up Extras sent to Datawedge
    public static final String EXTRA_GET_DATAWEDGE_STATUS = "com.symbol.datawedge.api.GET_DATAWEDGE_STATUS";
    //  6.4 API and up Parameter keys and values associated with extras received from Datawedge
    public static final String EXTRA_RESULT_GET_DATAWEDGE_STATUS = "com.symbol.datawedge.api.RESULT_GET_DATAWEDGE_STATUS";

    //  6.5 API and up Extras sent to Datawedge
    public static final String EXTRA_SEND_RESULT = "SEND_RESULT";
    public static final String EXTRA_COMMAND_IDENTIFIER = "COMMAND_IDENTIFIER";
    public static final String EXTRA_GET_CONFIG = "com.symbol.datawedge.api.GET_CONFIG";
    public static final String EXTRA_GET_DISABLED_APP_LIST = "com.symbol.datawedge.api.GET_DISABLED_APP_LIST";
    public static final String EXTRA_SET_DISABLED_APP_LIST = "com.symbol.datawedge.api.SET_DISABLED_APP_LIST";
    public static final String EXTRA_SWITCH_SCANNER = "com.symbol.datawedge.api.SWITCH_SCANNER";
    public static final String EXTRA_SWITCH_SCANNER_PARAMS = "com.symbol.datawedge.api.SWITCH_SCANNER_PARAMS";
    public static final String EXTRA_SWITCHSCANNERPARAMS_FROM_6_5 = "com.symbol.datawedge.api.SWITCH_SCANNER_PARAMS";
    //  6.5 API and up Parameter keys and values associated with extras received from Datawedge
    public static final String EXTRA_RESULT = "RESULT";
    public static final String EXTRA_RESULT_INFO = "RESULT_INFO";
    public static final String EXTRA_COMMAND = "COMMAND";
    public static final String EXTRA_RESULT_GET_CONFIG = "com.symbol.datawedge.api.RESULT_GET_CONFIG";
    public static final String EXTRA_RESULT_GET_DISABLED_APP_LIST = "com.symbol.datawedge.api.RESULT_GET_DISABLED_APP_LIST";

    public static final String AIM_MODE_PARAM = "aim_type";
    public static final String BEAM_TIMER_PARAM = "beam_timer";

    public static final String COMMAND_RESULT_SUCCESS = "SUCCESS";
    public static final String COMMAND_RESULT_FAILURE = "FAILURE";


}
