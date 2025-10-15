import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class UserInput
{
    private static final Scanner INPUT = new Scanner(System.in);
    private String path = null;
    private String outputPath = null;
    private int width = 160;
    private boolean choiceIsValid = false;
    private File file;

    public File requestFile() throws FileNotFoundException
    {
        System.out.println("Enter the file path of the image you would like to convert to ASCII: ");

        path = INPUT.nextLine();
        if (path == null || path.isBlank())
        {
            throw new IllegalArgumentException("File path cannot be null or blank");
        }
        file = new File(path);

        if (!file.exists())
        {
            throw new FileNotFoundException("File not found in current directory: " + path);
        }
        return file;
    }

    public void displayOptions() //Ian Coopers fault...
    {
        System.out.println("Enter prefered image width (Press enter for default): ");
        System.out.println("0) Print to console - fits in colsole (80 characters wide)");
        System.out.println("1) Medium - fits most screens (120 characters wide)");
        System.out.println("2) Large - more detail (160 characters wide)");
        System.out.println("3) Extra Large - high detail (200 characters wide)");
        System.out.println("4) Ultra Large - max detail (240 characters wide)");
        System.out.println("5) Exit Program - :(");
    }

    public int requestImageWidth()
    {
        displayOptions();
        String widthChoice = INPUT.nextLine();

        try 
        {
            switch (widthChoice.toUpperCase()) 
            {
                case "0":
                    width = 80;
                    break;
                case "1":
                case "MEDIUM":
                    width = 120;
                    break;
                case "2":
                case "LARGE":
                    width = 160;
                    break;
                case "3":
                case "EXTRA LARGE":
                    width = 200;
                    break;
                case "4":
                case "ULTRA LARGE":
                    width = 240;
                    break;
                case "5":
                case "EXIT":
                    System.out.println("Bye :(");
                    System.exit(0);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid choice: " + widthChoice);
            }
            System.out.println("Chosen width: " + width);
            return width;

        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please enter a valid option (1-5 or name).");
            return requestImageWidth();  //RECURSION!!!! :O
        }
    }

    public void requestFileOutput()
    {
        System.out.println("Enter the path of the directory you want to output the converted image to (Press enter for default (current directory)): ");
        System.out.println("Enter what you want the converted image to be named");
    }

    public File getFile()
    {
        return file;
    }

    public String getPath()
    {
        return path;
    }

    public int getTargetWidth()
    {
        return width;
    }
}