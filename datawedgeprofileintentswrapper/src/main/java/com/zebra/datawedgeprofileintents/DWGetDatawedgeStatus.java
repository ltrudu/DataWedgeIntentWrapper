package com.zebra.datawedgeprofileintents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import java.util.Arrays;

import androidx.core.content.ContextCompat;

/**
 * Created by laure on 16/04/2018.
 */

public class DWGetDatawedgeStatus extends DWProfileBase {

    private Handler broadcastReceiverHandler = null;
    private HandlerThread broadcastReceiverThread = null;
    private Looper broadcastReceiverThreadLooper = null;

    public DWGetDatawedgeStatus(Context aContext) {
        super(aContext);
        mBroadcastReceiver = new dwStatusReceiver();
    }

    /*
        An interface callback to be informed of the result
        when checking datawedge status
         */
    public interface onGetDatawedgeStatusResult
    {
        void result(boolean enabled);
        void timeOut(String profileName);
    }

    /*
    A store to keep the callback to be fired when we will get the
    result of the intent
     */
    private onGetDatawedgeStatusResult mGetDatawedgeStatusCallback = null;

    /*
    The receiver that we will register to retrieve DataWedge answer
     */
    private dwStatusReceiver mBroadcastReceiver = null;

    public void execute(DWGetDatawedgeStatusSettings settings, onGetDatawedgeStatusResult callback)
    {
        /*
        Launch timeout mechanism
         */
        super.execute(settings);

        mGetDatawedgeStatusCallback = callback;

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
        broadcastReceiverThread = new HandlerThread(settings.mProfileName.isEmpty() ? mContext.getPackageName() : settings.mProfileName + ".PROFILECHECKER.THREAD");//Create a thread for BroadcastReceiver
        broadcastReceiverThread.start();

        broadcastReceiverThreadLooper = broadcastReceiverThread.getLooper();
        broadcastReceiverHandler = new Handler(broadcastReceiverThreadLooper);

        ContextCompat.registerReceiver(mContext, mBroadcastReceiver, intentFilter, null, broadcastReceiverHandler, ContextCompat.RECEIVER_EXPORTED);

        /*
        Ask for DataWedge profile list
         */
        sendDataWedgeIntentWithExtra(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2, DataWedgeConstants.EXTRA_GET_DW_STATUS, DataWedgeConstants.EXTRA_EMPTY);

    }

    private class dwStatusReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra(DataWedgeConstants.EXTRA_RESULT_GET_DW_STATUS))
            {
                //  6.2 API to GetProfileList
                String isEnabled = intent.getStringExtra(DataWedgeConstants.EXTRA_RESULT_GET_DW_STATUS);

                if(mGetDatawedgeStatusCallback != null)
                {
                    mGetDatawedgeStatusCallback.result(isEnabled.equalsIgnoreCase("enabled"));
                    cleanAll();
                }
            }
        }
    }

    @Override
    protected void cleanAll()
    {
        mSettings.mProfileName = "";
        mGetDatawedgeStatusCallback = null;
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
        if(mGetDatawedgeStatusCallback != null)
        {
            mGetDatawedgeStatusCallback.timeOut(mSettings.mProfileName);
            cleanAll();
        }
    }
}
