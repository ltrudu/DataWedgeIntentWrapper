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

public class DWStatusScanner {
    private static String TAG = "DWStatusScanner";
    private Context mContext;
    private dataWedgeScannerStatusReceiver mStatusBroadcastReceiver = null;
    private DWStatusScannerSettings mStatusSettings = null;
    private Handler broadcastReceiverHandler = null;
    private HandlerThread broadcastReceiverThread = null;
    private Looper broadcastReceiverThreadLooper = null;


    public DWStatusScanner(Context aContext, DWStatusScannerSettings settings) {
        mContext = aContext;
        mStatusSettings = settings;
        mStatusBroadcastReceiver = new dataWedgeScannerStatusReceiver();
    }

    public void start()
    {
        /*
        Register notification broadcast receiver
         */
        registerNotificationReceiver();

        /*
        Register for status callcack
         */
        registerForScannerStatus(mStatusSettings);
    }

    public void stop()
    {
        unRegisterNotificationReceiver();
        unRegisterForScannerStatus(mStatusSettings);
    }

    protected class dataWedgeScannerStatusReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(DataWedgeConstants.NOTIFICATION_ACTION)){
                // handle notification
                if(intent.hasExtra(DataWedgeConstants.EXTRA_RESULT_NOTIFICATION)) {
                    Bundle b = intent.getBundleExtra(DataWedgeConstants.EXTRA_RESULT_NOTIFICATION);
                    String NOTIFICATION_TYPE  = b.getString(DataWedgeConstants.EXTRA_RESULT_NOTIFICATION_TYPE);
                    if(NOTIFICATION_TYPE!= null) {
                        switch (NOTIFICATION_TYPE) {
                            case DataWedgeConstants.NOTIFICATION_TYPE_SCANNER_STATUS:
                                String status = b.getString(DataWedgeConstants.EXTRA_KEY_VALUE_NOTIFICATION_STATUS);
                                if(status!=null){
                                    mStatusSettings.mScannerCallback.result(status);
                                }
                                break;
                        }
                    }
                }
            }
        }
    };

    protected void registerForScannerStatus(DWStatusScannerSettings settings)
    {
        Bundle b = new Bundle();
        b.putString(DataWedgeConstants.EXTRA_KEY_APPLICATION_NAME, settings.mPackageName);
        b.putString(DataWedgeConstants.EXTRA_KEY_NOTIFICATION_TYPE, DataWedgeConstants.NOTIFICATION_TYPE_SCANNER_STATUS);
        Intent i = new Intent();
        i.setAction(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2);
        i.putExtra(DataWedgeConstants.ACTION_EXTRA_REGISTER_FOR_NOTIFICATION, b);
        mContext.getApplicationContext().sendBroadcast(i);
    }

    protected void unRegisterForScannerStatus(DWStatusScannerSettings settings)
    {
        Bundle b = new Bundle();
        b.putString(DataWedgeConstants.EXTRA_KEY_APPLICATION_NAME, settings.mPackageName);
        b.putString(DataWedgeConstants.EXTRA_KEY_NOTIFICATION_TYPE, DataWedgeConstants.NOTIFICATION_TYPE_SCANNER_STATUS);
        Intent i = new Intent();
        i.setAction(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2);
        i.putExtra(DataWedgeConstants.ACTION_EXTRA_UNREGISTER_FOR_NOTIFICATION, b);
        mContext.getApplicationContext().sendBroadcast(i);
    }

    void registerNotificationReceiver() {
        // Ensure that no thread was left running
        QuitReceiverThreadNicely();

        Log.d(TAG, "registerNotificationReceiver()");
        broadcastReceiverThread = new HandlerThread(mStatusSettings.mPackageName + ".NOTIFICATION.THREAD");//Create a thread for BroadcastReceiver

        broadcastReceiverThread.start();

        broadcastReceiverThreadLooper = broadcastReceiverThread.getLooper();
        broadcastReceiverHandler = new Handler(broadcastReceiverThreadLooper);

        IntentFilter filter = new IntentFilter();
        filter.addAction(DataWedgeConstants.NOTIFICATION_ACTION);
        mContext.registerReceiver(mStatusBroadcastReceiver, filter, null, broadcastReceiverHandler);
    }

    void unRegisterNotificationReceiver() {
        //to unregister the broadcast receiver
        try {
            mContext.unregisterReceiver(mStatusBroadcastReceiver); //Android method
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        QuitReceiverThreadNicely();

    }

    private void QuitReceiverThreadNicely() {
        Log.d(TAG, "QuitReceiverThreadNicely()");
        if(broadcastReceiverHandler != null)
        {
            {
                try {
                    Log.d(TAG, "QuitReceiverThreadNicely():broadcastReceiverHandler.removeCallbacksAndMessages(null)");
                    broadcastReceiverHandler.removeCallbacksAndMessages(null);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                broadcastReceiverHandler = null;
            }

            if(broadcastReceiverThreadLooper != null)
            {
                try {
                    Log.d(TAG, "QuitReceiverThreadNicely():broadcastReceiverThreadLooper.quit()");
                    broadcastReceiverThreadLooper.quit();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                broadcastReceiverThreadLooper = null;
            }

            if(broadcastReceiverThread != null)
            {
                try {
                    Log.d(TAG, "QuitReceiverThreadNicely():broadcastReceiverThread.quit()");
                    broadcastReceiverThread.quit();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                broadcastReceiverThread = null;
            }
        }
    }
}
