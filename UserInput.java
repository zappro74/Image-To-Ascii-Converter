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

    public UserInput()
    {

    }

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
        System.out.println("Enter prefered image width: ");
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
                    System.out.println("The image will only be displayed in the console.");
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
        System.out.println("Enter the directory (including file name) that you would like to save this file to:  (Example: C:\\Users\\<user>\\Desktop\\output.txt): ");
        outputFileName = INPUT.nextLine().trim();

        if (outputFileName.isEmpty())
        {
            throw new IllegalArgumentException("File path cannot be empty.");
        }

        if (!outputFileName.toLowerCase().endsWith(".txt"))
        {
            outputFileName += ".txt";
        }

        if (!outputFileName.matches("^[\\w,\\s-\\\\:\\.]+\\.txt$"))
        {
            throw new IllegalArgumentException("File name contains invalid characters. Only letters, numbers, slashes, spaces, dashes, and underscores are allowed.");
        }

        willSaveToFile = true;
        System.out.println("Output will be saved as: " + outputFileName);
    }

    public String getOutputFileName() 
    { 
        if (!willSaveToFile || outputFileName == null || outputFileName.isBlank()) 
        {
            throw new IllegalStateException("Output file name not set. Call requestFileOutput() first and choose Yes.");
        }
        return outputFileName;
    }

    public String requestRampType() //Stretch goal 1
    {
        System.out.println("Choose a character ramp type (Enter the name): ");
        System.out.println("1) Simple (Default) - @%#*+=-:. ");
        System.out.println("2) Light mode - .:-=+*#%@ ");
        System.out.println("3) Dense - $@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/|()1{}[]?-_+~<>i!lI;:,\"^`'. ");
        String rampChoice = INPUT.nextLine();

        try
        {
            switch (rampChoice.toUpperCase()) 
            {
                case "1":
                case "SIMPLE":
                    rampChoice = "SIMPLE";
                    break;
                case "2":
                case "LIGHT MODE":
                case "LIGHT":
                    rampChoice = "LIGHT";
                    break;
                case "3":
                case "DENSE":
                    rampChoice = "DENSE";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid choice: " + rampChoice);
            }
            System.out.println("Chosen ramp: " + rampChoice);
            return rampChoice;

        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please enter a valid option (1-3 or name).");
            return requestRampType();
        }
    }

    //Stretch goal 2
    public boolean requestColoredOutput()
    {
        System.out.println("\u001B[31mThis is a \u001B[0m \u001B[32mTEST to see \u001B[0m \u001B[34mif your console\u001B[0m");
        System.out.println("\u001B[38;2;255;128;0mcan print color\u001B[0m");
        System.out.println("Would you like the output to be colored? (Yes or No)");
        System.out.println("*If the test above does not show color select no*");
        String choice = INPUT.nextLine();
        boolean willColor = false;

        switch (choice.toUpperCase())
        {
            case "Y":
            case "YES":
                willColor = true;
                break;
            case "N":
            case "NO":
                willColor = false;
        }
        return willColor;
    }
}