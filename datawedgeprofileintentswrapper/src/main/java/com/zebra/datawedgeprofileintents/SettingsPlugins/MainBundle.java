package com.zebra.datawedgeprofileintents.SettingsPlugins;

import com.zebra.datawedgeprofileenums.MB_E_CONFIG_MODE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainBundle
{
    /////////////////////////////////////////////////////////////////////////////////////////
    // MAIN Bundle
    /////////////////////////////////////////////////////////////////////////////////////////
    /*
       Set if the profile should be enabled or not
    */
    public Boolean PROFILE_ENABLED = null;

    /*
    Set how the profile will be processed
     */
    public MB_E_CONFIG_MODE CONFIG_MODE = null;

    /////////////////////////////////////////////////////////////////////////////////////////
    // APP LIST Bundle
    /////////////////////////////////////////////////////////////////////////////////////////
    /*
        Allow to set multiple package name / activity list
     */
    public HashMap<String, List<String>> APP_LIST = null;
}
