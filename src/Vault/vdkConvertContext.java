package Vault;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class vdkConvertContext extends VaultAPI {
    public Pointer ppConvertContext = new Memory(Native.POINTER_SIZE);
    public Pointer pConvertContext = null;

    public final void Create(final vdkContext context)
    {
        final int error = VaultSDKLib.INSTANCE.vdkConvert_CreateContext(context.pContext, ppConvertContext);
        pConvertContext = ppConvertContext.getPointer(0);
        if (error != Vault.VaultAPI.vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkConvertContext.Create failed.");
        }
    }

    public final void Destroy()
    {
        final int error = VaultSDKLib.INSTANCE.vdkConvert_DestroyContext(ppConvertContext);
        if (error != vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkConvertContext.Destroy failed.");
        }
    }


    public final void AddFile(final String fileName)
    {
        final int error = VaultSDKLib.INSTANCE.vdkConvert_AddItem(pConvertContext, fileName);
        if (error != vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkConvertContext.AddItem failed.");
        }
    }
    public final void SetFileName(final String fileName)
    {
        final int error = VaultSDKLib.INSTANCE.vdkConvert_SetOutputFilename(pConvertContext, fileName);
        if (error != vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkConvertContext.SetOutputFilename failed.");
        }
    }

    public final void DoConvert()
    {
        final int error = VaultSDKLib.INSTANCE.vdkConvert_DoConvert(pConvertContext);
        if (error != vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkConvertContext.DoConvert failed.");
        }
    }



}
