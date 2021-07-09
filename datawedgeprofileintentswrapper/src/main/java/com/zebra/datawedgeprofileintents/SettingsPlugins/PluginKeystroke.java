package com.zebra.datawedgeprofileintents.SettingsPlugins;

import android.os.Bundle;

import com.zebra.datawedgeprofileenums.KEY_E_ACTION_CHAR;
import com.zebra.datawedgeprofileintents.DWProfileSetConfigSettings;

/////////////////////////////////////////////////////////////////////////////////////////
// Keystroke Plugin...
/////////////////////////////////////////////////////////////////////////////////////////
public class PluginKeystroke
{
    /*
    Enable the keystroke to output something or not
     */
    public Boolean keystroke_output_enabled = null;

    /*
    Action to execute after keystroke
     */
    public KEY_E_ACTION_CHAR keystroke_action_character = null;

    public Integer keystroke_delay_control_characters = null;

    public Integer keystroke_character_delay = null;

    public Boolean keystroke_delay_multibyte_chars_only = null;

    public Bundle getKeyStrokePluginBundle(boolean resetConfig)
    {
        // KEYSTROKE plugin configuration -> Disabled
        Bundle keystrokePluginConfig = new Bundle();
        keystrokePluginConfig.putString("PLUGIN_NAME", "KEYSTROKE");
        keystrokePluginConfig.putString("RESET_CONFIG", resetConfig ? "true" : "false");
        Bundle keystrokeProps = new Bundle();
        setupKeystrokePlugin(keystrokeProps);
        keystrokePluginConfig.putBundle("PARAM_LIST", keystrokeProps);
        return keystrokePluginConfig;
    }
    
    private void setupKeystrokePlugin(Bundle keystrokeProps)
    {
        if(keystroke_output_enabled != null)
            keystrokeProps.putString("keystroke_output_enabled", keystroke_output_enabled ? "true" : "false");

        if(keystroke_action_character != null)
            keystrokeProps.putString("keystroke_action_character", keystroke_action_character.toString());

        if(keystroke_delay_control_characters != null)
            keystrokeProps.putInt("keystroke_delay_control_characters", keystroke_delay_control_characters);

        if(keystroke_character_delay != null)
            keystrokeProps.putInt("keystroke_character_delay", keystroke_character_delay);

        if(keystroke_delay_multibyte_chars_only != null)
            keystrokeProps.putString("keystroke_delay_multibyte_chars_only", keystroke_delay_multibyte_chars_only ? "true" : "false");

    }
}
