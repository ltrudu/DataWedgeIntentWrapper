package com.zebra.datawedgeprofileintents;

import android.content.Context;
import android.util.Pair;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

public class DWSynchronousMethodsNT {
    
    private String mLastMessage = "";
    private DWSynchronousMethods.EResults mLastResult = DWSynchronousMethods.EResults.NONE;
    private CountDownLatch mJobDoneLatch = null;
    private Context mContext = null;
    private long mSleepTimer = 50L;

    public DWSynchronousMethodsNT(Context context)
    {
        mContext = context;
        mSleepTimer = 50L;
    }

    public DWSynchronousMethodsNT(Context context, long sleepTimer)
    {
        mContext = context;
        mSleepTimer = sleepTimer;
    }

    private class SynchronousNTRunnable implements Runnable
    {
        private String mMethodName;
        private Object mParam;
        private Class<?> mParamClass;
        private Context mContext;
        public Pair<DWSynchronousMethods.EResults, String> mResults = null;
        public boolean mHasFinished = false;
        private int ExecutionTimeoutMs = 3 * 1000;


        public SynchronousNTRunnable(Context context, String methodName, Object param, Class<?> paramClass)
        {
            mMethodName = methodName;
            mParam = param;
            mParamClass = paramClass;
            mContext = context;
        }

        @Override
        public void run() {
            try {
                mHasFinished = false;
                Method method;
                Object result = null;
                DWSynchronousMethods dwSynchronousMethods = new DWSynchronousMethods(mContext);
                method = DWSynchronousMethods.class.getMethod(mMethodName, mParamClass);
                result = method.invoke(dwSynchronousMethods, mParam);
                if(result != null)
                {
                    mResults = (Pair<DWSynchronousMethods.EResults, String>)result;
                }
                else
                    mResults = null;
                mHasFinished = true;
                
            } catch (Exception e) {
                // Even if an error occurs, we must terminate the action to avoid waiting an infinite loop
                mHasFinished = true;

                e.printStackTrace();
            }
        }
    }

    /**
     * Return message left by the last executed method
     * it can be an error message if the method returned an Error result.
     * @return
     */
    public String getLastMessage()
    {
        return mLastMessage;
    }

    public Pair<DWSynchronousMethods.EResults,String> runInNewThread(String methodName, Object param, Class<?> paramClass) {
        SynchronousNTRunnable synchronousNTRunnable = new SynchronousNTRunnable(mContext, methodName, param, paramClass);
        Thread synchronizedThread = new Thread(synchronousNTRunnable);
        synchronizedThread.start();

        int totalElapsedMs = 0;
        while (!synchronousNTRunnable.mHasFinished) {
            try {
                // Sorry but we are not at school anymore...
                // And this is the only method I found to prevent ANR when
                // us use specific threading model (didn't found why on some
                // multi-thread apps it Countdownlatch usage throws an Android
                // Not Responding (ANR) exception.
                // This awful solution resolve the issue....
                // If you have a better solution, you are welcome
                // to do a PULL request on the code.
                Thread.sleep(mSleepTimer);

                // However, ANR still occurs. Notably on TC52X. So we add a timeout.
                totalElapsedMs += mSleepTimer;
                if (totalElapsedMs > synchronousNTRunnable.ExecutionTimeoutMs)
                    throw new TimeoutException("Unable to get thread result in " + synchronousNTRunnable.ExecutionTimeoutMs + "ms");

            } catch (InterruptedException e) {
                // Ré-interruption to current thread if InterruptedException
                Thread.currentThread().interrupt();
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
                break; // Exit loop on timeout
            } catch (Throwable e) {
                // on android this may not be allowed, that's why we
                // catch throwable the wait should be very short because we are
                // just waiting for the bind of the socket

                // However, we want to know if a other crash occurs. So we log it.
                e.printStackTrace();
            }
        }

        if (synchronizedThread.isAlive()) // In case of timeout : stop the thread
            synchronizedThread.interrupt();

        return synchronousNTRunnable.mResults;
    }

    public Pair<DWSynchronousMethods.EResults,String> setupDWProfile(final DWProfileSetConfigSettings settings) 
    {
        return runInNewThread("setupDWProfile", settings, DWProfileSetConfigSettings.class);
    }
    
    public Pair<DWSynchronousMethods.EResults, String> enablePlugin()
    {
        return runInNewThread("enablePlugin", mContext.getPackageName(), String.class);
    }

    public Pair<DWSynchronousMethods.EResults, String> enablePlugin(String profileName)
    {
        return runInNewThread("enablePlugin", profileName, String.class);
    }

    public Pair<DWSynchronousMethods.EResults, String> disablePlugin()
    {
        return runInNewThread("disablePlugin", mContext.getPackageName(), String.class);
    }
    
    public Pair<DWSynchronousMethods.EResults, String> disablePlugin(String profileName)
    {
        return runInNewThread("disablePlugin", profileName, String.class);
    }

    public Pair<DWSynchronousMethods.EResults, String> startScan()
    {
        return runInNewThread("startScan", mContext.getPackageName(), String.class);
    }
    
    public Pair<DWSynchronousMethods.EResults, String> startScan(String profileName)
    {
        return runInNewThread("startScan", profileName, String.class);
    }

    public Pair<DWSynchronousMethods.EResults, String> stopScan()
    {
        return runInNewThread("stopScan", mContext.getPackageName(), String.class);
    }
    
    public Pair<DWSynchronousMethods.EResults, String> stopScan(String profileName)
    {
        return runInNewThread("stopScan", profileName, String.class);
    }


    public Pair<DWSynchronousMethods.EResults, String> profileExists()
    {
        return runInNewThread("profileExists", mContext.getPackageName(), String.class);
    }

    public Pair<DWSynchronousMethods.EResults, String> profileExists(String profileName)
    {
        return runInNewThread("profileExists", profileName, String.class);
    }

    public Pair<DWSynchronousMethods.EResults, String> deleteProfile()
    {
        return runInNewThread("deleteProfile", mContext.getPackageName(), String.class);
    }

    public Pair<DWSynchronousMethods.EResults, String> deleteProfile(String profileName)
    {
        return runInNewThread("deleteProfile", profileName, String.class);
    }

    public Pair<DWSynchronousMethods.EResults, String> switchBarcodeParams(DWProfileSwitchBarcodeParamsSettings settings)
    {
        return runInNewThread("switchBarcodeParams", settings, DWProfileSwitchBarcodeParamsSettings.class);
    }

    private class SynchronousEnumerateScannersRunnable implements Runnable
    {
        private DWEnumerateScannersSettings mDWEnumerateScannersSettings;
        private Context mContext;
        public Pair<DWSynchronousMethods.EResults, Pair<String, List<DWEnumerateScanners.Scanner>>> mResults = null;
        public boolean mHasFinished = false;

        public SynchronousEnumerateScannersRunnable(Context context, DWEnumerateScannersSettings settings)
        {
            mDWEnumerateScannersSettings = settings;
            mContext = context;
        }

        @Override
        public void run() {
            try {
                mHasFinished = false;
                Method method;
                Object result = null;
                DWSynchronousMethods dwSynchronousMethods = new DWSynchronousMethods(mContext);
                method = DWSynchronousMethods.class.getMethod("enumerateScanners", DWEnumerateScannersSettings.class);
                result = method.invoke(dwSynchronousMethods, mDWEnumerateScannersSettings);
                if(result != null)
                {
                    mResults = (Pair<DWSynchronousMethods.EResults, Pair<String, List<DWEnumerateScanners.Scanner>>>)result;
                }
                else
                    mResults = null;
                mHasFinished = true;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public Pair<DWSynchronousMethods.EResults, Pair<String, List<DWEnumerateScanners.Scanner>>> enumerateScanners(DWEnumerateScannersSettings settings)
    {
        // For this case, we are returning a custom structure, so we do all the things inside this class according to our needs (returning a pair of pairs)
        SynchronousEnumerateScannersRunnable synchronousEnumerateScannersRunnable = new SynchronousEnumerateScannersRunnable(mContext, settings);
        Thread synchronizedThread = new Thread(synchronousEnumerateScannersRunnable);
        synchronizedThread.start();
        while (synchronousEnumerateScannersRunnable.mHasFinished == false) {
            try {
                Thread.sleep(mSleepTimer);
            } catch (Throwable e) {
                // on android this may not be allowed, that's why we
                // catch throwable the wait should be very short because we are
                // just waiting for the bind of the socket
            }
        }
        return synchronousEnumerateScannersRunnable.mResults;
    }

}
