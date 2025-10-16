import java.awt.image.BufferedImage;

class ImageController
{   
    public static final double CHAR_ASPECT = 0.38; //for tinkering with image height

    public static double[][] resizeOutput(BufferedImage image, double[][] originalPixels, int targetWidth)
    {
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        int targetHeight = (int) Math.max(1 , Math.round(targetWidth * ((double) originalHeight / originalWidth) * CHAR_ASPECT));
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

    public static double[][] ditherOutput(double[][] sourceBrightness, int numberOfLevels) //Refered to pseudocode from https://en.wikipedia.org/wiki/Floyd%E2%80%93Steinberg_dithering
    {
        int height = sourceBrightness.length;
        int width = sourceBrightness[0].length;

        double[][] sourceCopy = new double[height][width];
        for (int y = 0; y < height; y++)
        {
            System.arraycopy(sourceBrightness[y], 0, sourceCopy[y], 0, width);
        }

        double[][] ditheredBrightness = new double[height][width];

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                //keep algorithm from going out of bounds
                double oldPixel = sourceCopy[y][x];

                if (oldPixel < 0)
                {
                    oldPixel = 0;
                }
                if (oldPixel > 255)
                {
                    oldPixel = 255;
                }

                sourceCopy[y][x] = oldPixel;

                double normalized = oldPixel / 255.0;
                int levelIndex = (int) Math.round(normalized * (numberOfLevels - 1));
                if (levelIndex < 0) 
                {
                    levelIndex = 0;
                }
                if (levelIndex > numberOfLevels - 1)
                {
                    levelIndex = numberOfLevels - 1;
                }
                
                double newPixel = (levelIndex / (double)(numberOfLevels - 1)) * 255;

                ditheredBrightness[y][x] = newPixel;

                double quantError = oldPixel - newPixel;

                if (x + 1 < width)                    
                    ditheredBrightness[y][x + 1] += quantError * 7.0 / 16.0;

                if (y + 1 < height && x - 1 >= 0)     
                    ditheredBrightness[y + 1][x - 1] += quantError * 3.0 / 16.0;

                if (y + 1 < height)                   
                    ditheredBrightness[y + 1][x] += quantError * 5.0/16.0;

                if (y + 1 < height && x + 1 < width)  
                    ditheredBrightness[y + 1][x + 1] += quantError * 1.0 / 16.0;
            }
        }
        return ditheredBrightness;
    }
}