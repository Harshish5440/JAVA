package Vault;

import com.sun.jna.ptr.IntByReference;

public class vdkRenderContext extends  VaultAPI {
    public final void Create(final vdkContext context)
    {
        //final RefObject<IntByReference> tempRef_pRenderer = new RefObject<IntByReference>(pRenderer);
        final vdkError error = vdkRenderContext_Create(context.pContext, pRenderer);
        //pRenderer = tempRef_pRenderer.argValue;
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkRenderContext.Create failed.");
        }

        this.context = context;
    }

    public final void Destroy()
    {
        //final RefObject<IntByReference> tempRef_pRenderer = new RefObject<IntByReference>(pRenderer);
        final vdkError error = vdkRenderContext_Destroy(pRenderer);
        //pRenderer = tempRef_pRenderer.argValue;
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkRenderContext.Destroy failed.");
        }
    }

    public void Render(final vdkRenderView renderView, final vdkRenderInstance[] pModels, final int modelCount)
    {
        vdkError error = vdkRenderContext_Render(pRenderer, renderView.pRenderView, pModels, modelCount, h );
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkRenderContext.Render failed.");
        }
    }

    public IntByReference pRenderer = null;
    private vdkContext context;

    private  native vdkError vdkRenderContext_Create(IntByReference pContext, IntByReference ppRenderer);

    {
        System.loadLibrary("vaultSDK.dll");
    }
    private  native vdkError vdkRenderContext_Destroy(IntByReference ppRenderer);
    private  native vdkError vdkRenderContext_Render(IntByReference pRenderer, IntByReference pRenderView, vdkRenderInstance[] pModels, int modelCount, IntByReference options);
}