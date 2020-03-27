package Vault;

import com.sun.jna.ptr.IntByReference;

import java.math.BigInteger;

public class vdkRenderView extends VaultAPI {
    //ORIGINAL LINE: public void Create(vdkContext context, vdkRenderContext renderer, UInt32 width, UInt32 height)
    public final void Create(final vdkContext context, final vdkRenderContext renderer, final int width, final int height)
    {
        final RefObject<IntByReference> tempRef_pRenderView = new RefObject<IntByReference>(pRenderView);
        final vdkError error = vdkRenderView_Create(context.pContext, tempRef_pRenderView, renderer.pRenderer, width,height);
        pRenderView = tempRef_pRenderView.argValue;
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkRenderView.Create failed.");
        }

        this.context = context;
    }

    public final void Destroy()
    {
        final RefObject<IntByReference> tempRef_pRenderView = new RefObject<IntByReference>(pRenderView);
        final vdkError error = vdkRenderView_Destroy(tempRef_pRenderView);
        pRenderView = tempRef_pRenderView.argValue;
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkRenderView.Destroy failed.");
        }
    }

    //ORIGINAL LINE: public void SetTargets(ref UInt32[] colorBuffer, UInt32 clearColor, ref float[] depthBuffer)
    public final void SetTargets(final RefObject<int[]> colorBuffer, final int clearColor, final RefObject<float[]> depthBuffer)
    {
        colorBufferHandle = colorBuffer.argValue;
        depthBufferHandle = depthBuffer.argValue;

        final vdkError error = vdkRenderView_SetTargets(pRenderView, colorBufferHandle , clearColor, depthBufferHandle);
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkRenderView.SetTargets failed.");
        }
    }

    public final void GetMatrix(final RenderViewMatrix matrixType, final double[] cameraMatrix)
    {
        final vdkError error = vdkRenderView_GetMatrix(pRenderView, matrixType, cameraMatrix);
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkRenderView.GetMatrix failed.");
        }
    }

    public final void SetMatrix(final RenderViewMatrix matrixType, final double[] cameraMatrix)
    {
        final vdkError error = vdkRenderView_SetMatrix(pRenderView, matrixType, cameraMatrix);
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkRenderView.SetMatrix failed.");
        }
    }

    public IntByReference pRenderView = null;
    private vdkContext context;


    private  native vdkError vdkRenderView_Create(IntByReference pContext, RefObject<IntByReference> ppRenderView, IntByReference pRenderer, int width, int height);

    {
        System.loadLibrary("vaultSDK.dll");
    }

    private  native vdkError vdkRenderView_Destroy(RefObject<IntByReference> ppRenderView);

    private  native vdkError vdkRenderView_SetTargets(IntByReference pRenderView, IntByReference pColorBuffer, int colorClearValue, IntByReference pDepthBuffer);

    private  native vdkError vdkRenderView_GetMatrix(IntByReference pRenderView, RenderViewMatrix matrixType, double[] cameraMatrix);

    private  native vdkError vdkRenderView_SetMatrix(IntByReference pRenderView, RenderViewMatrix matrixType, double[] cameraMatrix);

}
