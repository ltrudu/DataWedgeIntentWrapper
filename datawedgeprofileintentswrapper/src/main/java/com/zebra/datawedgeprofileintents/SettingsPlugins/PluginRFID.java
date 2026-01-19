package com.zebra.datawedgeprofileintents.SettingsPlugins;

import android.os.Bundle;

import com.zebra.datawedgeprofileenums.RFID_E_MEMORY_BANK;
import com.zebra.datawedgeprofileenums.RFID_E_SESSION;
import com.zebra.datawedgeprofileenums.RFID_E_TRIGGER_MODE;

public class PluginRFID {
    /*
     Enable the RFID plugin
      */
    public Boolean rfid_input_enabled = null;

    public Boolean rfid_beeper_enable = null;
    public Boolean rfid_led_enable = null;
    public Integer rfid_antenna_transmit_power = null; // Integer from 5 to 30
    public RFID_E_MEMORY_BANK rfid_memory_bank = null;            // Uses MemoryBank enum codes
    public RFID_E_SESSION rfid_session = null;                // Uses Session enum codes
    public Boolean rfid_filter_duplicate_tags = null;
    public Boolean rfid_hardware_trigger_enabled = null;
    public RFID_E_TRIGGER_MODE rfid_trigger_mode = null;           // Uses TriggerMode enum codes
    public Integer rfid_tag_read_duration = null;      // Integer from 0 to 60000
    public Integer rfid_link_profile = null;           // Integer from 0 to 11
    public Boolean rfid_dynamic_power_mode = null;

    // --- Pre-filter Parameters ---

    public Boolean rfid_pre_filter_enable = null;
    public String rfid_pre_filter_tag_pattern = null; // [blank] or [any string]
    public Integer rfid_pre_filter_target = null;     // Integer from 0 to 4
    public Integer rfid_pre_filter_memory_bank = null;// Integer from 0 to 2
    public Integer rfid_pre_filter_offset = null;     // Integer from 0 to 1024
    public Integer rfid_pre_filter_action = null;     // Integer from 0 to 7

    // --- Post-filter Parameters ---

    public Boolean rfid_post_filter_enable = null;
    public Integer rfid_post_filter_no_of_tags_to_read = null; // Integer from 0 to 1000
    public Integer rfid_post_filter_rssi = null;                // Integer from -100 to 0

    public Bundle getRFIDPluginBundle(boolean resetConfig)
    {
        Bundle rfidConfigBundle = new Bundle();
        rfidConfigBundle.putString("PLUGIN_NAME", "RFID");
        rfidConfigBundle.putString("RESET_CONFIG", resetConfig ? "true" : "false");

        // Set RFID configuration
        Bundle rfidConfigParamList = new Bundle();

        if(rfid_input_enabled != null)
            rfidConfigParamList.putString("rfid_input_enabled", rfid_input_enabled ? "true" : "false");

        if(rfid_beeper_enable != null)
            rfidConfigParamList.putString("rfid_beeper_enable", rfid_beeper_enable ? "true" : "false");

        if(rfid_led_enable != null)
            rfidConfigParamList.putString("rfid_led_enable", rfid_led_enable ? "true" : "false");

        if(rfid_antenna_transmit_power != null)
            rfidConfigParamList.putString("rfid_antenna_transmit_power", rfid_antenna_transmit_power.toString());

        if(rfid_memory_bank != null)
            rfidConfigParamList.putString("rfid_memory_bank", rfid_memory_bank.toString());

        if(rfid_session != null)
            rfidConfigParamList.putString("rfid_session", rfid_session.toString());

        if(rfid_filter_duplicate_tags != null)
            rfidConfigParamList.putString("rfid_filter_duplicate_tags", rfid_filter_duplicate_tags ? "true" : "false");

        if(rfid_hardware_trigger_enabled != null)
            rfidConfigParamList.putString("rfid_hardware_trigger_enabled", rfid_hardware_trigger_enabled ? "true" : "false");

        if(rfid_trigger_mode != null)
            rfidConfigParamList.putString("rfid_trigger_mode", rfid_trigger_mode.toString());

        if(rfid_tag_read_duration != null)
            rfidConfigParamList.putString("rfid_tag_read_duration", rfid_tag_read_duration.toString());

        if(rfid_link_profile != null)
            rfidConfigParamList.putString("rfid_link_profile", rfid_link_profile.toString());

        if(rfid_dynamic_power_mode != null)
            rfidConfigParamList.putString("rfid_dynamic_power_mode", rfid_dynamic_power_mode ? "true" : "false");

        // --- Pre-filter Parameters ---
        if(rfid_pre_filter_enable != null)
            rfidConfigParamList.putString("rfid_pre_filter_enable", rfid_pre_filter_enable ? "true" : "false");

        if(rfid_pre_filter_tag_pattern != null)
            rfidConfigParamList.putString("rfid_pre_filter_tag_pattern", rfid_pre_filter_tag_pattern);

        if(rfid_pre_filter_target != null)
            rfidConfigParamList.putString("rfid_pre_filter_target", rfid_pre_filter_target.toString());

        if(rfid_pre_filter_memory_bank != null)
            rfidConfigParamList.putString("rfid_pre_filter_memory_bank", rfid_pre_filter_memory_bank.toString());

        if(rfid_pre_filter_offset != null)
            rfidConfigParamList.putString("rfid_pre_filter_offset", rfid_pre_filter_offset.toString());

        if(rfid_pre_filter_action != null)
            rfidConfigParamList.putString("rfid_pre_filter_action", rfid_pre_filter_action.toString());

        // --- Post-filter Parameters ---
        if(rfid_post_filter_enable != null)
            rfidConfigParamList.putString("rfid_post_filter_enable", rfid_post_filter_enable ? "true" : "false");

        if(rfid_post_filter_no_of_tags_to_read != null)
            rfidConfigParamList.putString("rfid_post_filter_no_of_tags_to_read", rfid_post_filter_no_of_tags_to_read.toString());

        if(rfid_post_filter_rssi != null)
            rfidConfigParamList.putString("rfid_post_filter_rssi", rfid_post_filter_rssi.toString());

        rfidConfigBundle.putBundle("PARAM_LIST", rfidConfigParamList);
        return rfidConfigBundle;
    }
}