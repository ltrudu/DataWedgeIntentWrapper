package com.zebra.datawedgeprofileintents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.zebra.datawedgeprofileenums.SC_E_SCANNER_IDENTIFIER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by laure on 16/04/2018.
 */

public class DWEnumerateScanners extends DWProfileBase {

    private Handler broadcastReceiverHandler = null;
    private HandlerThread broadcastReceiverThread = null;
    private Looper broadcastReceiverThreadLooper = null;

    public DWEnumerateScanners(Context aContext) {
        super(aContext);
        mBroadcastReceiver = new enumerateScannerReceiver();
    }

    public static class Scanner
    {
        public String mName = "";
        public int mIndex = -1;
        public boolean mScannerConnectionState = false;
        public SC_E_SCANNER_IDENTIFIER mScannerIdentifier = SC_E_SCANNER_IDENTIFIER.AUTO;
    }

    /*
        An interface callback to be informed of the result
        when checking if a profile exists
         */
    public interface onEnumerateScannerResult
    {
        void result(String profileName, List<Scanner> scannerList);
        void timeOut(String profileName);
    }

    /*
    A store to keep the callback to be fired when we will get the
    result of the intent
     */
    private onEnumerateScannerResult mEnumerateScannerCallback = null;

    /*
    The receiver that we will register to retrieve DataWedge answer
     */
    private enumerateScannerReceiver mBroadcastReceiver = null;

    public void execute(DWEnumerateScannersSettings settings, onEnumerateScannerResult callback)
    {
        /*
        Launch timeout mechanism
         */
        super.execute(settings);

        mEnumerateScannerCallback = callback;

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

        mContext.registerReceiver(mBroadcastReceiver, intentFilter, null, broadcastReceiverHandler);

        /*
        Ask for DataWedge profile list
         */
        sendDataWedgeIntentWithExtra(DataWedgeConstants.ACTION_DATAWEDGE_FROM_6_2, DataWedgeConstants.EXTRA_ENUMERATESCANNERS_FROM_6_3, DataWedgeConstants.EXTRA_EMPTY);

    }

    private class enumerateScannerReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra(DataWedgeConstants.EXTRA_RESULT_ENUMERATE_SCANNERS_FROM_6_3))
            {
                ArrayList<Scanner> returnList = new ArrayList<>();
                ArrayList<Bundle> scannerList = (ArrayList<Bundle>) intent.getSerializableExtra(DataWedgeConstants.EXTRA_RESULT_ENUMERATE_SCANNERS_FROM_6_3);
                if((scannerList != null) && (scannerList.size() > 0)) {
                    for (Bundle bunb : scannerList){
                        Scanner scanner = new Scanner();
                        scanner.mName = bunb.getString("SCANNER_NAME");
                        scanner.mScannerConnectionState = bunb.getBoolean("SCANNER_CONNECTION_STATE");
                        scanner.mIndex = bunb.getInt("SCANNER_INDEX");
                        scanner.mScannerIdentifier = SC_E_SCANNER_IDENTIFIER.fromString(bunb.getString("SCANNER_IDENTIFIER"));
                        returnList.add(scanner);
                    }
                }
                if(mEnumerateScannerCallback != null)
                {
                    mEnumerateScannerCallback.result(mSettings.mProfileName, returnList);
                    cleanAll();
                }
            }
        }
    }

    @Override
    protected void cleanAll()
    {
        mSettings.mProfileName = "";
        mEnumerateScannerCallback = null;
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
        if(mEnumerateScannerCallback != null)
        {
            mEnumerateScannerCallback.timeOut(mSettings.mProfileName);
            cleanAll();
        }
    }
}
