package Vault;

import com.sun.jna.ptr.IntByReference;

public class vdkContext extends VaultAPI {

    protected void finalize() throws Throwable {
        if (pContext != null) {
            Disconnect();
        }

    }

    public final void Connect(final String pURL, final String pApplicationName, final String pUsername, final String pPassword)
    {
        //final RefObject<IntByReference> tempRef_pContext = new RefObject<IntByReference>(pContext);
        final vdkError error = vdkContext_Connect(pContext, pURL, pApplicationName, pUsername, pPassword);
        //pContext = tempRef_pContext.argValue;
        if (error == Vault.VaultAPI.vdkError.vE_ConnectionFailure)
        {
            throw new RuntimeException("Could not connect to server.");
        }
        else if (error == Vault.VaultAPI.vdkError.vE_AuthFailure)
        {
            throw new RuntimeException("Username or Password incorrect.");
        }
        else if (error == Vault.VaultAPI.vdkError.vE_OutOfSync)
        {
            throw new RuntimeException("Your clock doesn't match the remote server clock.");
        }
        else if (error == Vault.VaultAPI.vdkError.vE_SecurityFailure)
        {
            throw new RuntimeException("Could not open a secure channel to the server.");
        }
        else if (error == Vault.VaultAPI.vdkError.vE_ServerFailure)
        {
            throw new RuntimeException("Unable to negotiate with server, please confirm the server address");
        }
        else if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("Unknown error occurred, please try again later.");
        }
    }

    public final void Disconnect()
    {
        //final RefObject<IntByReference> tempRef_pContext = new RefObject<IntByReference>(pContext);
        final vdkError error = vdkContext_Disconnect(pContext);
        //pContext = tempRef_pContext.argValue;
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkContext.Disconnect failed.");
        }
    }

    public final void GetLicenseInfo(LicenseType type, vdkLicenseInfo info)
    {
        final vdkError error = vdkContext_GetLicenseInfo(pContext, type, info);
        if (error != Vault.VaultAPI.vdkError.vE_Success && error != Vault.VaultAPI.vdkError.vE_InvalidLicense)
        {
            throw new RuntimeException("vdkContext.GetLicenseInfo failed.");
        }
    }

    public final void RequestLicense(final LicenseType type)
    {
        final vdkError error = vdkContext_RequestLicense(pContext, type);
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkContext.Disconnect failed.");
        }
    }

    public IntByReference pContext = null;

    private  native vdkError vdkContext_Connect(IntByReference ppContext, String pURL, String pApplicationName, String pUsername, String pPassword);

    {
        System.loadLibrary("vaultSDK.dll");
    }
    private  native vdkError vdkContext_Disconnect(IntByReference ppContext);
    private  native vdkError vdkContext_RequestLicense(IntByReference pContext, LicenseType licenseType);
    private  native vdkError vdkContext_GetLicenseInfo(IntByReference pContext, LicenseType licenseType,vdkLicenseInfo info);
}
