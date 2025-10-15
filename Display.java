import java.io.FileWriter;
import java.io.IOException;
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

    public static void saveToTxtFile(ArrayList<String> lines, String filePath)
    {
        try (FileWriter writer = new FileWriter(filePath))
        {
            for (String line : lines)
            {
                writer.write(line + System.lineSeparator());
            }
            System.out.println("Output successfully written to: " + filePath);
        }
        catch (IOException e)
        {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}