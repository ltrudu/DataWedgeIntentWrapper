package com.zebra.datawedgeprofileintents.SettingsPlugins;

import android.content.Context;
import android.os.Bundle;

import com.zebra.datawedgeprofileenums.INT_E_DELIVERY;
import com.zebra.datawedgeprofileintents.DWProfileSetConfigSettings;
import com.zebra.datawedgeprofileintents.SignatureCheck;

import java.util.ArrayList;

public class PluginIntent
{
    /////////////////////////////////////////////////////////////////////////////////////////
    // INTENT Plugin
    /////////////////////////////////////////////////////////////////////////////////////////
        /*
        Determine if the output of this plugin should be enabled or not
        Default is true
         */
    public Boolean intent_output_enabled = null;

    /*
    The action associated with the broadcasted intent
     */
    public String intent_action = null;

    /*
    The category associated with the broadcast intent
     */
    public String intent_category = null;

    /*
    Delivery mode of the intent plugin
    // Default: INT_E_DELIVERY.BROADCAST
     */
    public INT_E_DELIVERY intent_delivery = null;

    /**
     * Set this to true to use component registration instead of app registration
     * It will automatically extract the signature of the current package running this wrapper
     * when it will create the settings to be sent to DataWedge using the SetConfig method
     */
    public boolean use_component = false;

    public Bundle getIntentPluginBundle(boolean resetConfig, Context context)
    {
        // INTENT Plugin configuration
        Bundle intentPluginConfig = new Bundle();
        intentPluginConfig.putString("PLUGIN_NAME", "INTENT");
        intentPluginConfig.putString("RESET_CONFIG", resetConfig ? "true" : "false");

        Bundle intentProps = new Bundle();
        if(intent_output_enabled != null)
            intentProps.putString("intent_output_enabled", intent_output_enabled ? "true" : "false");
        if(intent_action != null)
            intentProps.putString("intent_action", intent_action);
        else
            intentProps.putString("intent_action", context.getPackageName() + ".RECVR");
        if(intent_category != null)
            intentProps.putString("intent_category", intent_category);
        else
            intentProps.putString("intent_categoty", "android.intent.category.DEFAULT");
        if(intent_delivery != null)
            intentProps.putString("intent_delivery", intent_delivery.toString());
        else
            intentProps.putString("intent_delivery", INT_E_DELIVERY.BROADCAST.toString());
        if(use_component == true)
        {
            // We are going to use the new method that consists
            ArrayList<Bundle> bundleComponentInfo = new ArrayList<Bundle>();

            Bundle component0 = new Bundle();
            component0.putString("PACKAGE_NAME",context.getPackageName());
            component0.putString("SIGNATURE", SignatureCheck.getSignature(context));
            bundleComponentInfo.add(component0);
            intentProps.putParcelableArrayList("intent_component_info", bundleComponentInfo);
        }

        intentPluginConfig.putBundle("PARAM_LIST", intentProps);
        return intentPluginConfig;
    }
}
