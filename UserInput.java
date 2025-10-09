import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class UserInput
{
    private static String path = null;
    private static String widthChoice;
    private static int width = 160;
    private static boolean choiceIsValid = false;
    private static File file;

    public static File requestFile() throws FileNotFoundException
    {
        System.out.println("Enter the file name of the image you would like to convert to ASCII: ");
        System.out.println("*Make sure the image is in the same directory as this program*");

        Scanner input = new Scanner(System.in);
        path = input.nextLine();
        if (path == null || path.isBlank())
        {
            throw new IllegalArgumentException("File path cannot be null or blank");
        }
        file = new File(path);

        if (!file.exists())
        {
            throw new FileNotFoundException("File not found in current directory: " + path);
        }

        input.close();
        return file;
    }

    public static void displayOptions() //Ian Coopers fault...
    {
        System.out.println("Enter prefered image width (Press enter for default): ");
        System.out.println("1) Medium - fits most screens (120 characters wide)");
        System.out.println("2) Large - more detail (160 characters wide)");
        System.out.println("3) Extra Large - high detail (200 characters wide)");
        System.out.println("4) Ultra Large - max detail (240 characters wide)");
        System.out.println("5) Exit Program - :(");
    }

    public static int requestImageWidth()
    {
        Scanner input = new Scanner(System.in);
        choiceIsValid = false;
        while (!choiceIsValid)
        {
            displayOptions();
            widthChoice = input.nextLine();
            
            switch(widthChoice.toUpperCase())
            {
                case "1":
                case "MEDIUM":
                    choiceIsValid = true;
                    width = 120;
                    break;
                case "2":
                case "LARGE":
                    choiceIsValid = true;
                    width = 160;
                    break;
                case "3":
                case "EXTRA LARGE":
                    choiceIsValid = true;
                    width = 200;
                    break;
                case "4":
                case "ULTRA LARGE":
                    choiceIsValid = true;
                    width = 240;
                    break;
                case "5":
                case "EXIT":
                    System.out.println("Bye :(");
                    System.exit(0);
            }
        }
        input.close();
        return width;
    }

    public static void requestFileOutput()
    {
        
    }

    public static File getFile()
    {
        return file;
    }

    public static String getPath()
    {
        return path;
    }

    public static int getTargetWidth()
    {
        return width;
    }
}