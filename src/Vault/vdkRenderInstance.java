package Vault;

import com.sun.jna.ptr.IntByReference;

public class vdkRenderInstance {
    public IntByReference pointCloud;
    public double[] worldMatrix;

    public int modelFlags;

    public IntByReference filter;
    public IntByReference voxelShader;
    public IntByReference voxelUserData;

}
