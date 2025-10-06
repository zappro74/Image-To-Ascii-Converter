import java.util.Scanner;

class UserInput
{
    private static String userInput;
    private static boolean choiceIsValid;
    private static Scanner input = new Scanner(System.in);

    public static int requestImageWidth()
    {
        choiceIsValid = false;
        while (!choiceIsValid)
        {
            System.out.println("Enter prefered image width (Press enter for default):");
            System.out.println("1) Medium - fits most screens (120 characters wide)");
            System.out.println("2) Large - more detail (160 characters wide)");
            System.out.println("3) Extra Large - high detail (200 characters wide)");
            System.out.println("4) Ultra Large - max detail (240 characters wide)");
            userInput = input.nextLine();
            
            switch(userInput.toUpperCase())
            {
                case "1":
                case "MEDIUM":
                    choiceIsValid = true;
                    return 120;
                case "2":
                case "LARGE":
                case "":
                    choiceIsValid = true;
                    return 160;
                case "3":
                case "EXTRA LARGE":
                    choiceIsValid = true;
                    return 200;
                case "4":
                case "ULTRA LARGE":
                    choiceIsValid = true;
                    return 240;
            }
        }
        return 300;
    }
}