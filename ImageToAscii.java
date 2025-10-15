import java.util.ArrayList;

class ImageToAscii
{
    private static final String DEFAULT_RAMP = "@%#*+=-:.";

    public static ArrayList<String> toAsciiLines(double[][] brightness)
    {
        ArrayList<String> lines = new ArrayList<>(brightness.length);
        String ramp = DEFAULT_RAMP;

        for (int y = 0; y < brightness.length; y++)
        {
            StringBuilder row = new StringBuilder(brightness[0].length);
            for (int x = 0; x < brightness[0].length; x++)
            {
                double value = brightness[y][x];
                int index = (int) Math.round((value / 225.0) * (ramp.length() - 1));
                index = Math.max(0, Math.min(index, ramp.length() - 1));

                row.append(ramp.charAt(index));
            }

            lines.add(row.toString());
        }

        return lines;
    }
}