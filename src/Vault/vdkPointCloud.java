package Vault;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class vdkPointCloud extends VaultAPI {
    Memory ppModel = null;

    public final void Load(final vdkContext context, final String modelLocation,  vdkPointCloudHeader header)
    {
        ppModel = new Memory(Native.POINTER_SIZE);
        final int error = VaultSDKLib.INSTANCE.vdkPointCloud_Load(context.pContext, (Pointer) ppModel, modelLocation, null);
        System.out.print(error);
        System.out.print("\n");

        System.out.println(Vault.VaultAPI.vdkError.vE_Success.val);

        if (error != Vault.VaultAPI.vdkError.vE_Success.val)
        {
            throw new RuntimeException("vdkPointCloud.Load failed.");
        }

        this.context = context;
    }

    public final void Unload()
    {           ppModel = null;


        final int error = VaultSDKLib.INSTANCE.vdkPointCloud_Unload((Pointer) ppModel);

        System.out.print(error);
        System.out.print("\n");

        System.out.println(Vault.VaultAPI.vdkError.vE_Success.val);

        if (error != Vault.VaultAPI.vdkError.vE_Success.val)

        {
            throw new RuntimeException("vdkPointCloud.Unload failed.");
        }
        ppModel.disposeAll();
        ppModel = null;
    }

    /*
    public String GetMetadata(final RefObject<String> ppJSONMetadata)

    {
        Memory ppJSONMetaData = new Memory(4);
        final vdkError error = vdkPointCloud_GetMetadata(pModel, ppJSONMetadata);
        if (error != Vault.VaultAPI.vdkError.vE_Success)
        {
            throw new RuntimeException("vdkPointCloud.GetMetadata failed.");
        }
    }

     */

    //public Pointer pModel = null;
    private vdkContext context;

}
