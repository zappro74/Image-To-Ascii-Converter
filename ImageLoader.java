import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ImageLoader
{
    private static String path = UserInput.getPath();
    private static BufferedImage image;
    private static File file = UserInput.getFile();
    private static int[][] pixels;

    public static void loadImage() 
    {
        try 
        {
            image = ImageIO.read(file);
            if (image == null) 
            {
                throw new IllegalArgumentException("Unsupported or empty image: " + path);
            }
        } 
        catch (IOException e) 
        {
            throw new RuntimeException("Failed to load image at " + path, e);
        }
    }

    public static void CalculatePixels()
    {
        if (image == null)
        {
            throw new IllegalStateException("No image loaded");
        }

        int width = image.getWidth();
        int height = image.getHeight();
        pixels = new int[height][width];

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                pixels[x][y] = image.getRGB(x, y);
            }
        }
    }

    public static int[][] getPixels()
    {
        return pixels;
    }
}