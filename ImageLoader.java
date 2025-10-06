import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ImageLoader
{
    private static String path = UserInput.getPath();
    private static BufferedImage image;
    private static File file = UserInput.getFile();

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

    public static int[] getPixelsFromImage()
    {
        if (image == null)
        {
            throw new IllegalStateException("No image loaded");
        }

        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = new int[width * height];

        image.getRGB(0, 0, width, height, pixels, 0, width);

        return pixels;
    }
}