import java.util.Scanner;

class UserInput
{
    private static String userChoice;
    private static int userChoiceValue = 160;
    private static boolean choiceIsValid;

    public static int requestImageWidth()
    {
        Scanner input = new Scanner(System.in);
        choiceIsValid = false;
        while (!choiceIsValid)
        {
            System.out.println("Enter prefered image width (Press enter for default):");
            System.out.println("1) Medium - fits most screens (120 characters wide)");
            System.out.println("2) Large - more detail (160 characters wide)");
            System.out.println("3) Extra Large - high detail (200 characters wide)");
            System.out.println("4) Ultra Large - max detail (240 characters wide)");
            System.out.println("5) Exit Program - :(");
            userChoice = input.nextLine();
            
            switch(userChoice.toUpperCase())
            {
                case "1":
                case "MEDIUM":
                    choiceIsValid = true;
                    userChoiceValue = 120;
                    break;
                case "2":
                case "LARGE":
                    choiceIsValid = true;
                    userChoiceValue = 160;
                    break;
                case "3":
                case "EXTRA LARGE":
                    choiceIsValid = true;
                    userChoiceValue = 200;
                    break;
                case "4":
                case "ULTRA LARGE":
                    choiceIsValid = true;
                    userChoiceValue = 240;
                    break;
                case "5":
                case "EXIT":
                    System.out.println("Bye :(");
                    System.exit(0);
            }
        }
        input.close();
        return userChoiceValue;
    }
}