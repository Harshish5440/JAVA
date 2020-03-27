package Vault;

import com.sun.jna.ptr.IntByReference;

public class vdkConvertContext extends VaultAPI {

    public final void Create(final vdkContext context)
    {
        final RefObject<IntByReference> tempRef_pConvertContext = new RefObject<IntByReference>(pConvertContext);
        final vdkError error = vdkConvert_CreateContext(context.pContext, tempRef_pConvertContext);
        pConvertContext = tempRef_pConvertContext.argValue;
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkConvertContext.Create failed.");
        }
    }

    public final void Destroy()
    {
        final RefObject<IntByReference> tempRef_pConvertContext = new RefObject<IntByReference>(pConvertContext);
        final vdkError error = vdkConvert_DestroyContext(tempRef_pConvertContext);
        pConvertContext = tempRef_pConvertContext.argValue;
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkConvertContext.Destroy failed.");
        }
    }


    public final void AddFile(final String fileName)
    {
        final vdkError error = vdkConvert_AddItem(pConvertContext, fileName);
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkConvertContext.AddItem failed.");
        }
    }
    public final void SetFileName(final String fileName)
    {
        final vdkError error = vdkConvert_SetOutputFilename(pConvertContext, fileName);
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkConvertContext.SetOutputFilename failed.");
        }
    }

    public final void DoConvert()
    {
        final vdkError error = vdkConvert_DoConvert(pConvertContext);
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkConvertContext.DoConvert failed.");
        }
    }

    public IntByReference pConvertContext = null;

    private  native vdkError vdkConvert_CreateContext(IntByReference pContext, RefObject<IntByReference> ppConvertContext);
    {
        System.loadLibrary("vaultSDK.dll");
    }

    private  native vdkError vdkConvert_DestroyContext(RefObject<IntByReference> ppConvertContext);

    private  native vdkError vdkConvert_AddItem(IntByReference pConvertContext, String fileName);

    private  native vdkError vdkConvert_SetOutputFilename(IntByReference pConvertContext, String fileName);

    private  native vdkError vdkConvert_DoConvert(IntByReference pConvertContext);
}
