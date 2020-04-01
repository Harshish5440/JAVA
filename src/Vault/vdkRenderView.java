package Vault;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class vdkRenderView extends VaultAPI {
    //ORIGINAL LINE: public void Create(vdkContext context, vdkRenderContext renderer, UInt32 width, UInt32 height)

    public Pointer pRenderView = null;
    public Pointer ppRenderView = new Memory(Native.POINTER_SIZE);
    private vdkContext context;
    public final void Create(final vdkContext context, final vdkRenderContext renderer, final int width, final int height)
    {
        final int error = VaultSDKLib.INSTANCE.vdkRenderView_Create(context.pContext, ppRenderView, renderer.pRenderContext, width,height);
        pRenderView = ppRenderView.getPointer(0);
        if (error != Vault.VaultAPI.vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkRenderView.Create failed.");
        }

        this.context = context;
    }

    public final void Destroy()
    {
        final int error = VaultSDKLib.INSTANCE.vdkRenderView_Destroy(ppRenderView);
        if (error != Vault.VaultAPI.vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkRenderView.Destroy failed.");
        }
        pRenderView = null;
    }

    //ORIGINAL LINE: public void SetTargets(ref UInt32[] colorBuffer, UInt32 clearColor, ref float[] depthBuffer)
    public final void SetTargets(Memory colourBuffer, final int clearColour, Memory depthBuffer)
    {

        final int error = VaultSDKLib.INSTANCE.vdkRenderView_SetTargets(pRenderView, colourBuffer , clearColour, depthBuffer);
        if (error != Vault.VaultAPI.vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkRenderView.SetTargets failed.");
        }
    }

    public final void GetMatrix(final RenderViewMatrix matrixType, final double[] cameraMatrix)
    {
        final int error = VaultSDKLib.INSTANCE.vdkRenderView_GetMatrix(pRenderView, matrixType.ordinal(), cameraMatrix);
        if (error != Vault.VaultAPI.vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkRenderView.GetMatrix failed.");
        }
    }

    public final void SetMatrix(final RenderViewMatrix matrixType, final double[] cameraMatrix)
    {
        final int error = VaultSDKLib.INSTANCE.vdkRenderView_SetMatrix(pRenderView, matrixType.ordinal(), cameraMatrix);
        if (error != Vault.VaultAPI.vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkRenderView.SetMatrix failed.");
        }
    }
}
