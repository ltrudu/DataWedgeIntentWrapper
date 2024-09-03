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

public class DWProfileChecker extends DWProfileBase {

    private Handler broadcastReceiverHandler = null;
    private HandlerThread broadcastReceiverThread = null;
    private Looper broadcastReceiverThreadLooper = null;

    public DWProfileChecker(Context aContext) {
        super(aContext);
        mBroadcastReceiver = new checkProfileReceiver();
    }

    /*
        An interface callback to be informed of the result
        when checking if a profile exists
         */
    public interface onProfileExistResult
    {
        void result(String profileName, boolean exists);
        void timeOut(String profileName);
    }

    /*
    A store to keep the callback to be fired when we will get the
    result of the intent
     */
    private onProfileExistResult mProfileExistsCallback = null;

    /*
    The receiver that we will register to retrieve DataWedge answer
     */
    private checkProfileReceiver mBroadcastReceiver = null;

    public void execute(DWProfileCheckerSettings settings, onProfileExistResult callback)
    {
        /*
        Launch timeout mechanism
         */
        super.execute(settings);

        mProfileExistsCallback = callback;

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
        broadcastReceiverThread = new HandlerThread(settings.mProfileName.isEmpty() ? mContext.getPackageName() : settings.mProfileName + ".ENUMERATESCANNERS.THREAD");//Create a thread for BroadcastReceiver
        broadcastReceiverThread.start();

        broadcastReceiverThreadLooper = broadcastReceiverThread.getLooper();
        broadcastReceiverHandler = new Handler(broadcastReceiverThreadLooper);

        ContextCompat.registerReceiver(mContext, mBroadcastReceiver, intentFilter, null, broadcastReceiverHandler, ContextCompat.RECEIVER_EXPORTED);

        /*
        Ask for DataWedge profile list
         */
        sendDataWedgeIntentWithExtra(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2, DataWedgeConstants.EXTRA_GET_PROFILES_LIST, DataWedgeConstants.EXTRA_EMPTY);

    }

    private class checkProfileReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra(DataWedgeConstants.EXTRA_RESULT_GET_PROFILE_LIST))
            {
                //  6.2 API to GetProfileList
                String[] profilesList = intent.getStringArrayExtra(DataWedgeConstants.EXTRA_RESULT_GET_PROFILE_LIST);
                //  Profile list for 6.2 APIs
                boolean exists = Arrays.asList(profilesList).contains(mSettings.mProfileName);
                if(mProfileExistsCallback != null)
                {
                    mProfileExistsCallback.result(mSettings.mProfileName, exists);
                    cleanAll();
                }
            }
        }
    }

    @Override
    protected void cleanAll()
    {
        mSettings.mProfileName = "";
        mProfileExistsCallback = null;
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
        if(mProfileExistsCallback != null)
        {
            mProfileExistsCallback.timeOut(mSettings.mProfileName);
            cleanAll();
        }
    }
}
