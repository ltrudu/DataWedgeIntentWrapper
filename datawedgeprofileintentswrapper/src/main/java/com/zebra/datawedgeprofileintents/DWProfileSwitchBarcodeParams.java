package com.zebra.datawedgeprofileintents;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by laure on 16/04/2018.
 */

public class DWProfileSwitchBarcodeParams extends DWProfileCommandBase {

    public DWProfileSwitchBarcodeParams(Context aContext) {
        super(aContext);
    }

    public void execute(DWProfileSwitchBarcodeParamsSettings settings, onProfileCommandResult callback)
    {
        /*
        Call base class execute to register command result
        broadcast receiver and launch timeout mechanism
         */
        super.execute(settings, callback);

        /*
        Get the Bundle configuration for parameters switch
         */
        Bundle params = null;
        try {
            params = settings.ScannerPlugin.getBarcodePluginBundleForSwitchParams();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG);
            System.exit(0);
        }
        /*
        Execute the profile using only the difference between original and destination settings
         */
        if(params != null)
            sendDataWedgeIntentWithExtraRequestResult(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2, DataWedgeConstants.EXTRA_SWITCH_SCANNER_PARAMS, params);
    }
}
