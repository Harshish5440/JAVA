package Vault;

import com.sun.jna.ptr.IntByReference;

public class vdkPointCloud extends VaultAPI {
    public final void Load(final vdkContext context, final String modelLocation, vdkPointCloudHeader header)
    {
        //final RefObject<IntByReference> tempRef_pModel = new RefObject<IntByReference>(pModel);
        final vdkError error = vdkPointCloud_Load(context.pContext, pModel, modelLocation, header);
        //pModel = tempRef_pModel.argValue;
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkPointCloud.Load failed.");
        }

        this.context = context;
    }

    public final void Unload()
    {
        //final RefObject<IntByReference> tempRef_pModel = new RefObject<IntByReference>(pModel);
        final vdkError error = vdkPointCloud_Unload(pModel);
        //pModel = tempRef_pModel.argValue;
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkPointCloud.Unload failed.");
        }
    }

    public final void GetMetadata(String ppJSONMetadata)

    {
        final vdkError error = vdkPointCloud_GetMetadata(pModel, ppJSONMetadata);
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkPointCloud.GetMetadata failed.");
        }
    }

    public IntByReference pModel = null;
    private vdkContext context;

    private  native vdkError vdkPointCloud_Load(IntByReference pContext, IntByReference ppModel, String modelLocation, vdkPointCloudHeader header);

    {
        System.loadLibrary("vaultSDK.dll");
    }

    private  native vdkError vdkPointCloud_Unload(IntByReference ppModel);

    private  native vdkError vdkPointCloud_GetMetadata(IntByReference pModel, String ppJSONMetadata);
}
