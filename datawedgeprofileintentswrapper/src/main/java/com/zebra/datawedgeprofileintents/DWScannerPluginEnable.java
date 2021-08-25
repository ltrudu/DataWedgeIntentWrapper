package com.zebra.datawedgeprofileintents;

import android.content.Context;
import android.provider.ContactsContract;

/**
 * Created by laure on 16/04/2018.
 */

public class DWScannerPluginEnable extends DWProfileCommandBase {

    private class onProfileCommandResultLocal implements DWProfileCommandBase.onProfileCommandResult {
        public onProfileCommandResult onProfileCommandResult = null;
        public String profileName = null, action = null, command = null, result = null, resultInfo = null, commandidentifier = null;

        public onProfileCommandResultLocal(onProfileCommandResult onProfileCommandResult)
        {
            this.onProfileCommandResult = onProfileCommandResult;
        }

        @Override
        public void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier) {
            this.profileName        = profileName ;
            this.action             = action;
            this.command            = command;
            this.result             = result;
            this.resultInfo         = resultInfo ;
            this.commandidentifier  = commandidentifier;
        }

        @Override
        public void timeout(String profileName) {
            this.result = "TIMEOUT";
            this.profileName = profileName;
        }

        public void executeResults()
        {
            onProfileCommandResult.result(this.profileName, this.action, this.command, this.result, this.resultInfo, this.commandidentifier);
        }

        public void executeTimeOut()
        {
            onProfileCommandResult.timeout(this.profileName);
        }
    }

    private onProfileCommandResultLocal myLocalCallback;
    private DWStatusScanner mScannerStatusChecker = null;
    private boolean intentLaunched = false;

    public DWScannerPluginEnable(Context aContext) {
        super(aContext);
    }

    public void execute(DWProfileBaseSettings settings, onProfileCommandResult callback)
    {
        /*
        Call base class execute to register command result
        broadcast receiver and launch timeout mechanism
         */
        myLocalCallback = new onProfileCommandResultLocal(callback);

        super.execute(settings, myLocalCallback);

        /*
        Wait for Scanner status before sending the result.
         */
        DWStatusScannerSettings profileStatusSettings = new DWStatusScannerSettings()
        {{
            mPackageName = DWScannerPluginEnable.this.mContext.getPackageName();
            mScannerCallback = new DWStatusScannerCallback() {
                @Override
                public void result(String status) {
                    if(status.equalsIgnoreCase(DataWedgeConstants.SCAN_STATUS_WAITING) &&
                            myLocalCallback.result != null)
                    {
                        myLocalCallback.executeResults();
                        mScannerStatusChecker.stop();
                        mScannerStatusChecker = null;
                    }
                    else if(myLocalCallback.result.equalsIgnoreCase("TIMEOUT"))
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
        Enable plugin
         */
        sendDataWedgeIntentWithExtraRequestResult(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2,
                DataWedgeConstants.EXTRA_SCANNERINPUTPLUGIN_FROM_6_3, DataWedgeConstants.DWAPI_PARAMETER_SCANNERINPUTPLUGIN_ENABLE);

    }
}
