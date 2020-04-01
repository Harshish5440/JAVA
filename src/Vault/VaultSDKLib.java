package Vault;

import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface VaultSDKLib extends Library {
    VaultSDKLib INSTANCE = (VaultSDKLib) Native.load("D:/git/Euclideon_vdk0.5.1/lib/win_x64/vaultSDK.dll",VaultSDKLib.class);

    public int vdkContext_Connect(Pointer ppContext, String pURL, String pApplicationName, String pUsername, String pPassword);
    public int vdkContext_Disconnect(Pointer ppContext);
    public int vdkContext_RequestLicense(Pointer pContext, int licenseType);
    public int vdkContext_GetLicenseInfo(Pointer pContext, VaultAPI.LicenseType licenseType, vdkLicenseInfo info);


    public int vdkPointCloud_Load(Pointer pContext, Pointer ppModel, String modelLocation, vdkPointCloudHeader pHeader);
    public int vdkPointCloud_Unload(Pointer ppModel);
    public int vdkPointCloud_GetMetadata(Pointer pModel, RefObject<String> ppJSONMetadata);


    public int vdkRenderContext_Create(Pointer pContext, Pointer ppRenderer);
    public int vdkRenderContext_Destroy(Pointer ppRenderer);
    public int vdkRenderContext_Render(Pointer pRenderer, Pointer pRenderView, vdkRenderInstance[] pModels, int modelCount, Pointer options);


    public int vdkRenderView_Create(Pointer pContext, Pointer ppRenderView, Pointer pRenderer, int width, int height);
    public int vdkRenderView_Destroy(Pointer ppRenderView);
    public int vdkRenderView_SetTargets(Pointer pRenderView, Memory pColorBuffer, int colorClearValue, Memory pDepthBuffer);
    public int vdkRenderView_GetMatrix(Pointer pRenderView, int matrixType, double[] cameraMatrix);
    public int vdkRenderView_SetMatrix(Pointer pRenderView, int matrixType, double[] cameraMatrix);


    public int vdkConvert_DestroyContext(Pointer ppConvertContext);
    public int vdkConvert_AddItem(Pointer pConvertContext, String fileName);
    public int vdkConvert_SetOutputFilename(Pointer pConvertContext, String fileName);
    public int vdkConvert_DoConvert(Pointer pConvertContext);
    public int vdkConvert_CreateContext(Pointer pContext, Pointer ppConvertContext);
}
