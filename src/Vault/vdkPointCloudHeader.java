package Vault;

import com.sun.jna.Structure;

@Structure.FieldOrder({"scaledRange","unitMeterScale","totalLODLayers","convertedResolution","storedMatrix","attributes","baseOffset","pivot","boundingBoxCenter","boundingBoxExtents"})
public class vdkPointCloudHeader extends Structure {
    public double scaledRange; //!< The point cloud's range multiplied by the voxel size
    public double unitMeterScale; //!< The scale to apply to convert to/from metres (after scaledRange is applied to the unitCube)
    public int totalLODLayers; //!< The total number of LOD layers in this octree
    public double convertedResolution; //!< The resolution this model was converted at
    public double[] storedMatrix = new double[16]; //!< This matrix is the 'default' internal matrix to go from a unit cube to the full size

    public int attributes; //!< The attributes contained in this pointcloud

    public double[] baseOffset; //!< The offset to the root of the pointcloud in unit cube space
    public double[] pivot; //!< The pivot point of the model, in unit cube space
    public double[] boundingBoxCenter; //!< The center of the bounding volume, in unit cube space
    public double[] boundingBoxExtents; //!< The extents of the bounding volume, in unit cube space  }
}
