package com.zebra.datawedgeprofileintents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * Created by laure on 16/04/2018.
 */

public class DWScannerPluginStatus extends DWProfileBase {

    private Handler broadcastReceiverHandler = null;
    private HandlerThread broadcastReceiverThread = null;
    private Looper broadcastReceiverThreadLooper = null;

    public DWScannerPluginStatus(Context aContext) {
        super(aContext);
        mBroadcastReceiver = new pluginStatusReceiver();
    }

    /*
        An interface callback to be informed of the result
         */
    public interface onScannerPluginStatus
    {
        void result(String profileName, boolean enabled);
        void timeOut(String profileName);
    }

    /*
    A store to keep the callback to be fired when we will get the
    result of the intent
     */
    private onScannerPluginStatus mScannerPluginStatusCallback = null;

    /*
    The receiver that we will register to retrieve DataWedge answer
     */
    private pluginStatusReceiver mBroadcastReceiver = null;

    public void execute(DWProfileBaseSettings settings, onScannerPluginStatus callback)
    {
        /*
        Launch timeout mechanism
         */
        super.execute(settings);

        mScannerPluginStatusCallback = callback;

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DataWedgeConstants.ACTION_RESULT_DATAWEDGE_FROM_6_2);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);

        /*
        Register receiver for resutls
         */
               /*
        Register receiver for results
        Receiver is registered in a new thread instead of the main thread
        This allow us to still receive the broadcasted results even if we
        are working on a separate thread or in synchronous mode
         */
        broadcastReceiverThread = new HandlerThread(settings.mProfileName.isEmpty() ? mContext.getPackageName() : settings.mProfileName + ".GETSTATUS.THREAD");//Create a thread for BroadcastReceiver
        broadcastReceiverThread.start();

        broadcastReceiverThreadLooper = broadcastReceiverThread.getLooper();
        broadcastReceiverHandler = new Handler(broadcastReceiverThreadLooper);

        mContext.registerReceiver(mBroadcastReceiver, intentFilter, null, broadcastReceiverHandler);

        /*
        Ask for DataWedge profile list
         */
        sendDataWedgeIntentWithExtra(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2, DataWedgeConstants.EXTRA_GET_DATAWEDGE_STATUS, DataWedgeConstants.EXTRA_EMPTY);

    }

    private class pluginStatusReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra(DataWedgeConstants.EXTRA_RESULT_GET_DATAWEDGE_STATUS))
            {
                //  6.2 API to GetStatus
                String status = intent.getStringExtra(DataWedgeConstants.EXTRA_RESULT_GET_DATAWEDGE_STATUS);
                //  Profile list for 6.2 APIs
                boolean enabled = status.equalsIgnoreCase(DataWedgeConstants.EXTRA_RESULT_DATAWEDGE_ENABLED);
                if(mScannerPluginStatusCallback != null)
                {
                    mScannerPluginStatusCallback.result(mSettings.mProfileName, enabled);
                    cleanAll();
                }
            }
        }
    }

    @Override
    protected void cleanAll()
    {
        mSettings.mProfileName = "";
        mScannerPluginStatusCallback = null;
        mContext.unregisterReceiver(mBroadcastReceiver);
        if(broadcastReceiverThread != null)
        {
            broadcastReceiverThreadLooper.quit();
            broadcastReceiverThreadLooper = null;
            broadcastReceiverThread = null;
            broadcastReceiverHandler = null;
        }
        super.cleanAll();
    }

    /*
    This is what will happen if Datawedge does not answer before the timeout
     */
    @Override
    protected void onTimeOut() {
        if(mScannerPluginStatusCallback != null)
        {
            mScannerPluginStatusCallback.timeOut(mSettings.mProfileName);
            cleanAll();
        }
    }
}
