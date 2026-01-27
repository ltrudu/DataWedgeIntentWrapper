package com.zebra.datawedgeprofileintents;

import android.content.Context;

/**
 * Created by laure on 16/04/2018.
 */

public class DWProfileCreate extends DWProfileCommandBase {

    public DWProfileCreate(Context aContext) {
        super(aContext);
    }

    public void execute(DWProfileCreateSettings settings, IProfileCommandResult callback)
    {
        /*
        Call base class execute to register command result
        broadcast receiver and launch timeout mechanism
         */
        super.execute(settings, callback);

        /*
        Create the profile
         */
        createProfile(settings);
     }

    private void createProfile(DWProfileCreateSettings settings)
    {
        // Create a new profile using intent CREATE_PROFILE
        sendDataWedgeIntentWithExtraRequestResult(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2,
                DataWedgeConstants.EXTRA_CREATE_PROFILE,
                settings.mProfileName);

    }
}
