package com.zebra.datawedgeprofileintents;

import android.util.Log;

import com.google.gson285.Gson;
import com.google.gson285.GsonBuilder;
import com.google.gson285.reflect.TypeToken;
import com.zebra.datawedgeprofileenums.*;

import  com.zebra.datawedgeprofileintents.SettingsPlugins.*;

import org.json.JSONObject;

import java.io.Reader;
import java.lang.reflect.Type;

/*
Add more initialisation parameters here
 */
public class DWProfileSetConfigSettings extends DWProfileBaseSettings
{
    public MainBundle MainBundle = new MainBundle();

    public PluginIntent IntentPlugin = new PluginIntent();

    public PluginBasicDataFormatting BasicDataFormatting = new PluginBasicDataFormatting();

    public PluginKeystroke KeystrokePlugin = new PluginKeystroke();

    public PluginScanner ScannerPlugin = new PluginScanner();

    public PluginRFID RFIDPlugin = new PluginRFID();

    public static DWProfileSetConfigSettings fromJson(String myJSONString)
    {
        Log.v("JSONBuilder:", myJSONString);
        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();

        JSONObject j;
        DWProfileSetConfigSettings settings = null;

        try
        {
            j = new JSONObject(myJSONString);
            settings = gson.fromJson(j.toString(), DWProfileSetConfigSettings.class);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            settings  = null;
        }
        return settings;
    }

    public static String toJson(DWProfileSetConfigSettings mySettings) {
        Gson gson = new Gson();
        Type settingsType = new TypeToken<DWProfileSetConfigSettings>(){}.getType();
        String j = gson.toJson(mySettings, settingsType);
        return j;
    }

    public static String toJsonWN(DWProfileSetConfigSettings mySettings) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type settingsType = new TypeToken<DWProfileSetConfigSettings>(){}.getType();
        String j = gson.toJson(mySettings, settingsType);
        return j;
    }
}
