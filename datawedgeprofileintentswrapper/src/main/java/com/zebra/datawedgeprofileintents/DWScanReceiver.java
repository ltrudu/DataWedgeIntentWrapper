package com.zebra.datawedgeprofileintents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class DWScanReceiver {

    private String mIntentAction = "";
    private String mIntentCategory = "";
    private boolean mShowSpecialCharacters = false;
    private IntentFilter mIntentFilter = null;
    private Context mContext = null;

    /*
    An interface callback to receive the scanned data
     */
    public interface onScannedData
    {
        void scannedData(String source, String data, String typology);
    }

    private onScannedData mOnScannedDataCallback = null;

    /**
     * Broadcast receiver
     */
    private BroadcastReceiver mMessageReceiver = null;
    /**
     * To launch broadcast receiver in a separate thread
     */
    private boolean mUseSeparateThread = false;
    private Handler mBroadcastReceiverHandler = null;
    private HandlerThread mBroadcastReceiverThread = null;
    private Looper mBroadcastReceiverThreadLooper = null;
    /***
     * Object that handle the scans associated with the defined intent action and category
     * @param myContext : a reference to the Context that will handle the scans
     * @param intentAction : the action to listen to (defined in the DW intent plugin)
     * @param intentCategory : the category to listen to (defined in the DW intent plugin)
     * @param showSpecialChars : Will display any special character (CR, LR,...) inside brackets
     * @param scannedDataCallback : The interface to implement to receive the scanned date
     */
    public DWScanReceiver(Context myContext, String intentAction, String intentCategory
            , boolean showSpecialChars, onScannedData scannedDataCallback)
    {
        if(intentAction != null && intentAction.isEmpty() != true)
            mIntentAction = intentAction;
        else
        {
            mIntentAction = myContext.getPackageName().toString() + ".RECVR";
        }
        if(intentCategory != null && intentCategory.isEmpty() != true)
            mIntentCategory = intentCategory;
        else
        {
            mIntentCategory = "android.intent.category.DEFAULT";
        }
        mContext = myContext;

        mOnScannedDataCallback = scannedDataCallback;
        mShowSpecialCharacters = showSpecialChars;

        // Create the intent filter for further usage
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(mIntentAction);
        mIntentFilter.addCategory(mIntentCategory);

        // Create the message receiver
        mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                handleDecodeData(intent);
            }
        };
    }

    public DWScanReceiver(Context myContext, String intentAction, String intentCategory
            , boolean showSpecialChars, onScannedData scannedDataCallback, boolean useSeparateThread)
    {
        mIntentAction = intentAction;
        mIntentCategory = intentCategory;
        mContext = myContext;

        mOnScannedDataCallback = scannedDataCallback;
        mShowSpecialCharacters = showSpecialChars;

        // Create the intent filter for further usage
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(mIntentAction);
        mIntentFilter.addCategory(mIntentCategory);

        mUseSeparateThread = useSeparateThread;

        // Create the message receiver
        mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                handleDecodeData(intent);
            }
        };
    }

    public void startReceive()
    {
        if(mUseSeparateThread) {
            try {
                mBroadcastReceiverThread = new HandlerThread(mContext.getPackageName() + ".SCANNER.THREAD");//Create a thread for BroadcastReceiver
                mBroadcastReceiverThread.start();

                mBroadcastReceiverThreadLooper = mBroadcastReceiverThread.getLooper();
                mBroadcastReceiverHandler = new Handler(mBroadcastReceiverThreadLooper);

                mContext.registerReceiver(mMessageReceiver, mIntentFilter, null, mBroadcastReceiverHandler);
            } catch (Exception e) {
                e.printStackTrace();
                cleanReceiverThread();
            }
        }
        else
        {
            mContext.registerReceiver(mMessageReceiver, mIntentFilter);
        }
        // Register the internal broadcast receiver when we are alive
    }

    public void stopReceive()
    {
        try
        {
            if(mBroadcastReceiverThread != null)
            {
                // Unregister internal broadcast receiver when we are going in background
                cleanReceiverThread();
                mContext.unregisterReceiver(mMessageReceiver);
            }
            else
            {
                mContext.unregisterReceiver(mMessageReceiver);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void cleanReceiverThread()
    {
        if(mUseSeparateThread && mBroadcastReceiverThread != null)
        {
            if(mBroadcastReceiverHandler != null)
            {
                mBroadcastReceiverHandler.removeCallbacksAndMessages(null);
                mBroadcastReceiverHandler = null;
            }
            if(mBroadcastReceiverThreadLooper != null) {
                mBroadcastReceiverThreadLooper.quit();
                mBroadcastReceiverThreadLooper = null;
            }
            if(mBroadcastReceiverThread != null) {
                mBroadcastReceiverThread.quit();
                mBroadcastReceiverThread = null;
            }
        }
    }

    // This method is responsible for getting the data from the intent
    // formatting it and adding it to the end of the edit box
    private boolean handleDecodeData(Intent i) {
        // check the intent action is for us
        if ( i.getAction().contentEquals(mIntentAction) ) {
            // define a string that will hold our output
            String out = "";
            // get the source of the data
            String source = i.getStringExtra(DataWedgeConstants.SOURCE_TAG);
            // save it to use later
            if (source == null) source = "scanner";
            // get the data from the intent
            String data = i.getStringExtra(DataWedgeConstants.DATA_STRING_TAG);

            String sLabelType = null;

            // check if the data has come from the barcode scanner
            if (source.equalsIgnoreCase("scanner")) {
                // check if there is anything in the data
                if (data != null && data.length() > 0) {
                    // we have some data, so let's get it's symbology
                    sLabelType = i.getStringExtra(DataWedgeConstants.LABEL_TYPE_TAG);
                    // check if the string is empty
                    if (sLabelType != null && sLabelType.length() > 0) {
                        // format of the label type string is LABEL-TYPE-SYMBOLOGY
                        // so let's skip the LABEL-TYPE- portion to get just the symbology
                        sLabelType = sLabelType.substring(11);
                    }
                    else {
                        // the string was empty so let's set it to "Unknown"
                        sLabelType = "Unknown";
                    }
                }
            }


            if(data != null && mShowSpecialCharacters)
            {
               data = showSpecialChars(data);
            }

            if(mOnScannedDataCallback != null)
            {
                mOnScannedDataCallback.scannedData(source, data, sLabelType);
            }

            return true;
        }
        return false;
    }

    private String showSpecialChars(String data)
    {
        String returnString="";
        char[] dataChar = data.toCharArray();
        for(char acar : dataChar)
        {
            if(!Character.isISOControl(acar))
            {
                returnString += acar;
            }
            else
            {
                returnString += "["+(int)acar+"]";
            }
        }

        return returnString;
    }

}
