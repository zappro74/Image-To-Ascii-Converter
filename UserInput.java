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
    private String outputFileName;
    private boolean willSaveToFile;

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

    private void displayOptions() //Ian Coopers fault...
    {
        System.out.println("Enter prefered image width (Press enter for default): ");
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
                case "0": //testing purposes
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

    public boolean requestFileOutput()
    {
        System.out.println("Do you want the output to be saved to a .txt(text) file? (Reccomended for larger images)");
        System.out.println("Yes or No?");
        String choice = INPUT.nextLine();
        try
        {
            switch (choice.toUpperCase())
            {
                case "Y":
                case "YES":
                    requestFileName();
                    break;
                case "N":
                case "No":
                    willSaveToFile = false;
                    System.out.println("The image will be displayed in the console.");
                    break;
                default:
                    throw new IllegalArgumentException("Invalid choice: " + choice);
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please enter a valid option (y, yes, n, no)");
            return requestFileOutput();
        }
        return willSaveToFile;
    }

    private void requestFileName()
    {
        System.out.println("Enter what you want this text file to be named: ");
        outputFileName = INPUT.nextLine().trim();

        if (outputFileName.isEmpty())
        {
            throw new IllegalArgumentException("File name cannot be empty.");
        }

        if (!outputFileName.toLowerCase().endsWith(".txt"))
        {
            outputFileName += ".txt";
        }

        if (!outputFileName.matches("^[\\w,\\s-]+\\.txt$"))
        {
            throw new IllegalArgumentException("File name contains invalid characters. Only letters, numbers, spaces, dashes, and underscores are allowed.");
        }

        willSaveToFile = true;
        System.out.println("Output will be saved as: " + outputFileName);
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
    
    public String getOutputFileName()
    {
        return outputFileName;
    }
}