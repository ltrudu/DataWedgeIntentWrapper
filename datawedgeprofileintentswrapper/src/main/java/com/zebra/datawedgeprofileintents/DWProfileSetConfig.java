package com.zebra.datawedgeprofileintents;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;

import com.zebra.datawedgeprofileenums.MB_E_CONFIG_MODE;
import com.zebra.datawedgeprofileintents.SettingsPlugins.BaseSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by laure on 16/04/2018.
 */

public class DWProfileSetConfig extends DWProfileCommandBase {
    public DWProfileSetConfig(Context aContext) {
        super(aContext);
    }

    private DWProfileSetConfigSettings mBaseSettings = new DWProfileSetConfigSettings();

    public void execute(DWProfileSetConfigSettings settings, onProfileCommandResult callback)
    {
        /*
        Call base class execute to register command result
        broadcast receiver and launch timeout mechanism
         */
        super.execute(settings, callback);

        /*
        Create the profile
         */
        setProfileConfig(settings);
     }

    private void setProfileConfig(DWProfileSetConfigSettings settings)
    {
        Bundle profileConfig = new Bundle();
        if(settings.mProfileName != null)
            profileConfig.putString("PROFILE_NAME", settings.mProfileName);
        if(settings.MainBundle.PROFILE_ENABLED != null)
            profileConfig.putString("PROFILE_ENABLED", settings.MainBundle.PROFILE_ENABLED ? "true" : "false");
        if(settings.MainBundle.CONFIG_MODE != null)
            profileConfig.putString("CONFIG_MODE", settings.MainBundle.CONFIG_MODE.toString());

        // We only add the app list if we are "not in update" mode.
        // Having an APP_LIST set when in update mode throws an APP_ALREADY_ASSOCIATED error.
        if(settings.MainBundle.CONFIG_MODE != MB_E_CONFIG_MODE.UPDATE)
        {
            if(settings.IntentPlugin.use_component == false)
            {
                // We are going to use the old configuration mode that use application
                // and activity binding to send Datawedge Intents
                if (settings.MainBundle.APP_LIST == null || settings.MainBundle.APP_LIST.size() == 0) {
                    // Setup app list for this package only
                    Bundle appConfig = new Bundle();
                    appConfig.putString("PACKAGE_NAME", mContext.getPackageName());
                    appConfig.putStringArray("ACTIVITY_LIST", new String[]{"*"});
                    profileConfig.putParcelableArray("APP_LIST", new Bundle[]{appConfig});
                } else {
                    Bundle[] appConfigs = new Bundle[settings.MainBundle.APP_LIST.size()];
                    int index = 0;
                    // Setup associated application and activities
                    for (Map.Entry<String, List<String>> packageDescription : settings.MainBundle.APP_LIST.entrySet()) {
                        Bundle appConfig = new Bundle();
                        appConfig.putString("PACKAGE_NAME", packageDescription.getKey());
                        List<String> activityList = packageDescription.getValue();
                        if (activityList == null || activityList.size() == 0) {
                            appConfig.putStringArray("ACTIVITY_LIST", new String[]{"*"});
                        } else {
                            appConfig.putStringArray("ACTIVITY_LIST", (String[]) activityList.toArray());
                        }
                        appConfigs[index] = appConfig;
                        index++;
                    }
                    profileConfig.putParcelableArray("APP_LIST", appConfigs);
                }
            }
        }

        // Array that will hold all the DW plugins
        ArrayList<Bundle> pluginConfigs = new ArrayList<Bundle>();

        // Add barcode plugin config
        pluginConfigs.add(settings.ScannerPlugin.getBarcodePluginBundleForSetConfig(true));

        // Add keystroke plugin config (disabled in this case)
        pluginConfigs.add(settings.KeystrokePlugin.getKeyStrokePluginBundle(true));

        // Add BDF plugin
        pluginConfigs.add(settings.BasicDataFormatting.getBDFPluginBundle(true, "INTENT"));

        // Setup intent delivery by broadcast for this case
        pluginConfigs.add(settings.IntentPlugin.getIntentPluginBundle(true, mContext));

        // Send Plugin configuration intent
        profileConfig.putParcelableArrayList("PLUGIN_CONFIG", pluginConfigs);
        //profileConfig.putBundle("PLUGIN_CONFIG", settings.ScannerPlugin.getBarcodePluginBundleForSetConfig(true, null));

        String jsonwn = DWProfileSetConfigSettings.toJsonWN(settings);

        sendDataWedgeIntentWithExtraRequestResult(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2,
                DataWedgeConstants.EXTRA_SET_CONFIG,
                profileConfig);
    }

}
