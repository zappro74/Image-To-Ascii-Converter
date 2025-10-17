import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ImageLoader
{
    private static double[][] pixels;

    public static BufferedImage loadImage(File file) 
    {
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(file);
            if (image == null) 
            {
                throw new IllegalArgumentException("Unsupported or empty image: " + file.getName());
            }
        } 
        catch (IOException e) 
        {
            throw new RuntimeException("Failed to load image at " + file.getPath(), e);
        }

        return image;
    }

    public static double[][] calculatePixels(BufferedImage image)
    {
        if (image == null)
        {
            throw new IllegalStateException("No image loaded");
        }

        int width = image.getWidth();
        int height = image.getHeight();
        pixels = new double[height][width];

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int rgb = image.getRGB(x, y);
                
                int red   = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8)  & 0xFF;
                int blue  = rgb & 0xFF;

                double luminance = 0.2126 * red + 0.7152 * green + 0.0722 * blue;

                pixels[y][x] = luminance;
            }
        }
        return pixels;
    }

    //stretch goal 2
    public static double[][][] calculateRgbPixels(BufferedImage image) 
    {
        int width = image.getWidth();
        int height = image.getHeight();
        double[][][] rgbPixels = new double[height][width][3];

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int rgb = image.getRGB(x, y);
                rgbPixels[y][x][0] = (rgb >> 16) & 0xFF; //red value
                rgbPixels[y][x][1] = (rgb >> 8) & 0xFF; //green value
                rgbPixels[y][x][2] = rgb & 0xFF; //blue value
            }
        }
        return rgbPixels;
    }
}