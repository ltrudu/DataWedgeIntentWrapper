package com.zebra.datawedgeprofileintents;

public class DWStatusScannerSettings {

    /**
     * Set to false to work in the mainthread
     */
    public boolean mUseSeparateThread = true;


    /*
    Set a the package name that will retrieve the scanner status
    */
    public String mPackageName = "";

    /*
    A store to keep the callback to be fired when we will get the scanner status
    */
    public DWStatusScannerCallback mScannerCallback = null;
}
