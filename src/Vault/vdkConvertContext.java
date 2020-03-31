package Vault;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

public class vdkConvertContext extends VaultAPI {

    public final void Create(final vdkContext context)
    {
        ppConvertContext = new Memory(4);

        final vdkError error = vdkConvert_CreateContext(context.ppContext, ppConvertContext);
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkConvertContext.Create failed.");
        }
    }

    public final void Destroy()
    {
        final vdkError error = vdkConvert_DestroyContext(ppConvertContext);
        pConvertContext = tempRef_pConvertContext.argValue;
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkConvertContext.Destroy failed.");
        }
    }


    public final void AddFile(final String fileName)
    {
        final vdkError error = vdkConvert_AddItem(ppConvertContext, fileName);
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkConvertContext.AddItem failed.");
        }
    }
    public final void SetFileName(final String fileName)
    {
        final vdkError error = vdkConvert_SetOutputFilename(ppConvertContext, fileName);
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkConvertContext.SetOutputFilename failed.");
        }
    }

    public final void DoConvert()
    {
        final vdkError error = vdkConvert_DoConvert(ppConvertContext);
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkConvertContext.DoConvert failed.");
        }
    }

    public Pointer ppConvertContext = null;

    private  native vdkError vdkConvert_CreateContext(IntByReference pContext, Pointer ppConvertContext);
    {
        System.loadLibrary("vaultSDK.dll");
    }

    private  native vdkError vdkConvert_DestroyContext(Pointer ppConvertContext);

    private  native vdkError vdkConvert_AddItem(ppConvertContext, String fileName);

    private  native vdkError vdkConvert_SetOutputFilename(IntByReference pConvertContext, String fileName);

    private  native vdkError vdkConvert_DoConvert(IntByReference pConvertContext);
}
