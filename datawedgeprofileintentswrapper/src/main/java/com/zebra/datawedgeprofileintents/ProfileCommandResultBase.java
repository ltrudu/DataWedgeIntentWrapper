package com.zebra.datawedgeprofileintents;

public class ProfileCommandResultBase implements IProfileCommandResult {

    public interface IPostponedOnResult
    {
        void onResult();
    }

    public IPostponedOnResult postponedOnResult = null;

    public IProfileCommandResult IProfileCommandResult = null;
    public String profileName = null, action = null, command = null, result = null, resultInfo = null, commandidentifier = null;
    public boolean initialized = false;

    public ProfileCommandResultBase(IProfileCommandResult IProfileCommandResult)
    {
        this.IProfileCommandResult = IProfileCommandResult;
    }

    @Override
    public void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier) {
        this.profileName        = profileName ;
        this.action             = action;
        this.command            = command;
        this.result             = result;
        this.resultInfo         = resultInfo ;
        this.commandidentifier  = commandidentifier;
        this.initialized        = true;
        if(postponedOnResult != null)
        {
            postponedOnResult.onResult();
        }
    }

    @Override
    public void timeout(String profileName) {
        this.result = "TIMEOUT";
        this.profileName = profileName;
    }

    public void executeResults()
    {
        if(IProfileCommandResult != null)
            IProfileCommandResult.result(this.profileName, this.action, this.command, this.result, this.resultInfo, this.commandidentifier);
    }

    public void executeTimeOut()
    {
       if(IProfileCommandResult != null)
            IProfileCommandResult.timeout(this.profileName);
    }
}
