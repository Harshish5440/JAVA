package Vault;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;

public class vdkContext extends VaultAPI {

    protected void finalize() throws Throwable {
        if (ppContext != null) {
            Disconnect();
        }

    }

    public final void Connect(final String pURL, final String pApplicationName, final String pUsername, final String pPassword)
    {
        ppContext = new Memory(4);
        final vdkError error = vdkContext_Connect((Pointer) ppContext, pURL, pApplicationName, pUsername, pPassword);
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
        ppContext = new Memory(4);
        final vdkError error = vdkContext_Disconnect((Pointer) ppContext);
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkContext.Disconnect failed.");
        }
    }

    public final void GetLicenseInfo(final VaultAPI.LicenseType type, final vdkLicenseInfo info)
    {
        ppContext = new Memory(4);
        final vdkError error = vdkContext_GetLicenseInfo((Pointer)ppContext, type, info);
        if (error != Vault.VaultAPI.vdkError.vE_Success && error != Vault.VaultAPI.vdkError.vE_InvalidLicense)
        {
            throw new RuntimeException("vdkContext.GetLicenseInfo failed.");
        }
    }

    public final void RequestLicense(final LicenseType type)
    {
        ppContext = new Memory(4);
        final vdkError error = vdkContext_RequestLicense((Pointer)ppContext, type);
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkContext.Disconnect failed.");
        }
    }

    public Pointer ppContext = null;

    private  native vdkError vdkContext_Connect(Pointer ppContext, String pURL, String pApplicationName, String pUsername, String pPassword);

    {
        System.loadLibrary("vaultSDK.dll");
    }
    private  native vdkError vdkContext_Disconnect(Pointer ppContext);
    private  native vdkError vdkContext_RequestLicense(Pointer pContext, LicenseType licenseType);
    private  native vdkError vdkContext_GetLicenseInfo(Pointer pContext, LicenseType licenseType, vdkLicenseInfo info);
}
