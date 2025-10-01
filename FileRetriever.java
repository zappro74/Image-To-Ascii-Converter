import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class FileRetriever
{
    private String inputPath;
    private BufferedImage image;
    private File imageFile;

    public FileRetriever(String inputPath)
    {
        if (inputPath == null || inputPath.isBlank())
        {
            throw new IllegalArgumentException("Input path cannot be null or blank");
        }
        this.inputPath = inputPath;
        this.imageFile = new File(inputPath);
    }

    public boolean loadImage()
    {
        try
        {
            image = ImageIO.read(imageFile);

            if (image == null)
            {
                System.out.println("Invalid image format or file not found.");
                return false;
            }

            System.out.println("Image loaded successfully!");
            return true;
        }
        catch (IOException e)
        {
            System.out.println("Error loading image: " + e.getMessage());
            return false;
        }
    }

    public int[] getPixelsFromImage()
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