import java.awt.image.BufferedImage;

class ImageController
{   
    public static double[][] resizeOutput(BufferedImage image, double[][] originalPixels, int targetWidth)
    {
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        int targetHeight = (int) (targetWidth * ((double) originalHeight / originalWidth));
        double[][] resizedPixels = new double[targetHeight][targetWidth];

        for (int y = 0; y < targetHeight; y++)
        {
            for (int x = 0; x < targetWidth; x++)
            {
                int sourceX = (int) (x * ((double) originalWidth / targetWidth) * .5);
                int sourceY = (int) (y * ((double) originalHeight / targetHeight) * .5);
                resizedPixels[y][x] = originalPixels[sourceY][sourceX];
            }
        }

        System.out.println("Image (Converted) dimensions: " + targetWidth + "x" + targetHeight);

        return resizedPixels;
    }
}