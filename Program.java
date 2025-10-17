import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

            boolean willColor = user.requestColoredOutput();
            int targetWidth = user.requestImageWidth();

            String rampChoice = user.requestRampType();

            int numberOfLevels;
            if (rampChoice.equals("DENSE"))
            {
                numberOfLevels = 71;
            }
            else
            {
                numberOfLevels = 68;
            }

            double[][] brightness = ImageLoader.calculatePixels(image);
            double[][] resizedBrightness = ImageController.resizeOutput(image, brightness, targetWidth);
            double[][] ditheredBrightness = ImageController.ditherOutput(resizedBrightness, numberOfLevels);
            
            if (willColor) 
            {
                double[][][] rgbPixels = ImageLoader.calculateRgbPixels(image);
                double[][][] resizedRgbPixels = ImageController.resizeRgb(rgbPixels, targetWidth);
                Display.printToConsole(ImageToAscii.toAsciiLinesColored(ditheredBrightness, resizedRgbPixels, rampChoice));
                return;
            } 
            else 
            {
                boolean willSave = user.requestFileOutput();
                if (willSave) 
                {
                    Display.saveToTxtFile(ImageToAscii.toAsciiLines(ditheredBrightness, rampChoice), user.getOutputFileName());
                } 
                else 
                {
                    Display.printToConsole(ImageToAscii.toAsciiLines(ditheredBrightness, rampChoice));
                }
            }
        }
        catch (FileNotFoundException e) 
        {
            openGif();
            System.out.println("Error: " + e.getMessage());
        }
        catch (RuntimeException e)
        {
            openGif();
            System.out.println("Error processing image: " + e.getMessage());
        }
    }

    private void openGif()
    {
        try 
        {
            Desktop desktop = Desktop.getDesktop();
            File errorGif = new File("gifThatDisplaysOnExceptionThrow.gif");
            if (errorGif.exists()) 
            {
                desktop.open(errorGif);
            } 
            else 
            {
                System.out.println("Error GIF file not found.");
            }
        } 
        catch (IOException | IllegalArgumentException ex) 
        {
            System.out.println("Failed to open error GIF: " + ex.getMessage());
        }
    }
}
