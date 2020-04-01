package Vault;

public class VaultAPI {

    public enum vdkError {

        vE_Success(0), //!< Indicates the operation was successful

        vE_Failure(1), //!< A catch-all value that is rarely used, internally the below values are favored
        vE_InvalidParameter(2), //!< One or more parameters is not of the expected format
        vE_InvalidConfiguration(3), //!< Something in the request is not correctly configured or has conflicting settings
        vE_InvalidLicense(4), //!< The required license isn't available or has expired
        vE_SessionExpired(5), //!< The Vault Server has terminated your session

        vE_NotAllowed(6), //!< The requested operation is not allowed (usually this is because the operation isn't allowed in the current state)
        vE_NotSupported(7), //!< This functionality has not yet been implemented (usually some combination of inputs isn't compatible yet)
        vE_NotFound(8), //!< The requested item wasn't found or isn't currently available
        vE_NotInitialized(9), //!< The request can't be processed because an object hasn't been configured yet

        vE_ConnectionFailure(10), //!< There was a connection failure
        vE_MemoryAllocationFailure(11), //!< VDK wasn't able to allocate enough memory for the requested feature
        vE_ServerFailure(12), //!< The server reported an error trying to fufil the request
        vE_AuthFailure(13), //!< The provided credentials were declined (usually username or password issue)
        vE_SecurityFailure(14), //!< There was an issue somewhere in the security system- usually creating or verifying of digital signatures or cryptographic key pairs
        vE_OutOfSync(15), //!< There is an inconsistency between the internal VDK state and something external. This is usually because of a time difference between the local machine and a remote server

        vE_ProxyError(16), //!< There was some issue with the provided proxy information (either a proxy is in the way or the provided proxy info wasn't correct)
        vE_ProxyAuthRequired(17), //!< A proxy has requested authentication

        vE_OpenFailure(18), //!< A requested resource was unable to be opened
        vE_ReadFailure(19), //!< A requested resourse was unable to be read
        vE_WriteFailure(20), //!< A requested resource was unable to be written
        vE_ParseError(21), //!< A requested resource or input was unable to be parsed
        vE_ImageParseError(22), //!< An image was unable to be parsed. This is usually an indication of either a corrupt or unsupported image format

        vE_Pending(23), //!< A requested operation is pending.
        vE_TooManyRequests(24), //!< This functionality is currently being rate limited or has exhausted a shared resource. Trying again later may be successful
        vE_Cancelled(25), //!< The requested operation was cancelled (usually by the user)

        vE_Count(26); //!< Internally used to verify return values

        vdkError(int n){
            this.val = n;
        }
        public final int val;
    }

    public enum RenderViewMatrix {
        Camera, // The local to world-space transform of the camera (View is implicitly set as the inverse)
        View, // The view-space transform for the model (does not need to be set explicitly)
        Projection, // The projection matrix (default is 60 degree LH)
        Viewport, // Viewport scaling matrix (default width and height of viewport)

        Count;


    }

    public enum LicenseType {
        Render,
        Convert,

        Count;


    }

    public enum StandardAttribute {
        GPSTime,
        ARGB,
        Normal,
        Intensity,
        NIR,
        ScanAngle,
        PointSourceID,
        Classification,
        ReturnNumber,
        NumberOfReturns,
        ClassificationFlags,

        ScannerChannel,
        ScanDirection,
        EdgeOfFlightLine,
        ScanAngleRank,
        LASUserData,

        Count;


    }


    public enum StandardAttributeContent {

       
        GPSTime(1 << StandardAttribute.GPSTime.ordinal()),
        ARGB((1 << StandardAttribute.ARGB.ordinal())),
        Normal((1 << StandardAttribute.Normal.ordinal())),
        Intensity((1 << StandardAttribute.Intensity.ordinal())),
        NIR((1 << StandardAttribute.NIR.ordinal())),
        ScanAngle((1 << StandardAttribute.ScanAngle.ordinal())),
        PointSourceID((1 << StandardAttribute.PointSourceID.ordinal())),
        Classification((1 << StandardAttribute.Classification.ordinal())),
        ReturnNumber((1 << StandardAttribute.ReturnNumber.ordinal())),
        NumberOfReturns((1 << StandardAttribute.NumberOfReturns.ordinal())),
        ClassificationFlags((1 << StandardAttribute.ClassificationFlags.ordinal())),
        ScannerChannel((1 << StandardAttribute.ScannerChannel.ordinal())),
        ScanDirection((1 << StandardAttribute.ScanDirection.ordinal())),
        EdgeOfFlightLine((1 << StandardAttribute.EdgeOfFlightLine.ordinal())),
        ScanAngleRank((1 << StandardAttribute.ScanAngleRank.ordinal())),
        LasUserData((1 << StandardAttribute.LASUserData.ordinal()));


        StandardAttributeContent(int i) {
        }


    }


    }
