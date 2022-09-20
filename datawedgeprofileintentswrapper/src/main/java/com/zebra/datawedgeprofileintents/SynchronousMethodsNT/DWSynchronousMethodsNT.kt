package com.zebra.datawedgeprofileintents.SynchronousMethodsNT

import android.content.Context
import android.util.Pair
import com.zebra.datawedgeprofileintents.*
import com.zebra.datawedgeprofileintents.DWSynchronousMethods.EResults

class DWSynchronousMethodsNT(private val mContext: Context) {

    fun runInNewThread(methodName: String, param: Any, paramClass: Class<*>): Pair<EResults, String> {
        val synchronousNTRunnable = SynchronousMethodExecutor<String>(mContext, methodName, param, paramClass, "")
        synchronousNTRunnable.runInThreadAndWaitForEnd()
        return synchronousNTRunnable.results
    }

    fun setupDWProfile(settings: DWProfileSetConfigSettings): Pair<EResults, String> {
        return runInNewThread("setupDWProfile", settings, DWProfileSetConfigSettings::class.java)
    }

    fun enablePlugin(): Pair<EResults, String> {
        return runInNewThread("enablePlugin", mContext.packageName, String::class.java)
    }

    fun enablePlugin(profileName: String): Pair<EResults, String> {
        return runInNewThread("enablePlugin", profileName, String::class.java)
    }

    fun disablePlugin(): Pair<EResults, String> {
        return runInNewThread("disablePlugin", mContext.packageName, String::class.java)
    }

    fun disablePlugin(profileName: String): Pair<EResults, String> {
        return runInNewThread("disablePlugin", profileName, String::class.java)
    }

    fun startScan(): Pair<EResults, String> {
        return runInNewThread("startScan", mContext.packageName, String::class.java)
    }

    fun startScan(profileName: String): Pair<EResults, String> {
        return runInNewThread("startScan", profileName, String::class.java)
    }

    fun stopScan(): Pair<EResults, String> {
        return runInNewThread("stopScan", mContext.packageName, String::class.java)
    }

    fun stopScan(profileName: String): Pair<EResults, String> {
        return runInNewThread("stopScan", profileName, String::class.java)
    }

    fun profileExists(): Pair<EResults, String> {
        return runInNewThread("profileExists", mContext.packageName, String::class.java)
    }

    fun profileExists(profileName: String): Pair<EResults, String> {
        return runInNewThread("profileExists", profileName, String::class.java)
    }

    fun deleteProfile(): Pair<EResults, String> {
        return runInNewThread("deleteProfile", mContext.packageName, String::class.java)
    }

    fun deleteProfile(profileName: String): Pair<EResults, String> {
        return runInNewThread("deleteProfile", profileName, String::class.java)
    }

    fun switchBarcodeParams(settings: DWProfileSwitchBarcodeParamsSettings): Pair<EResults, String> {
        return runInNewThread("switchBarcodeParams", settings, DWProfileSwitchBarcodeParamsSettings::class.java)
    }

    fun enumerateScanners(settings: DWEnumerateScannersSettings): Pair<EResults, List<DWEnumerateScanners.Scanner>> {
        val synchronousNTRunnable = SynchronousMethodExecutor<List<DWEnumerateScanners.Scanner>>(mContext, "enumerateScanners", settings, DWEnumerateScannersSettings::class.java, emptyList())
        synchronousNTRunnable.runInThreadAndWaitForEnd()
        return synchronousNTRunnable.results
    }
}