package Vault;

import com.sun.jna.Structure;
import com.sun.jna.Pointer;

@Structure.FieldOrder({"pointCloud","worldMatrix","modelFlags","filter","voxelShader","voxelUserData"})
public class vdkRenderInstance extends Structure {
    public Pointer pointCloud;
    public double[] worldMatrix =
            {1,0,0,0,
            0,1,0,0,
            0,0,1,0,
            0,0,0,1};

    public int modelFlags;

    public Pointer filter;
    public Pointer voxelShader;
    public Pointer voxelUserData;
}
