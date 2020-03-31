package Vault;

import com.sun.jna.Structure;

import java.math.BigInteger;

public class vdkLicenseInfo extends Structure {
    public long queuePosition;
    //ORIGINAL LINE: public UInt64 expiresTimestamp;
    public BigInteger expiresTimestamp;

    public vdkLicenseInfo clone()
    {
        final vdkLicenseInfo varCopy = new vdkLicenseInfo();

        varCopy.queuePosition = this.queuePosition;
        varCopy.expiresTimestamp = this.expiresTimestamp;

        return varCopy;
    }

}
