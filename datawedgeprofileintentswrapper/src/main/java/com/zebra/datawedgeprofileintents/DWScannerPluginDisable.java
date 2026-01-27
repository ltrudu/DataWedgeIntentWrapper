package com.zebra.datawedgeprofileintents;

import android.content.Context;

/**
 * Created by laure on 16/04/2018.
 */

public class DWScannerPluginDisable extends DWProfileCommandBase {

    private ProfileCommandResultBase myLocalCallback;
    private DWStatusScanner mScannerStatusChecker = null;

    public DWScannerPluginDisable(Context aContext) {
        super(aContext);
    }

    public void execute(DWProfileBaseSettings settings, IProfileCommandResult callback)
    {
        /*
        Call base class execute to register command result
        broadcast receiver and launch timeout mechanism
         */
        myLocalCallback = new ProfileCommandResultBase(callback);

        super.execute(settings, myLocalCallback);

        /*
        Wait for Scanner status before sending the result.
         */
        DWStatusScannerSettings profileStatusSettings = new DWStatusScannerSettings()
        {{
            mPackageName = DWScannerPluginDisable.this.mContext.getPackageName();
            mScannerCallback = new DWStatusScannerCallback() {
                @Override
                public void result(String status) {
                    if(status != null && (status.equalsIgnoreCase(DataWedgeConstants.SCAN_STATUS_DISABLED)))
                    {
                        if(myLocalCallback != null)
                        {
                            if(myLocalCallback.initialized == true) {
                                // Command has returned values
                                myLocalCallback.executeResults();

                                mScannerStatusChecker.stop();
                                mScannerStatusChecker.unRegisterNotificationReceiver();
                                mScannerStatusChecker = null;
                            }
                            else
                            {
                                // We need to postpone the execution of the result to when
                                // the command will send its return values
                                myLocalCallback.postponedOnResult = new ProfileCommandResultBase.IPostponedOnResult() {
                                    @Override
                                    public void onResult() {
                                        // Command has returned values
                                        myLocalCallback.executeResults();

                                        mScannerStatusChecker.stop();
                                        mScannerStatusChecker.unRegisterNotificationReceiver();
                                        mScannerStatusChecker = null;
                                    }
                                };
                            }
                        }
                    }
                    else if(myLocalCallback != null && myLocalCallback.result != null && myLocalCallback.result.equalsIgnoreCase("TIMEOUT"))
                    {
                        myLocalCallback.executeTimeOut();
                        mScannerStatusChecker.stop();
                        mScannerStatusChecker.unRegisterNotificationReceiver();
                        mScannerStatusChecker = null;
                    }
                }
            };
        }};

        mScannerStatusChecker = new DWStatusScanner(this.mContext, profileStatusSettings);
        mScannerStatusChecker.start();

        /*
        Disable plugin
         */
        sendDataWedgeIntentWithExtraRequestResult(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2,
                DataWedgeConstants.EXTRA_SCANNERINPUTPLUGIN_FROM_6_3, DataWedgeConstants.DWAPI_PARAMETER_SCANNERINPUTPLUGIN_DISABLE);
     }
}
