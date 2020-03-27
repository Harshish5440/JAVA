package Vault;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {

        final int width = 1280;
        final int height = 720;

        Vault.vdkContext context = new Vault.vdkContext();
        Vault.vdkRenderContext renderer = new Vault.vdkRenderContext();
        Vault.vdkRenderView renderView = new Vault.vdkRenderView();
        Vault.vdkPointCloud udModel = new Vault.vdkPointCloud();
        Vault.vdkPointCloudHeader header = new Vault.vdkPointCloudHeader();


        int[] colorBuffer = new int[width * height];
        float[] depthBuffer = new float[width * height];

        String server = "https://earth.vault.euclideon.com";
        String username = "Username";
        String password = "Password";
        String modelName = "DirCube.uds";
        boolean pause = false;

        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("-u") && i + 1 < args.length) {
                username = args[++i];
            } else if (args[i].equals("-p") && i + 1 < args.length) {
                password = args[++i];
            } else if (args[i].equals("-s") && i + 1 < args.length) {
                server = args[++i];
            } else if (args[i].equals("-m") && i + 1 < args.length) {
                modelName = args[++i];
            } else if (args[i].equals("-pause")) {
                pause = true;
            }
        }

        try {
            context.Connect(server, "java Sample", username, password);
            context.RequestLicense(Vault.VaultAPI.LicenseType.Render);

            Vault.vdkLicenseInfo info = new Vault.vdkLicenseInfo();
            Vault.RefObject<Vault.vdkLicenseInfo> tempRef_info = new Vault.RefObject<Vault.vdkLicenseInfo>(info);
            context.GetLicenseInfo(Vault.VaultAPI.LicenseType.Render, tempRef_info);
            info = tempRef_info.argValue;

            if (info.queuePosition == -1) {
                //long unixTimestamp = (long) (LocalDateTime.UtcNow.Subtract(LocalDateTime(1970, 1, 1))).TotalSeconds;
              //  System.out.printf("License fetched and available for another %1$s seconds." + "\r\n", info.expiresTimestamp - unixTimestamp);
            }

            renderer.Create(context);
            renderView.Create(context, renderer, width, height);
            RefObject<Vault.vdkPointCloudHeader> tempRef_header = new RefObject<Vault.vdkPointCloudHeader>(header);
            udModel.Load(context, modelName, tempRef_header);
            header = tempRef_header.argValue;
            RefObject<int[]> tempRef_colorBuffer = new RefObject<int[]>(colorBuffer);
            RefObject<float[]> tempRef_depthBuffer = new RefObject<float[]>(depthBuffer);
            renderView.SetTargets(tempRef_colorBuffer, 0, tempRef_depthBuffer);
            depthBuffer = tempRef_depthBuffer.argValue;
            colorBuffer = tempRef_colorBuffer.argValue;

            double[] cameraMatrix = {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, -5, 0, 1};

            renderView.SetMatrix(Vault.VaultAPI.RenderViewMatrix.Camera, cameraMatrix);

            Vault.vdkRenderInstance item =  new Vault.vdkRenderInstance();
            item.pointCloud = udModel.pModel;
            item.worldMatrix = header.storedMatrix;

            Vault.vdkRenderInstance itemFlipped = new Vault.vdkRenderInstance();
            itemFlipped.pointCloud = udModel.pModel;
            itemFlipped.worldMatrix = header.storedMatrix;
            itemFlipped.worldMatrix[0] = -itemFlipped.worldMatrix[0];
            itemFlipped.worldMatrix[5] = -itemFlipped.worldMatrix[5];
            itemFlipped.worldMatrix[10] = -itemFlipped.worldMatrix[10];

            Vault.vdkRenderInstance[] modelArray = new Vault.vdkRenderInstance[]{item, itemFlipped};

            for (int i = 0; i < 10; i++) {
                renderer.Render(renderView, modelArray, modelArray.length);
            }

            String imagePath = "tmp.png";
            SaveColorImage(imagePath, width, height, colorBuffer);

            if (pause) {
                System.out.println(imagePath + " written to the build directory.");
                System.out.println("Press any key to exit.");
                //Console.ReadKey();
            }

            //! Uncomment the following line to test the convert API
            //Convert(modelName, modelName + ".uds", context);
        } finally {
            udModel.Unload();
            renderView.Destroy();
            renderer.Destroy();
            context.Disconnect();
        }
    }

    static void Convert(String inputPath, String outputPath, Vault.vdkContext context) {
        Vault.vdkLicenseInfo info = new Vault.vdkLicenseInfo();
        RefObject<Vault.vdkLicenseInfo> tempRef_info = new RefObject<Vault.vdkLicenseInfo>(info);
        context.GetLicenseInfo(Vault.VaultAPI.LicenseType.Convert, tempRef_info);
        info = tempRef_info.argValue;

        if (info.queuePosition == -1) {
            context.RequestLicense(Vault.VaultAPI.LicenseType.Convert);
        }

        Vault.vdkConvertContext convertContext = new Vault.vdkConvertContext();
        convertContext.Create(context);

        convertContext.AddFile(inputPath);
        convertContext.SetFileName(outputPath);

        convertContext.DoConvert();

        convertContext.Destroy();
    }

    static void SaveColorImage(String path, int width, int height, int[] colorBufferArr) {
        int type = BufferedImage.TYPE_INT_ARGB;
        BufferedImage bmp = new BufferedImage(width, height, type);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                byte a = (byte) (colorBufferArr[x + y * width] >> 24);
                byte r = (byte) (colorBufferArr[x + y * width] >> 16);
                byte g = (byte) (colorBufferArr[x + y * width] >> 8);
                byte b = (byte) colorBufferArr[x + y * width];

                bmp.setRGB(x, y, new Color(a, r, g, b).getRGB());
            }
        }

      //  bmp.Save(path);
	// write your code here
    }
}
