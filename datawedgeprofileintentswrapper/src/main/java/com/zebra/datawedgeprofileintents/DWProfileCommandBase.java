package com.zebra.datawedgeprofileintents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import java.util.Date;
import java.util.Set;

/**
 * Created by laure on 16/04/2018.
 */

public class DWProfileCommandBase extends DWProfileBase {
    private Handler broadcastReceiverHandler = null;
    private HandlerThread broadcastReceiverThread = null;
    private Looper broadcastReceiverThreadLooper = null;

    /*
    A command identifier used if we request a result from DataWedge
    */
    protected String mCommandIdentifier = "";

    public DWProfileCommandBase(Context aContext) {
        super(aContext);
        mBroadcastReceiver = new dataWedgeActionResultReceiver();
    }

    /*
        An interface callback to be informed of the result
        when checking if a profile exists
         */
    public interface onProfileCommandResult
    {
        void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier);
        void timeout(String profileName);
    }

    /*
    A store to keep the callback to be fired when we will get the
    result of the intent
     */
    private onProfileCommandResult mProfileCommandCallback = null;

    /*
    The receiver that we will register to retrieve DataWedge answer
     */
    private dataWedgeActionResultReceiver mBroadcastReceiver = null;

    protected void execute(DWProfileBaseSettings settings, onProfileCommandResult callback)
    {
        /*
        Launch timeout mechanism
         */
        super.execute(settings);

        mProfileCommandCallback = callback;

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DataWedgeConstants.ACTION_RESULT_DATAWEDGE_FROM_6_2);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);

        /*
        Register receiver for results
        Receiver is registered in a new thread instead of the main thread
        This allow us to still receive the broadcasted results even if we
        are working on a separate thread or in synchronous mode
         */
        broadcastReceiverThread = new HandlerThread(settings.mProfileName + ".THREAD");//Create a thread for BroadcastReceiver
        broadcastReceiverThread.start();

        broadcastReceiverThreadLooper = broadcastReceiverThread.getLooper();
        broadcastReceiverHandler = new Handler(broadcastReceiverThreadLooper);

        //mContext.registerReceiver(mBroadcastReceiver, intentFilter);
        mContext.registerReceiver(mBroadcastReceiver, intentFilter, null, broadcastReceiverHandler);
     }

    protected void sendDataWedgeIntentWithExtraRequestResult(String action, String extraKey, String extraValue)
    {
        Intent dwIntent = new Intent();
        dwIntent.setAction(action);
        dwIntent.putExtra(extraKey, extraValue);
        dwIntent.putExtra("SEND_RESULT","true");
        mCommandIdentifier = mSettings.mProfileName + new Date().getTime();
        dwIntent.putExtra("COMMAND_IDENTIFIER",mCommandIdentifier);
        mContext.sendBroadcast(dwIntent);
    }

    protected void sendDataWedgeIntentWithExtraRequestResult(String action, String extraKey, boolean extraValue)
    {
        Intent dwIntent = new Intent();
        dwIntent.setAction(action);
        dwIntent.putExtra(extraKey, extraValue);
        dwIntent.putExtra("SEND_RESULT","true");
        mCommandIdentifier = mSettings.mProfileName + new Date().getTime();
        dwIntent.putExtra("COMMAND_IDENTIFIER",mCommandIdentifier);
        mContext.sendBroadcast(dwIntent);
    }

    protected void sendDataWedgeIntentWithExtraRequestResult(String action, String extraKey, Bundle extras)
    {
        Intent dwIntent = new Intent();
        dwIntent.setAction(action);
        dwIntent.putExtra(extraKey, extras);
        dwIntent.putExtra("SEND_RESULT","true");
        mCommandIdentifier = mSettings.mProfileName + new Date().getTime();
        dwIntent.putExtra("COMMAND_IDENTIFIER",mCommandIdentifier);
        mContext.sendBroadcast(dwIntent);
    }

    protected class dataWedgeActionResultReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(DataWedgeConstants.ACTION_RESULT_DATAWEDGE_FROM_6_2))
            {
                    // L'intent reçu est bien l'intent cible
                    // On trace le résultat
                    String action = intent.getAction();
                    String command = intent.getStringExtra("COMMAND");
                    String commandidentifier = intent.getStringExtra("COMMAND_IDENTIFIER");
                    String result = intent.getStringExtra("RESULT");

                    if(commandidentifier == null)
                        return;

                    Bundle bundle = new Bundle();
                    String resultInfo = "";
                    if (intent.hasExtra("RESULT_INFO")) {
                        bundle = intent.getBundleExtra("RESULT_INFO");
                        Set<String> keys = bundle.keySet();
                        for (String key : keys) {
                            String value ="";

                            if(bundle.getString(key) != null)
                            {
                                value = bundle.getString(key);
                            }
                            else if(bundle.getStringArray(key) != null)
                            {
                                for(String innerString : bundle.getStringArray(key))
                                    value += innerString + ";";
                            }
                            if(resultInfo.length() > 0 && value != null)
                                resultInfo += "\n";
                            if(value != null)
                                resultInfo += key + ": " + value;
                            if(key.equalsIgnoreCase("PROFILE_NAME") == true && value.equalsIgnoreCase(mSettings.mProfileName) == false)
                            {
                                resultInfo += "\n-> active profile differs from expected profile " + mSettings.mProfileName;
                            }
                        }
                    }


                    String text = "Action: " + action + "\n" +
                            "Command: " + command + "\n" +
                            "Result: " + result + "\n" +
                            "Result Info: " + resultInfo + "\n" +
                            "CID:" + commandidentifier;

                    Log.d(TAG, text);

                    if(commandidentifier.equalsIgnoreCase(mCommandIdentifier) == false)
                        return;

                    if(mProfileCommandCallback != null)
                    {
                        mProfileCommandCallback.result(mSettings.mProfileName, action, command, result, resultInfo, commandidentifier);
                        cleanAll();
                    }
            }
        }
    }

    @Override
    protected void cleanAll()
    {
        mProfileCommandCallback = null;
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
        if(mProfileCommandCallback != null)
        {
            mProfileCommandCallback.timeout(mSettings.mProfileName);
            cleanAll();
        }
    }
}
