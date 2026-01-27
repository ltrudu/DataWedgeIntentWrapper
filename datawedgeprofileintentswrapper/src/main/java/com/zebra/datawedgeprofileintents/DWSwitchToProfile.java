package com.zebra.datawedgeprofileintents;

import android.content.Context;

/**
 * Created by laure on 16/04/2018.
 */

public class DWSwitchToProfile extends DWProfileCommandBase {

    public DWSwitchToProfile(Context aContext) {
        super(aContext);
    }

    public void execute(DWSwitchToProfileSettings settings, IProfileCommandResult callback)
    {
        /*
        Call base class execute to register command result
        broadcast receiver and launch timeout mechanism
         */
        super.execute(settings, callback);

        /*
        Create the profile
         */
        switchToProfile(settings);
     }

    private void switchToProfile(DWSwitchToProfileSettings settings)
    {
        // Create a new profile using intent CREATE_PROFILE
        sendDataWedgeIntentWithExtraRequestResult(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2,
                DataWedgeConstants.EXTRA_SWITCHTOPROFILE_FROM_6_3,
                settings.mProfileName);
    }
}
