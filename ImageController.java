import java.awt.image.BufferedImage;

class ImageController
{   
    public static double[][] resizeOutput(BufferedImage image, double[][] originalPixels, int targetWidth)
    {
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        int targetHeight = (int) Math.max(1 , Math.round(targetWidth * ((double) originalHeight / originalWidth)));
        double[][] resizedPixels = new double[targetHeight][targetWidth];

        double scaleX = (double) originalWidth / targetWidth;
        double scaleY = (double) originalHeight / targetHeight;

        for (int y = 0; y < targetHeight; y++)
        {
            int sourceY = Math.min((int) Math.floor(y * scaleY), originalHeight - 1);
            for (int x = 0; x < targetWidth; x++)
            {
                int sourceX = Math.min((int) Math.floor(x * scaleX), originalWidth - 1);
                resizedPixels[y][x] = originalPixels[sourceY][sourceX];
            }
        }
        System.out.println("Image (Converted) dimensions: " + targetWidth + "x" + targetHeight);
        return resizedPixels;
    }
}