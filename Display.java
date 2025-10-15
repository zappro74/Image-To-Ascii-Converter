import java.util.ArrayList;

class Display
{
    public static void printToConsole(ArrayList<String> lines)
    {
        for (int i = 0; i < lines.size(); i++)
        {
            System.out.println(lines.get(i));
        }
    }

    public static void saveToTxtFile(ArrayList<String> lines)
    {
        
    }
}