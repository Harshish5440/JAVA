package Vault;

import com.sun.jna.ptr.IntByReference;

public class vdkAttributeSet {

    public VaultAPI.StandardAttributeContent standardContent;
    //ORIGINAL LINE: public uint count;
    public long count;

    //ORIGINAL LINE: public uint allocated;

    public long allocated;
    public IntByReference pDescriptors;
}
