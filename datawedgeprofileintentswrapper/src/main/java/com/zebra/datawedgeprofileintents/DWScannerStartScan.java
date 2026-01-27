package com.zebra.datawedgeprofileintents;

import android.content.Context;

/**
 * Created by laure on 16/04/2018.
 */

public class DWScannerStartScan extends DWProfileCommandBase {

    public DWScannerStartScan(Context aContext) {
        super(aContext);
    }

    public void execute(DWProfileBaseSettings settings, IProfileCommandResult callback)
    {
        /*
        Call base class execute to register command result
        broadcast receiver and launch timeout mechanism
         */
        super.execute(settings, callback);

        /*
        Start Scan
         */
        sendDataWedgeIntentWithExtraRequestResult(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2,
                DataWedgeConstants.EXTRA_SOFTSCANTRIGGER_FROM_6_3, DataWedgeConstants.DWAPI_START_SCANNING);
     }
}
