import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class FileRetriever
{
    private String inputPath;
    private BufferedImage image;

    public FileRetriever(String inputPath)
    {
        this.inputPath = inputPath;
    }

    public int[] getPixelsFromImage(String inputPath)
    {
        BufferedImage image = null;

        try
        {
            File imageFile = new File(inputPath);
            image = ImageIO.read(imageFile);

            if (image == null) 
            {
                System.out.println("Invalid image format or file not found.");
                return null;
            }

            System.out.println("Image loaded successfully!");
        } 
        catch (IOException e) 
        {
            System.out.println("Error loading image: " + e.getMessage());
            return null;
        }

        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = new int[width * height];

        image.getRGB(0, 0, width, height, pixels, 0, width);

        return pixels;
    }
}