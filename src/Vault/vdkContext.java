package Vault;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class vdkContext extends VaultAPI implements AutoCloseable {
    Memory ppContext;
    public Pointer pContext = null;
    @Override
    public void close()
    {
        if (pContext != null) {

            Disconnect();
            ppContext.disposeAll();
            ppContext = null;
            pContext = null;
        }
    }

    public final void Connect(final String pURL, final String pApplicationName, final String pUsername, final String pPassword)
    {
        ppContext = new Memory(Native.POINTER_SIZE);
        final int error = VaultSDKLib.INSTANCE.vdkContext_Connect( (Pointer) ppContext, pURL, pApplicationName, pUsername, pPassword);
        switch(error) {
            case 0:
                pContext = ppContext.getPointer(0);
                break;
            default:
                throw new RuntimeException("Error: "+ error);
            /*
            case vdkError.vE_ConnectionFailure.val:
                throw new RuntimeException("Could not connect to server.");
            case vdkError.vE_AuthFailure.val:
                throw new RuntimeException("Username or Password incorrect.");
            case vdkError.vE_OutOfSync:
                throw new RuntimeException("Your clock doesn't match the remote server clock.");
            case vdkError.vE_SecurityFailure:
                throw new RuntimeException("Could not open a secure channel to the server.");
            case vdkError.vE_ServerFailure:
                throw new RuntimeException("Unable to negotiate with server, please confirm the server address");
            case vdkError.vE_Success:
                break;
            default:
                throw new RuntimeException("Unknown error occurred, please try again later: " + error);
             */
        }
    }

    public final void Disconnect()
    {
        final int error = VaultSDKLib.INSTANCE.vdkContext_Disconnect( (Pointer) ppContext);
        Logger.getAnonymousLogger().log(Level.INFO,"Disconnecting vdkContext");
        if (error != vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkContext.Disconnect failed.");
        }
        ppContext = null;
    }

    public final void GetLicenseInfo(final LicenseType type, vdkLicenseInfo info)
    {
        final int error = VaultSDKLib.INSTANCE.vdkContext_GetLicenseInfo(pContext, type, info);
        if (error != Vault.VaultAPI.vdkError.vE_Success.val && error != Vault.VaultAPI.vdkError.vE_InvalidLicense.val)
        {
            throw new RuntimeException("vdkContext.GetLicenseInfo failed.");
        }
    }

    public final void RequestLicense(final LicenseType type)
    {
        final int error = VaultSDKLib.INSTANCE.vdkContext_RequestLicense(pContext, type.ordinal());
        if (error != Vault.VaultAPI.vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkContext.Disconnect failed.");
        }
    }
}
