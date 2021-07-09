package com.zebra.datawedgeprofileintents;

import android.util.Log;

import com.google.gson285.Gson;
import com.google.gson285.GsonBuilder;
import com.google.gson285.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class DWProfileBaseSettings
{
    /*
    The profile we are working on
     */
    public String mProfileName = "";

    /*
    Some method return only errors (StartScan, StopScan)
    We do not need a time out for them
     */
    public boolean mEnableTimeOutMechanism = true;

    /*
    A time out, in case we don't receive an answer
    from DataWedge
     */
    public long mTimeOutMS = 5000;

    public static DWProfileBaseSettings fromJson(String myJSONString)
    {
        Log.v("JSONBuilder:", myJSONString);
        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();

        JSONObject j;
        DWProfileBaseSettings settings = null;

        try
        {
            j = new JSONObject(myJSONString);
            settings = gson.fromJson(j.toString(), DWProfileBaseSettings.class);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            settings  = null;
        }
        return settings;
    }

    public static String toJson(DWProfileBaseSettings mySettings) {
        Gson gson = new Gson();
        Type settingsType = new TypeToken<DWProfileBaseSettings>(){}.getType();
        String j = gson.toJson(mySettings, settingsType);
        return j;
    }

    public static String toJsonWN(DWProfileBaseSettings mySettings) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type settingsType = new TypeToken<DWProfileBaseSettings>(){}.getType();
        String j = gson.toJson(mySettings, settingsType);
        return j;
    }
}
