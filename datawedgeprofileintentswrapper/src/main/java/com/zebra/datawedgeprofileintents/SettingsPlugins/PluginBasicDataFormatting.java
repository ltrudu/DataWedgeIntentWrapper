package com.zebra.datawedgeprofileintents.SettingsPlugins;

import android.os.Bundle;

import com.zebra.datawedgeprofileenums.BDF_E_OUTPUT_PLUGIN;
import com.zebra.datawedgeprofileintents.DWProfileSetConfigSettings;

public class PluginBasicDataFormatting
{
    /////////////////////////////////////////////////////////////////////////////////////////
    // BDF Plugin
    /////////////////////////////////////////////////////////////////////////////////////////
        /*
        Enable or disable BDF
         */
    public Boolean bdf_enabled  = null;

    /*
    Target output plugin
    i.e. to what plugin this config is associated
     */
    public BDF_E_OUTPUT_PLUGIN bdf_output_plugin = null;

    /*
    Prefix to acquired data
     */
    public String bdf_prefix  = null;

    /*
    Suffix to acquired data
     */
    public String bdf_suffix  = null;

    /*
    Send Data ? set to false if you want only hex for exemple
    */
    public Boolean bdf_send_data  = null;

    /*
    Send as Hexadecimal data
    */
    public Boolean bdf_send_hex  = null;

    /*
    Send a TAB after the data
    */
    public Boolean bdf_send_tab  = null;

    /*
    Send a Enter after the data
    */
    public Boolean bdf_send_enter  = null;

    public Bundle getBDFPluginBundle(boolean resetConfig, String outputPluginName)
    {
        // Basic Data Formatting plugin configuration
        Bundle bdfPluginConfig = new Bundle();
        bdfPluginConfig.putString("PLUGIN_NAME","BDF");
        bdfPluginConfig.putString("RESET_CONFIG",resetConfig ? "true" : "false");
        if(bdf_output_plugin != null)
            bdfPluginConfig.putString("OUTPUT_PLUGIN_NAME",bdf_output_plugin.toString());

        // param_list bundle properties
        Bundle bParams = new Bundle();
        if(bdf_enabled != null)
            bParams.putString("bdf_enabled", bdf_enabled ? "true" : "false");
        if(bdf_prefix != null)
            bParams.putString("bdf_prefix",bdf_prefix);
        if(bdf_suffix  != null )
            bParams.putString("bdf_suffix",bdf_suffix );
        if(bdf_send_data  != null )
            bParams.putString("bdf_send_data", bdf_send_data ? "true" : "false");
        if(bdf_send_hex  != null )
            bParams.putString("bdf_send_hex",bdf_send_hex  ? "true" : "false");
        if(bdf_send_tab != null)
            bParams.putString("bdf_send_tab",bdf_send_tab ? "true" : "false");
        if(bdf_send_enter  != null )
            bParams.putString("bdf_send_enter",bdf_send_enter  ? "true" : "false");

        bdfPluginConfig.putBundle("PARAM_LIST", bParams);
        return bdfPluginConfig;
    }
}


