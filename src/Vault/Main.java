package Vault;

import com.sun.jna.Memory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String username = "intern1";
        String password = "udt3hGO";
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

        //try {
            context.Connect(server, "Java Sample", username, password);
            context.RequestLicense(Vault.VaultAPI.LicenseType.Render);
            /*
            Vault.vdkLicenseInfo info = new Vault.vdkLicenseInfo();
            context.GetLicenseInfo(Vault.VaultAPI.LicenseType.Render, info);

            if (info.queuePosition == -1) {
                //long unixTimestamp = (long) (LocalDateTime.UtcNow.Subtract(LocalDateTime(1970, 1, 1))).TotalSeconds;
              //  System.out.printf("License fetched and available for another %1$s seconds." + "\r\n", info.expiresTimestamp - unixTimestamp);
            }
            */
            renderer.Create(context);
            renderView.Create(context, renderer, width, height);
            udModel.Load(context, modelName, header);
            Memory tempRef_colorBuffer = new Memory(width * height * Integer.BYTES);
            Memory tempRef_depthBuffer = new Memory(width * height * Float.BYTES);
            renderView.SetTargets(tempRef_colorBuffer, 0, tempRef_depthBuffer);

            double[] cameraMatrix =
                    {1, 0, 0, 0,
                    0, 1, 0, 0,
                    0, 0, 1, 0,
                    0, -5, 0, 1};

            renderView.SetMatrix(Vault.VaultAPI.RenderViewMatrix.Camera, cameraMatrix);
            Vault.vdkRenderInstance item =  new Vault.vdkRenderInstance();
            //item.write();
            item.pointCloud = udModel.ppModel.getPointer(0);
            item.worldMatrix = header.storedMatrix;

            Vault.vdkRenderInstance itemFlipped = new Vault.vdkRenderInstance();
            //itemFlipped.write();
            itemFlipped.pointCloud = udModel.ppModel.getPointer(0);
            itemFlipped.worldMatrix = header.storedMatrix;
            itemFlipped.worldMatrix[0] = -itemFlipped.worldMatrix[0];
            itemFlipped.worldMatrix[5] = -itemFlipped.worldMatrix[5];
            itemFlipped.worldMatrix[10] = -itemFlipped.worldMatrix[10];


            Vault.vdkRenderInstance[] modelArray = new Vault.vdkRenderInstance[]{item, itemFlipped};
            System.out.println(renderView);


            for (int i = 0; i < 10; i++) {
                renderer.Render(renderView, modelArray, modelArray.length, null);
            }
            depthBuffer = tempRef_depthBuffer.getFloatArray(0,width*height);
            colorBuffer = tempRef_colorBuffer.getIntArray(0,width*height);
            tempRef_colorBuffer.disposeAll();
            tempRef_depthBuffer.disposeAll();

            String imagePath = "tmp.png";
            SaveColorImage(imagePath, width, height, colorBuffer);

            if (pause) {
                System.out.println(imagePath + " written to the build directory.");
                System.out.println("Press any key to exit.");
                //Console.ReadKey();
            }

            //! Uncomment the following line to test the convert API
            //Convert(modelName, modelName + ".uds", context);
        //} finally {
            udModel.Unload();
            renderView.Destroy();
            renderer.Destroy();
            context.Disconnect();
        //}
    }

    static void Convert(String inputPath, String outputPath, Vault.vdkContext context) {
        Vault.vdkLicenseInfo info = new Vault.vdkLicenseInfo();
        context.GetLicenseInfo(Vault.VaultAPI.LicenseType.Convert, info);

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
        int x = 0, y = 0;
        int val;
        byte a = 0, r = 0, g = 0, b =0;
        try {
            for (y = 0; y < height; y++) {
                for (x = 0; x < width; x++) {
                    val = colorBufferArr[x+y*width];
                    a = (byte) (val >> 24);
                    //a= (byte) 254;
                    r = (byte) (val >> 16);
                    g = (byte) (val >> 8);
                    b = (byte) val;

                    bmp.setRGB(x, y, r<<16 | g <<8 | b);
                }
            }
        }
        catch(IllegalArgumentException e){
            Logger.getAnonymousLogger().log(Level.WARNING,String.format("Illegal Colour a: %d r: %d g: %d b: %d at (%d,%d)",a, r,g,b,x,y));
        }
        try {
            ImageIO.write(bmp, "png", new File("saved.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //bmp.Save(path);
	// write your code here
    }
}
