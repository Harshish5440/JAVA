package Vault;

import java.math.BigInteger;

public class vdkLicenseInfo extends VaultAPI {
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
