package Vault;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class vdkRenderContext extends  VaultAPI {
    Pointer ppRenderContext = new Memory(Native.POINTER_SIZE);
    Pointer pRenderContext = null;
    private vdkContext context;
    public final void Create(final vdkContext context)
    {
        final int error = VaultSDKLib.INSTANCE.vdkRenderContext_Create(context.pContext, ppRenderContext);
        if (error != Vault.VaultAPI.vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkRenderContext.Create failed.");
        }
        pRenderContext = ppRenderContext.getPointer(0);
        this.context = context;
    }

    public final void Destroy()
    {
        final int error = VaultSDKLib.INSTANCE.vdkRenderContext_Destroy(ppRenderContext);
        if (error != Vault.VaultAPI.vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkRenderContext.Destroy failed.");
        }
        pRenderContext = null;
    }

    public void Render(final vdkRenderView renderView, final vdkRenderInstance[] pModels, final int modelCount, Structure options)
    {
        int error = VaultSDKLib.INSTANCE.vdkRenderContext_Render(pRenderContext, renderView.pRenderView, pModels, modelCount, null );
        if (error != Vault.VaultAPI.vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkRenderContext.Render failed.");
        }
    }


}