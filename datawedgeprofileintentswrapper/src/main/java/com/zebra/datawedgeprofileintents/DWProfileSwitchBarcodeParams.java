package com.zebra.datawedgeprofileintents;

import android.content.Context;

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
        Execute the profile using only the difference between original and destination settings
         */
        sendDataWedgeIntentWithExtraRequestResult(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2, DataWedgeConstants.EXTRA_SWITCH_SCANNER_PARAMS, settings.ScannerPlugin.getBarcodePluginBundleForSwitchParams());
    }
}
