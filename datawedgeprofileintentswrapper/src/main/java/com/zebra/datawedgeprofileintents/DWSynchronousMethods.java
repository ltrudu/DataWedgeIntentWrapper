package com.zebra.datawedgeprofileintents;

import android.content.Context;
import android.os.Looper;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DWSynchronousMethods {

    public enum EResults
    {
        SUCCEEDED,
        FAILED,
        TIMEOUT,
        NONE
    }

    private String mLastMessage = "";
    private EResults mLastResult = EResults.NONE;
    private boolean mIsDWPluginEnabledResult = false;
    private List<DWEnumerateScanners.Scanner> mEnumerateScannersReturnList = null;
    private CountDownLatch mJobDoneLatch = null;
    private Context mContext = null;

    public DWSynchronousMethods(Context context)
    {
        mContext = context;
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


    public Pair<EResults,String> setupDWProfile(final DWProfileSetConfigSettings settings) {

        // Profile name is forced to package name when using sync methods
        // it is not possible to get more than one profile on the same app
        // so managing profiles names is not relevant
        settings.mProfileName = mContext.getPackageName();

        // First delete the profile synchronously
        deleteProfile(settings.mProfileName);
        // Then setup the new profile

        if(mJobDoneLatch != null)
        {
            mLastMessage = "DataWedge Service: Error, a job is already running in background. Please wait for it to finish or timeout.";
            return new Pair<>(EResults.FAILED, "DataWedge Service: Error, a job is already running in background. Please wait for it to finish or timeout.");
        }

        mJobDoneLatch = new CountDownLatch(1);

        if(settings.mEnableTimeOutMechanism)
        {
            try
            {
                Looper.prepare();
            }
            catch(Exception e)
            {
            }
        }

        final DWProfileSetConfig setConfig = new DWProfileSetConfig(mContext);
        final DWProfileCommandBase.onProfileCommandResult onSetConfigCommandResult = new DWProfileCommandBase.onProfileCommandResult() {
            @Override
            public void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier) {
                if(result.equalsIgnoreCase(DataWedgeConstants.COMMAND_RESULT_SUCCESS))
                {
                    mLastMessage = "Setup DataWedge: succeeded for profile:" + settings.mProfileName;
                    mLastResult = EResults.SUCCEEDED;
                    mJobDoneLatch.countDown();
                }
                else
                {
                    mLastMessage = "Setup Scanner: error on profile(" +settings.mProfileName +"):"+ resultInfo;
                    mLastResult = EResults.FAILED;
                    mJobDoneLatch.countDown();
                }
            }

            @Override
            public void timeout(String profileName) {
                mLastMessage = "Setup Scanner: timeout while trying to setup profile: " + settings.mProfileName;
                mLastResult = EResults.TIMEOUT;
                mJobDoneLatch.countDown();
            }
        };
        setConfig.execute(settings, onSetConfigCommandResult);

        try {
            mJobDoneLatch.await();
            mJobDoneLatch = null;
            return new Pair<>(mLastResult, mLastMessage);
        } catch (InterruptedException e) {
            if(mJobDoneLatch != null)
            {
                while(mJobDoneLatch.getCount() > 0)
                    mJobDoneLatch.countDown();
                mJobDoneLatch = null;
            }
            return new Pair<>(EResults.FAILED, "Setup Scanner: Exception while waiting for CountDownLatch : " + e.getMessage());
        }
    }

    public Pair<EResults, String> enablePlugin()
    {
        return enablePlugin(mContext.getPackageName());
    }

    public Pair<EResults, String> enablePlugin(final String profileName)
    {
        if(mJobDoneLatch != null)
        {
            return new Pair<>(EResults.FAILED, "DataWedge Service: Error, a job is already running in background. Please wait for it to finish or timeout.");
        }

        mJobDoneLatch = new CountDownLatch(1);

        DWProfileBaseSettings settings = new DWProfileBaseSettings()
        {{
            mProfileName = profileName;
        }};

        try
        {
            Looper.prepare();
        }
        catch(Exception e)
        {
        }

        DWScannerPluginEnable dwpluginenable = new DWScannerPluginEnable(mContext);
        dwpluginenable.execute(settings, new DWProfileCommandBase.onProfileCommandResult() {
            @Override
            public void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier) {
                if(result.equalsIgnoreCase(DataWedgeConstants.COMMAND_RESULT_SUCCESS))
                {
                    mLastMessage = "Enable Scanner: succeeded for profile:" + profileName;
                    mLastResult = EResults.SUCCEEDED;
                    mJobDoneLatch.countDown();
                }
                else
                {
                    mLastMessage = "Enable Scanner: error on profile(" +profileName +"):"+ resultInfo;
                    mLastResult = EResults.FAILED;
                    mJobDoneLatch.countDown();
                }
            }
            @Override
            public void timeout(String profileName) {
                mLastMessage = "Enable Scanner: timeout while trying to enable scanner on profile: " + profileName;
                mLastResult = EResults.TIMEOUT;
                mJobDoneLatch.countDown();
            }
        });

        try {
            mJobDoneLatch.await();
            mJobDoneLatch = null;
            return new Pair<>(mLastResult, mLastMessage);
        } catch (InterruptedException e) {
            if(mJobDoneLatch != null)
            {
                while(mJobDoneLatch.getCount() > 0)
                    mJobDoneLatch.countDown();
                mJobDoneLatch = null;
            }
            return new Pair<>(EResults.FAILED, "Enable Scanner: Exception while waiting for CountDownLatch : " + e.getMessage());
        }
    }

    public Pair<EResults, String> disablePlugin()
    {
        return disablePlugin(mContext.getPackageName());
    }

    public Pair<EResults, String> disablePlugin(final String profileName)
    {
        if(mJobDoneLatch != null)
        {
            return new Pair<>(EResults.FAILED, "DataWedge Service: Error, a job is already running in background. Please wait for it to finish or timeout.");
        }

        mJobDoneLatch = new CountDownLatch(1);

        DWProfileBaseSettings settings = new DWProfileBaseSettings()
        {{
            mProfileName = profileName;
        }};

        try
        {
            Looper.prepare();
        }
        catch(Exception e)
        {
        }

        DWScannerPluginDisable dwplugindisable = new DWScannerPluginDisable(mContext);
        dwplugindisable.execute(settings, new DWProfileCommandBase.onProfileCommandResult() {
            @Override
            public void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier) {
                if(result.equalsIgnoreCase(DataWedgeConstants.COMMAND_RESULT_SUCCESS))
                {
                    mLastMessage = "Disable Scanner: succeeded for profile:" + profileName;
                    mLastResult = EResults.SUCCEEDED;
                    mJobDoneLatch.countDown();
                }
                else
                {
                    mLastMessage = "Disable Scanner: error on profile(" +profileName +"):"+ resultInfo;
                    mLastResult = EResults.FAILED;
                    mJobDoneLatch.countDown();
                }
            }
            @Override
            public void timeout(String profileName) {
                mLastMessage = "Disable Scanner: timeout while trying to enable scanner on profile: " + profileName;
                mLastResult = EResults.TIMEOUT;
                mJobDoneLatch.countDown();
            }
        });

        try {
            mJobDoneLatch.await();
            mJobDoneLatch = null;
            return new Pair<>(mLastResult, mLastMessage);
        } catch (InterruptedException e) {
            if(mJobDoneLatch != null)
            {
                while(mJobDoneLatch.getCount() > 0)
                    mJobDoneLatch.countDown();
                mJobDoneLatch = null;
            }
            return new Pair<>(EResults.FAILED, "Disable Scanner: Exception while waiting for CountDownLatch : " + e.getMessage());
        }
    }

    public Pair<EResults, String> startScan()
    {
        return startScan(mContext.getPackageName());
    }

    public Pair<EResults, String> startScan(final String profileName)
    {
        if(mJobDoneLatch != null)
        {
            return new Pair<>(EResults.FAILED, "Start Scan: Error, a job is already running in background. Please wait for it to finish or timeout.");
        }

        mJobDoneLatch = new CountDownLatch(1);

        DWProfileBaseSettings settings = new DWProfileBaseSettings()
        {{
            mProfileName = profileName;
        }};

        try
        {
            Looper.prepare();
        }
        catch(Exception e)
        {
        }

        DWScannerStartScan dwstartscan = new DWScannerStartScan(mContext);
        dwstartscan.execute(settings, new DWProfileCommandBase.onProfileCommandResult() {
            @Override
            public void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier) {
                if(result.equalsIgnoreCase(DataWedgeConstants.COMMAND_RESULT_SUCCESS))
                {
                    mLastMessage = "Start Scanner: succeeded for profile:" + profileName;
                    mLastResult = EResults.SUCCEEDED;
                    mJobDoneLatch.countDown();
                }
                else
                {
                    mLastMessage = "Start Scanner: error on profile(" +profileName +"):"+ resultInfo;
                    mLastResult = EResults.FAILED;
                    mJobDoneLatch.countDown();
                }
            }
            @Override
            public void timeout(String profileName) {
                mLastMessage = "Start Scanner: timeout while trying to enable scanner on profile: " + profileName;
                mLastResult = EResults.TIMEOUT;
                mJobDoneLatch.countDown();
            }
        });


        try {
            mJobDoneLatch.await();
            mJobDoneLatch = null;
            return new Pair<>(mLastResult, mLastMessage);
        } catch (InterruptedException e) {
            if(mJobDoneLatch != null)
            {
                while(mJobDoneLatch.getCount() > 0)
                    mJobDoneLatch.countDown();
                mJobDoneLatch = null;
            }
            return new Pair<>(EResults.FAILED, "Start Scan: Exception while waiting for CountDownLatch : " + e.getMessage());
        }
    }

    public Pair<EResults, String> stopScan()
    {
        return stopScan(mContext.getPackageName());
    }

    public Pair<EResults, String> stopScan(final String profileName)
    {
        if(mJobDoneLatch != null)
        {
            return new Pair<>(EResults.FAILED, "Stop Scan: Error, a job is already running in background. Please wait for it to finish or timeout.");
        }

        mJobDoneLatch = new CountDownLatch(1);

        DWProfileBaseSettings settings = new DWProfileBaseSettings()
        {{
            mProfileName = profileName;
        }};

        try
        {
            Looper.prepare();
        }
        catch(Exception e)
        {
        }

        DWScannerStopScan dwstopscan = new DWScannerStopScan(mContext);
        dwstopscan.execute(settings, new DWProfileCommandBase.onProfileCommandResult() {
            @Override
            public void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier) {
                if(result.equalsIgnoreCase(DataWedgeConstants.COMMAND_RESULT_SUCCESS))
                {
                    mLastMessage = "Stop Scanner: succeeded for profile:" + profileName;
                    mLastResult = EResults.SUCCEEDED;
                    mJobDoneLatch.countDown();
                }
                else
                {
                    mLastMessage = "Stop Scanner: error on profile(" +profileName +"):"+ resultInfo;
                    mLastResult = EResults.FAILED;
                    mJobDoneLatch.countDown();
                }
            }
            @Override
            public void timeout(String profileName) {
                mLastMessage = "Stop Scanner: timeout while trying to enable scanner on profile: " + profileName;
                mLastResult = EResults.TIMEOUT;
                mJobDoneLatch.countDown();
            }
        });


        try {
            mJobDoneLatch.await();
            mJobDoneLatch = null;
            return new Pair<>(mLastResult, mLastMessage);
        } catch (InterruptedException e) {
            if(mJobDoneLatch != null)
            {
                while(mJobDoneLatch.getCount() > 0)
                    mJobDoneLatch.countDown();
                mJobDoneLatch = null;
            }
            return new Pair<>(EResults.FAILED, "Stop Scan: Exception while waiting for CountDownLatch : " + e.getMessage());
        }
    }

    public Pair<EResults, String> profileExists()
    {
        return profileExists(mContext.getPackageName());
    }

    public Pair<EResults, String> profileExists(final String profileName)
    {
        if(mJobDoneLatch != null)
        {
            return new Pair<>(EResults.FAILED, "profileExists: Error, a job is already running in background. Please wait for it to finish or timeout.");
        }

        mJobDoneLatch = new CountDownLatch(1);

        DWProfileCheckerSettings settings = new DWProfileCheckerSettings()
        {{
            mProfileName = profileName;
        }};

        try
        {
            Looper.prepare();
        }
        catch(Exception e)
        {
        }

        DWProfileChecker dwprofilechecker = new DWProfileChecker(mContext);
        dwprofilechecker.execute(settings, new DWProfileChecker.onProfileExistResult() {
            @Override
            public void result(String profileName, boolean exists) {
                if(exists)
                {
                    mLastMessage = profileName + " exists.";
                    mLastResult = EResults.SUCCEEDED;
                }
                else
                {
                    mLastMessage = profileName + " does not exists.";
                    mLastResult = EResults.FAILED;
                }
                mJobDoneLatch.countDown();
            }

            @Override
            public void timeOut(String profileName) {
                mLastMessage = "profileExists: timeout while trying to enable check if profile exists: " + profileName;
                mLastResult = EResults.TIMEOUT;
                mJobDoneLatch.countDown();
            }
        });


        try {
            mJobDoneLatch.await();
            mJobDoneLatch = null;
            return new Pair<>(mLastResult, mLastMessage);
        } catch (InterruptedException e) {
            if(mJobDoneLatch != null)
            {
                while(mJobDoneLatch.getCount() > 0)
                    mJobDoneLatch.countDown();
                mJobDoneLatch = null;
            }
            return new Pair<>(EResults.FAILED, "profileExists: Exception while waiting for CountDownLatch : " + e.getMessage());
        }
    }

    public Pair<EResults, String> isDWPluginEnabled()
    {
        return isDWPluginEnabled(mContext.getPackageName());
    }

    public Pair<EResults, String> isDWPluginEnabled(final String profileName)
    {
        if(mJobDoneLatch != null)
        {
            return new Pair<>(EResults.FAILED, "isDWPluginEnabled: Error, a job is already running in background. Please wait for it to finish or timeout.");
        }

        mJobDoneLatch = new CountDownLatch(1);

        DWProfileBaseSettings settings = new DWProfileBaseSettings()
        {{
            mProfileName = profileName;
        }};

        try
        {
            Looper.prepare();
        }
        catch(Exception e)
        {
        }

        DWScannerPluginStatus dwScannerPluginStatus = new DWScannerPluginStatus(mContext);
        dwScannerPluginStatus.execute(settings, new DWScannerPluginStatus.onScannerPluginStatus() {
            @Override
            public void result(String profileName, boolean isEnabled) {
                if(isEnabled)
                {
                    mLastMessage = "true";
                    mLastResult = EResults.SUCCEEDED;

                }
                else
                {
                    mLastMessage = "false";
                    mLastResult = EResults.FAILED;
                }
                mJobDoneLatch.countDown();
            }

            @Override
            public void timeOut(String profileName) {
                mLastMessage = "isDWPluginEnabled: timeout while trying to enable check if profile exists: " + profileName;
                mLastResult = EResults.TIMEOUT;
                mJobDoneLatch.countDown();
            }
        });


        try {
            mJobDoneLatch.await();
            mJobDoneLatch = null;
            return new Pair<>(mLastResult, mLastMessage);
        } catch (InterruptedException e) {
            if(mJobDoneLatch != null)
            {
                while(mJobDoneLatch.getCount() > 0)
                    mJobDoneLatch.countDown();
                mJobDoneLatch = null;
            }
            return new Pair<>(EResults.FAILED, "isDWPluginEnabled: Exception while waiting for CountDownLatch : " + e.getMessage());
        }
    }


    public Pair<EResults, String> deleteProfile()
    {
        return deleteProfile(mContext.getPackageName());
    }


    public Pair<EResults, String> deleteProfile(final String profileName)
    {
        if(mJobDoneLatch != null)
        {
            return new Pair<>(EResults.FAILED, "deleteProfile: Error, a job is already running in background. Please wait for it to finish or timeout.");
        }

        mJobDoneLatch = new CountDownLatch(1);

        DWProfileDeleteSettings settings = new DWProfileDeleteSettings()
        {{
            mProfileName = profileName;
        }};

        try
        {
            Looper.prepare();
        }
        catch(Exception e)
        {
        }

        DWProfileDelete dwprofileDelete = new DWProfileDelete(mContext);
        dwprofileDelete.execute(settings, new DWProfileCommandBase.onProfileCommandResult() {
            @Override
            public void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier) {
                if(result.equalsIgnoreCase(DataWedgeConstants.COMMAND_RESULT_SUCCESS))
                {
                    mLastMessage = "Profile: " + profileName + " delete succeeded";
                    mLastResult = EResults.SUCCEEDED;
                    mJobDoneLatch.countDown();
                }
                else
                {
                    mLastMessage = "Error while trying to delete profile: " + profileName + "\n" + resultInfo;
                    mLastResult = EResults.FAILED;
                    mJobDoneLatch.countDown();
                }
            }

            @Override
            public void timeout(String profileName) {
                mLastMessage = "deleteProfile: timeout while trying to delete profile: " + profileName;
                mLastResult = EResults.TIMEOUT;
                mJobDoneLatch.countDown();
            }
        });


        try {
            mJobDoneLatch.await();
            mJobDoneLatch = null;
            return new Pair<>(mLastResult, mLastMessage);
        } catch (InterruptedException e) {
            if(mJobDoneLatch != null)
            {
                while(mJobDoneLatch.getCount() > 0)
                    mJobDoneLatch.countDown();
                mJobDoneLatch = null;
            }
            return new Pair<>(EResults.FAILED, "deleteProfile: Exception while waiting for CountDownLatch : " + e.getMessage());
        }
    }


    public Pair<EResults, String> switchBarcodeParams(DWProfileSwitchBarcodeParamsSettings settings)
    {
        if(mJobDoneLatch != null)
        {
            return new Pair<>(EResults.FAILED, "switchBarcodeParams: Error, a job is already running in background. Please wait for it to finish or timeout.");
        }

        mJobDoneLatch = new CountDownLatch(1);

        // Force package name on settings
        settings.mProfileName = mContext.getPackageName();

        try
        {
            Looper.prepare();
        }
        catch(Exception e)
        {
        }

        DWProfileSwitchBarcodeParams dwProfileSwitchBarcodeParams = new DWProfileSwitchBarcodeParams(mContext);
        dwProfileSwitchBarcodeParams.execute(settings, new DWProfileCommandBase.onProfileCommandResult() {
            @Override
            public void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier) {
                if(result.equalsIgnoreCase(DataWedgeConstants.COMMAND_RESULT_SUCCESS))
                {
                    mLastMessage = "Profile: " + profileName + " barcode parameters updated successfully";
                    mLastResult = EResults.SUCCEEDED;
                    mJobDoneLatch.countDown();
                }
                else
                {
                    mLastMessage = "Error while trying to update barcode parameters on profile: " + profileName + "\n" + resultInfo;
                    mLastResult = EResults.FAILED;
                    mJobDoneLatch.countDown();
                }
            }

            @Override
            public void timeout(String profileName) {
                mLastMessage = "deleteProfile: timeout while trying to delete profile: " + profileName;
                mLastResult = EResults.TIMEOUT;
                mJobDoneLatch.countDown();
            }
        });


        try {
            mJobDoneLatch.await();
            mJobDoneLatch = null;
            return new Pair<>(mLastResult, mLastMessage);
        } catch (InterruptedException e) {
            if(mJobDoneLatch != null)
            {
                while(mJobDoneLatch.getCount() > 0)
                    mJobDoneLatch.countDown();
                mJobDoneLatch = null;
            }
            return new Pair<>(EResults.FAILED, "switchBarcodeParams: Exception while waiting for CountDownLatch : " + e.getMessage());
        }
    }

    public Pair<EResults, Pair<String, List<DWEnumerateScanners.Scanner>>> enumerateScanners(DWEnumerateScannersSettings settings)
    {
        if(mJobDoneLatch != null)
        {
            return new Pair<>(EResults.FAILED, new Pair<>("enumerateScanners: Error, a job is already running in background. Please wait for it to finish or timeout.", (List<DWEnumerateScanners.Scanner>)null));
        }

        mEnumerateScannersReturnList = null;

        mJobDoneLatch = new CountDownLatch(1);

        // Force package name on settings
        settings.mProfileName = mContext.getPackageName();

        try
        {
            Looper.prepare();
        }
        catch(Exception e)
        {
        }

        DWEnumerateScanners dwEnumerateScanners = new DWEnumerateScanners(mContext);
        dwEnumerateScanners.execute(settings, new DWEnumerateScanners.onEnumerateScannerResult(){
            @Override
            public void result(String profileName, List<DWEnumerateScanners.Scanner> scannerList) {
                if(scannerList != null && scannerList.size() != 0) {
                    mLastMessage = "enumerateScanners: Scanners enumerated successfully.";
                    mLastResult = EResults.SUCCEEDED;
                    mEnumerateScannersReturnList = new ArrayList<>(scannerList);
                    mJobDoneLatch.countDown();
                }
                else
                {
                    mLastMessage = "enumerateScanners: Error while trying to enumerate scanners, returned list is empty.";
                    mLastResult = EResults.FAILED;
                    mEnumerateScannersReturnList = null;
                    mJobDoneLatch.countDown();
                }
            }

            @Override
            public void timeOut(String profileName) {
                mLastMessage = "enumerateScanners: timeout while trying to enumerate scanners.";
                mLastResult = EResults.TIMEOUT;
                mEnumerateScannersReturnList = null;
                mJobDoneLatch.countDown();
            }
        });


        try {
            mJobDoneLatch.await();
            mJobDoneLatch = null;
            return new Pair<>(mLastResult, new Pair<>(mLastMessage, mEnumerateScannersReturnList));
        } catch (InterruptedException e) {
            if(mJobDoneLatch != null)
            {
                while(mJobDoneLatch.getCount() > 0)
                    mJobDoneLatch.countDown();
                mJobDoneLatch = null;
            }
            mEnumerateScannersReturnList = null;
            return new Pair<>(EResults.FAILED, new Pair<>("switchBarcodeParams: Exception while waiting for CountDownLatch : " + e.getMessage(), mEnumerateScannersReturnList));
        }
    }
}
