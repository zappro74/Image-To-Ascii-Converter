import java.util.ArrayList;

class ImageToAscii
{
    private static final String DEFAULT_RAMP = "@%#*+=-:.";

    public static ArrayList<String> toAsciiLines(double[][] brightness)
    {
        ArrayList<String> lines = new ArrayList<>();
        String ramp = DEFAULT_RAMP;

        for (int y = 0; y < brightness.length; y++)
        {
            String row = "";
            for (int x = 0; x < brightness[0].length; x++)
            {
                double value = brightness[y][x];
                int index = (int) ((value / 225.0) * (ramp.length() - 1));

                row += ramp.charAt(index);
            }
            
            lines.add(row);
        }

        return lines;
    }
}