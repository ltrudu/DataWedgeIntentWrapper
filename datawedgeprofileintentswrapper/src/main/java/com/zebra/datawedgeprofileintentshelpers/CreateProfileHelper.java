package com.zebra.datawedgeprofileintentshelpers;

import android.content.Context;

import com.zebra.datawedgeprofileintents.DWProfileChecker;
import com.zebra.datawedgeprofileintents.DWProfileCheckerSettings;
import com.zebra.datawedgeprofileintents.DWProfileCommandBase;
import com.zebra.datawedgeprofileintents.DWProfileCreate;
import com.zebra.datawedgeprofileintents.DWProfileCreateSettings;
import com.zebra.datawedgeprofileintents.DWProfileDelete;
import com.zebra.datawedgeprofileintents.DWProfileDeleteSettings;
import com.zebra.datawedgeprofileintents.DWProfileSetConfig;
import com.zebra.datawedgeprofileintents.DWProfileSetConfigSettings;
import com.zebra.datawedgeprofileintents.DataWedgeConstants;

import java.util.Date;

public class CreateProfileHelper {

    public interface CreateProfileHelperCallback
    {
        void onSuccess(String profileName);
        void onError(String profileName, String error, String errorMessage);
        void ondebugMessage(String profileName, String message);
    }

    public static void createProfile(Context context, DWProfileSetConfigSettings settings, CreateProfileHelperCallback callback)
    {
        debugMessage(callback, settings.mProfileName, "Creating profile :" + settings.mProfileName + " with custom settings.");
        setupProfileAsync(context, settings, callback);
    }
    
    private static void debugMessage(CreateProfileHelperCallback callback, String profileName, String message)
    {
        if(callback != null)
        {
            callback.ondebugMessage(profileName, message);
        }
    }

    private static void setupProfileAsync(Context context, DWProfileSetConfigSettings settings, CreateProfileHelperCallback callback)
    {
        debugMessage(callback, settings.mProfileName, "Checking if profile: " + settings.mProfileName + " exists.");
        /*
        The profile checker will check if the profile already exists
         */
        DWProfileChecker checker = new DWProfileChecker(context);

        // Setup profile checker parameters
        DWProfileCheckerSettings profileCheckerSettings = new DWProfileCheckerSettings()
        {{
            mProfileName = settings.mProfileName;
            mTimeOutMS = settings.mTimeOutMS;
            mEnableTimeOutMechanism = settings.mEnableTimeOutMechanism;
        }};

        // Execute the checker with the given parameters
        checker.execute(profileCheckerSettings, new DWProfileChecker.onProfileExistResult() {
            @Override
            public void result(String profileName, boolean exists) {
                // empty error... means... I let you guess....
                // exists == true means that the profile already... exists..
                if(exists)
                {
                    debugMessage(callback, settings.mProfileName,"Profile " + profileName + " found in DW profiles list.\n Deleting profile before creating a new one.");
                    // the profile exists, so we are going to delete it.
                    // here is a sample on how you could directly inline the code (without using methods) and have the
                    // whole workflow at the same place
                    DWProfileDelete dwProfileDelete = new DWProfileDelete(context);
                    DWProfileDeleteSettings dwProfileDeleteSettings = new DWProfileDeleteSettings()
                    {{
                        mProfileName = settings.mProfileName;
                        mTimeOutMS = settings.mTimeOutMS;
                    }};
                    dwProfileDelete.execute(dwProfileDeleteSettings, new DWProfileCommandBase.onProfileCommandResult() {
                        @Override
                        public void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier) {
                            if(result.equalsIgnoreCase(DataWedgeConstants.COMMAND_RESULT_SUCCESS))
                            {
                                debugMessage(callback, settings.mProfileName, "Profile: " + profileName + " deleted with success.\nCreating new profile now.");
                                createProfileAsync(context, settings, callback);
                            }
                            else
                            {
                                debugMessage(callback, settings.mProfileName, "Error deleting profile: " + profileName + "\n" + resultInfo);
                                if(callback != null)
                                {
                                    callback.onError(settings.mProfileName,"Errord deleting profile :" + profileName, resultInfo);
                                }
                            }
                        }

                        @Override
                        public void timeout(String profileName) {
                            debugMessage(callback, settings.mProfileName, "Timeout while trying to delete profile: " + profileName);
                            if(callback != null)
                                callback.onError(settings.mProfileName,"Timeout while trying to delete profile: " + profileName, "");
                        }
                    });
                }
                else
                {
                    debugMessage(callback, settings.mProfileName, "Profile " + profileName + " not found in DW profiles list. Creating profile.");
                    createProfileAsync(context, settings, callback);
                }
            }

            @Override
            public void timeOut(String profileName) {
                debugMessage(callback, settings.mProfileName, "Timeout while trying to check if profile " + profileName + " exists.");
                if(callback != null)
                    callback.onError(settings.mProfileName,"Timeout while trying to check if profile " + profileName + " exists.", "");
            }
        });
    }

    private static void createProfileAsync(Context context, DWProfileSetConfigSettings settings, CreateProfileHelperCallback callback)
    {
        DWProfileCreate profileCreate = new DWProfileCreate(context);

        DWProfileCreateSettings profileCreateSettings = new DWProfileCreateSettings()
        {{
            mProfileName = settings.mProfileName;
            mTimeOutMS = settings.mTimeOutMS;
            mEnableTimeOutMechanism = settings.mEnableTimeOutMechanism;
        }};

        profileCreate.execute(profileCreateSettings, new DWProfileCommandBase.onProfileCommandResult() {
            @Override
            public void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier) {
                if(result.equalsIgnoreCase(DataWedgeConstants.COMMAND_RESULT_SUCCESS))
                {
                    debugMessage(callback, settings.mProfileName, "Profile: " + profileName + " created with success.\nSetting config now.");
                    setProfileConfigAsync(context, settings, callback);
                }
                else
                {
                    debugMessage(callback, settings.mProfileName, "Error creating profile: " + profileName + "\n" + resultInfo);
                    if(callback != null)
                    {
                        callback.onError(settings.mProfileName,"Errord creating profile :" + profileName, resultInfo);
                    }
                }
            }

            @Override
            public void timeout(String profileName) {
                debugMessage(callback, settings.mProfileName, "Timeout while trying to create profile: " + profileName);
                if(callback != null)
                    callback.onError(settings.mProfileName,"Timeout while trying to create profile: " + profileName , "");
            }
        });
    }


    private static void setProfileConfigAsync(Context context, DWProfileSetConfigSettings settings, CreateProfileHelperCallback callback)
    {
        DWProfileSetConfig profileSetConfig = new DWProfileSetConfig(context);

        profileSetConfig.execute(settings, new DWProfileCommandBase.onProfileCommandResult() {
            @Override
            public void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier) {
                if(result.equalsIgnoreCase(DataWedgeConstants.COMMAND_RESULT_SUCCESS))
                {
                    debugMessage(callback, settings.mProfileName, "Set config on profile: " + profileName + " succeeded.");
                    if(callback != null)
                        callback.onSuccess(profileName);
                }
                else
                {
                    debugMessage(callback, settings.mProfileName, "Error setting params on profile: " + profileName + "\n" + resultInfo);
                }
            }

            @Override
            public void timeout(String profileName) {
                debugMessage(callback, settings.mProfileName, "Timeout while trying to set params on profile: " + profileName);
            }
        });
    }

}
