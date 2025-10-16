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
            System.out.println("Image processed successfully!");
            System.out.println("Image (Pre-conversion) dimensions: " + image.getWidth() + "x" + image.getHeight());

            double[][] brightness = ImageLoader.calculatePixels(image);
            double[][] resizedBrightness = ImageController.resizeOutput(image, brightness, user.requestImageWidth());

            double[][] ditheredBrightness = ImageController.ditherOutput(resizedBrightness, 9); 

            boolean willSave = user.requestFileOutput();
            
            if (willSave)
            {
                Display.saveToTxtFile(ImageToAscii.toAsciiLines(ditheredBrightness, user.requestRampType()), user.getOutputFileName());
            }
            else
            {
                Display.printToConsole(ImageToAscii.toAsciiLines(ditheredBrightness, user.requestRampType()));
            }
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