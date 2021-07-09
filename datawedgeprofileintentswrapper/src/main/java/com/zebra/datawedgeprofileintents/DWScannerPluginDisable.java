package com.zebra.datawedgeprofileintents;

import android.content.Context;

/**
 * Created by laure on 16/04/2018.
 */

public class DWScannerPluginDisable extends DWProfileCommandBase {

    public DWScannerPluginDisable(Context aContext) {
        super(aContext);
    }

    public void execute(DWProfileBaseSettings settings, onProfileCommandResult callback)
    {
        /*
        Call base class execute to register command result
        broadcast receiver and launch timeout mechanism
         */
        super.execute(settings, callback);

        /*
        Disable plugin
         */
        sendDataWedgeIntentWithExtraRequestResult(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2,
                DataWedgeConstants.EXTRA_SCANNERINPUTPLUGIN_FROM_6_3, DataWedgeConstants.DWAPI_PARAMETER_SCANNERINPUTPLUGIN_DISABLE);
     }
}
