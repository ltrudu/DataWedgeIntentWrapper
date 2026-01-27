package com.zebra.datawedgeprofileintents;

/*
    An interface callback to be informed of the result
    when checking if a profile exists
*/
public interface IProfileCommandResult
{
    void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier);
    void timeout(String profileName);
}
