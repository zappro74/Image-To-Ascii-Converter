import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;


class Program
{
    private UserInput user = new UserInput();
    
    public void run() 
    {
        try 
        {
            File file = user.requestFile();
            BufferedImage image = ImageLoader.loadImage(file);
            double[][] brightness = ImageLoader.calculatePixels(image);

            System.out.println("Image processed successfully!");
            System.out.println("Image (Pre-conversion) dimensions: " + image.getWidth() + "x" + image.getHeight());
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
        catch (RuntimeException e)
        {
            System.out.println("Error processing image: " + e.getMessage());
        }
    }
}