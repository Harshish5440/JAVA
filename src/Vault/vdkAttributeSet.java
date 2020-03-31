package Vault;

import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

@Structure.FieldOrder({"standardContent","count","allocated","pDescriptors"})
class vdkAttributeSet extends Structure {

    public VaultAPI.StandardAttributeContent standardContent;
    //ORIGINAL LINE: public uint count;
    public long count;

    //ORIGINAL LINE: public uint allocated;

    public long allocated;
    public IntByReference pDescriptors;
}
