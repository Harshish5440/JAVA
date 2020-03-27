package Vault;

public class VaultAPI {

    public enum vdkError {
        vE_Success, //!< Indicates the operation was successful

        vE_Failure, //!< A catch-all value that is rarely used, internally the below values are favored
        vE_InvalidParameter, //!< One or more parameters is not of the expected format
        vE_InvalidConfiguration, //!< Something in the request is not correctly configured or has conflicting settings
        vE_InvalidLicense, //!< The required license isn't available or has expired
        vE_SessionExpired, //!< The Vault Server has terminated your session

        vE_NotAllowed, //!< The requested operation is not allowed (usually this is because the operation isn't allowed in the current state)
        vE_NotSupported, //!< This functionality has not yet been implemented (usually some combination of inputs isn't compatible yet)
        vE_NotFound, //!< The requested item wasn't found or isn't currently available
        vE_NotInitialized, //!< The request can't be processed because an object hasn't been configured yet

        vE_ConnectionFailure, //!< There was a connection failure
        vE_MemoryAllocationFailure, //!< VDK wasn't able to allocate enough memory for the requested feature
        vE_ServerFailure, //!< The server reported an error trying to fufil the request
        vE_AuthFailure, //!< The provided credentials were declined (usually username or password issue)
        vE_SecurityFailure, //!< There was an issue somewhere in the security system- usually creating or verifying of digital signatures or cryptographic key pairs
        vE_OutOfSync, //!< There is an inconsistency between the internal VDK state and something external. This is usually because of a time difference between the local machine and a remote server

        vE_ProxyError, //!< There was some issue with the provided proxy information (either a proxy is in the way or the provided proxy info wasn't correct)
        vE_ProxyAuthRequired, //!< A proxy has requested authentication

        vE_OpenFailure, //!< A requested resource was unable to be opened
        vE_ReadFailure, //!< A requested resourse was unable to be read
        vE_WriteFailure, //!< A requested resource was unable to be written
        vE_ParseError, //!< A requested resource or input was unable to be parsed
        vE_ImageParseError, //!< An image was unable to be parsed. This is usually an indication of either a corrupt or unsupported image format

        vE_Pending, //!< A requested operation is pending.
        vE_TooManyRequests, //!< This functionality is currently being rate limited or has exhausted a shared resource. Trying again later may be successful
        vE_Cancelled, //!< The requested operation was cancelled (usually by the user)

        vE_Count; //!< Internally used to verify return values

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
