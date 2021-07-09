package com.zebra.datawedgeprofileintents;

import android.content.Context;

/**
 * Created by laure on 16/04/2018.
 */

public class DWDataWedgeEnable extends DWProfileCommandBase {

    public DWDataWedgeEnable(Context aContext) {
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
        Start Scan
         */
        sendDataWedgeIntentWithExtraRequestResult(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2, DataWedgeConstants.EXTRA_ENABLE_DATAWEDGE, true);
     }
}
